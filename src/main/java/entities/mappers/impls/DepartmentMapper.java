package entities.mappers.impls;

import entities.Department;
import entities.mappers.MetaObject;
import entities.mappers.ObjectMapper;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.Map;

public class DepartmentMapper implements ObjectMapper<Department> {
    @Override
    public Department mapObject(Objects objects, Map<String, Params> paramsMap) {
        MetaObject metaObject = new MetaObject(objects, paramsMap);
        return mapObject(metaObject);
    }

    @Override
    public Department mapObject(MetaObject metaObject) {
        Department department = new Department();
        department.setMetaObject(metaObject);
        department.setObjectId(metaObject.getObjectId());
        department.setParentId(metaObject.getParentObjectId());
        department.setName(metaObject.getName());
        return department;
    }

    @Override
    public Department updateObject(Department temp, Map<String, String[]> params) {
        if (params.get("name") != null) {
            String name = params.get("name")[0];
            if (name != null && !name.equals(temp.getName()))
                temp.setName(name);
        }

        return temp;
    }
}
