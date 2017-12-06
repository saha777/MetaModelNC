package dao.impls;

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

public class DepartmentsDaoImpls implements DepartmentDao {
    private static Map<Integer, Department> cache = new HashMap<>();

    private ObjectTypes objectType;

    //    private final ObjectTypesDao objectTypesDao;
//    private final AttrsDao attrsDao;
//    private final ParamsDao paramsDao;
//    private final ObjectsDao objectsDao;
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
        if(cache.containsKey(object.getObjectId())) {
            department = cache.get(object.getObjectId());
        } else {
            department = departmentConverter.convertDataToTemplate(
                    object,
                    metaModelService.getParamsMap(object.getObjectId(), grant),
                    Department.class);

            cache.put(department.getObjectId(), department);
        }
        return department;
    }

    @Override
    public void save(Department department, Integer grant) {

    }

    @Override
    public void update(Department department, Integer grant) {

    }

    @Override
    public void delete(Integer departmentId, Integer grant) {

    }

    @Override
    public void commit() {

    }
}
