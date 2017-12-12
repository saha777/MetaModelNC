package dao.impls;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dao.DepartmentDao;
import dao.converter.Converter;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import entities.Department;
import entities.ObjectType;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DepartmentsDaoImpls implements DepartmentDao {
    private static Map<Integer, DepartmentCache> grantCacheMap = new HashMap<>();

    private ObjectTypes objectType;

    private Converter<Department> departmentConverter;
    private MetaModelService metaModelService;

    @Autowired
    public DepartmentsDaoImpls(
            MetaModelService metaModelService,
            Converter<Department> departmentConverter) {
        this.metaModelService = metaModelService;
        this.departmentConverter = departmentConverter;
        this.objectType = metaModelService.findObjectTypeByTypeName(ObjectType.DEPARTMENT.toString());
    }

    @Override
    public List<Department> findAll(Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByTypeName(objectType.getName(), grant);
        List<Department> departments = new ArrayList<>();
        for(Objects object : objects){
            departments.add(checkDepartmentInCache(object, grant));
        }
        return departments;
    }

    @Override
    public List<Department> findByOfficeId(Integer officeId, Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByParentId(officeId, grant);
        List<Department> departments = new ArrayList<>();
        for(Objects object : objects){
            departments.add(checkDepartmentInCache(object, grant));
        }
        return departments;
    }

    @Override
    public Department findById(Integer id, Integer grant) {
        return checkDepartmentInCache(metaModelService.findObjectByObjectId(id, grant), grant);
    }

    private Department checkDepartmentInCache(Objects object, Integer grant){
        Department department;

        DepartmentCache departmentCache = grantCacheMap.get(grant);

        if (departmentCache == null)
            grantCacheMap.put(grant, new DepartmentCache());

        department = grantCacheMap.get(grant).getByObject(object, grant);

        return department;
    }

    @Override
    public void save(Department department, Integer grant) {
        Integer objectTypeId = objectType.getTypeId();

        department.setObjectId(
                metaModelService.saveObject(
                        departmentConverter.convertTemplateToObjects(
                                department,
                                objectTypeId
                        ),
                        grant
                ));

        metaModelService.saveParams(
                departmentConverter.convertTemplateToParams(
                        department,
                        objectTypeId,
                        metaModelService.getAttrsMap(objectTypeId, grant)
                ),
                grant
        );

        getDepartmentCache(grant).put(department);
    }

    @Override
    public void update(Department department, Integer grant) {
        metaModelService.updateObject(
                departmentConverter.convertTemplateToObjects(
                        department,
                        objectType.getTypeId()
                ),
                grant);

        metaModelService.updateParams(
                departmentConverter.convertTemplateToParams(
                        department,
                        objectType.getTypeId(),
                        metaModelService.getAttrsMap(objectType.getTypeId(), grant)
                ),
                grant);

        getDepartmentCache(grant).put(department);
    }

    @Override
    public void delete(Integer departmentId, Integer grant) {
        metaModelService.deleteParamsByObjectId(departmentId, grant);
        metaModelService.deleteObjectById(departmentId, grant);

        getDepartmentCache(grant).delete(departmentId);
    }

    private DepartmentCache getDepartmentCache(Integer grant) {
        DepartmentCache locationCache = grantCacheMap.get(grant);

        if (locationCache == null)
            grantCacheMap.put(grant, new DepartmentCache());

        return grantCacheMap.get(grant);
    }


    class DepartmentCache {

        private LoadingCache<Integer, Department> cache;
        private Objects object;
        private Integer grant;

        public DepartmentCache() {
            cache = CacheBuilder.newBuilder()
                    .maximumSize(100)                             // maximum 100 records can be cached
                    .expireAfterAccess(5, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
                    .build(new CacheLoader<Integer, Department>() {  // build the cacheloader

                        @Override
                        public Department load(Integer deptId) throws Exception {
                            return departmentConverter.convertDataToTemplate(
                                    object,
                                    metaModelService.getParamsMap(deptId, grant),
                                    Department.class);
                        }
                    });
        }

        public Department getByObject(Objects object, Integer grant){
            try {
                this.object = object;
                this.grant = grant;
                return cache.get(object.getObjectId());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return new Department();
        }

        void put(Department department) {
            cache.put(department.getObjectId(), department);
        }

        void delete(Integer departmentId) {
            cache.invalidate(departmentId);
        }
    }
}
