package dao.impls;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dao.EmployeesDao;
import entities.ObjectType;
import dao.converter.Converter;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;
import entities.Employee;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EmployeesDaoImpls implements EmployeesDao{
    private static Map<Integer, EmployeeCache> grantCacheMap = new HashMap<>();

    private ObjectTypes objectType;

    private MetaModelService metaModelService;
    private Converter<Employee> employeeConverter;

    @Autowired
    public EmployeesDaoImpls(MetaModelService metaModelService, Converter<Employee> employeeConverter) {
        this.metaModelService = metaModelService;
        this.employeeConverter = employeeConverter;
        objectType = metaModelService.findObjectTypeByTypeName(ObjectType.EMPLOYEE.toString());
    }

    @Override
    public List<Employee> findAll(Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByTypeName(objectType.getName(), grant);
        List<Employee> employees = new ArrayList<>();

        for(Objects object : objects){
            employees.add(checkEmployeeInCache(object, grant));
        }

        return employees;
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId, Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByParentId(departmentId, grant);
        List<Employee> employees = new ArrayList<>();

        for(Objects object : objects){
            employees.add(checkEmployeeInCache(object, grant));
        }

        return employees;
    }


    @Override
    public Employee findById(Integer id, Integer grant) {
        return checkEmployeeInCache(metaModelService.findObjectByObjectId(id, grant), grant);
    }

    @Override
    public void save(Employee employee, Integer grant) {
        Integer objectTypeId = metaModelService.findObjectTypeByTypeName(objectType.getName()).getTypeId();

        Objects object = employeeConverter.convertTemplateToObjects(employee, objectTypeId);

        List<Params> params = employeeConverter.convertTemplateToParams(
                employee,
                objectTypeId,
                metaModelService.getAttrsMap(objectTypeId, grant));

        metaModelService.saveObject(object, grant);
        metaModelService.saveParams(params, grant);

        getEmployeeCache(grant).put(employee);
    }

    @Override
    public void update(Employee employee, Integer grant) {
        Integer objectTypeId = metaModelService.findObjectTypeByTypeName(objectType.getName()).getTypeId();

        Objects object = employeeConverter.convertTemplateToObjects(employee, objectTypeId);

        List<Params> params = employeeConverter.convertTemplateToParams(
                employee,
                objectTypeId,
                metaModelService.getAttrsMap(objectTypeId, grant));

        metaModelService.updateObject(object, grant);
        metaModelService.updateParams(params, grant);

        getEmployeeCache(grant).put(employee);
    }

    @Override
    public void delete(Integer empId, Integer grant) {
        metaModelService.deleteParamsByObjectId(empId, grant);
        metaModelService.deleteObjectById(empId, grant);

        getEmployeeCache(grant).delete(empId);
    }

    private Employee checkEmployeeInCache(Objects object, Integer grant){
        Employee employee = getEmployeeCache(grant).getByObject(object, grant);
        return employee;
    }

    private EmployeeCache getEmployeeCache(Integer grant) {
        EmployeeCache departmentCache = grantCacheMap.get(grant);

        if (departmentCache == null)
            grantCacheMap.put(grant, new EmployeeCache());

        return grantCacheMap.get(grant);
    }


    class EmployeeCache {

        private LoadingCache<Integer, Employee> cache;
        private Objects object;
        private Integer grant;

        public EmployeeCache() {
            cache = CacheBuilder.newBuilder()
                    .maximumSize(100)                             // maximum 100 records can be cached
                    .expireAfterAccess(5, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
                    .build(new CacheLoader<Integer, Employee>() {  // build the cacheloader
                        @Override
                        public Employee load(Integer deptId) throws Exception {
                            return employeeConverter.convertDataToTemplate(
                                    object,
                                    metaModelService.getParamsMap(deptId, grant),
                                    Employee.class);
                        }
                    });
        }

        public Employee getByObject(Objects object, Integer grant){
            try {
                this.object = object;
                this.grant = grant;
                return cache.get(object.getObjectId());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return new Employee();
        }

        void put(Employee employee) {
            cache.put(employee.getObjectId(), employee);
        }

        void delete(Integer empId) {
            cache.invalidate(empId);
        }
    }
}
