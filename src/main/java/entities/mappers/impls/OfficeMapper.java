package entities.mappers.impls;

import entities.Office;
import entities.mappers.MetaObject;
import entities.mappers.ObjectMapper;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.Map;

public class OfficeMapper implements ObjectMapper<Office> {
    @Override
    public Office mapObject(Objects objects, Map<String, Params> paramsMap) {
        MetaObject metaObject = new MetaObject(objects, paramsMap);
        return mapObject(metaObject);
    }

    @Override
    public Office mapObject(MetaObject metaObject) {
        Office office = new Office();
        office.setMetaObject(metaObject);
        office.setObjectId(metaObject.getObjectId());
        office.setParentId(metaObject.getParentObjectId());
        office.setName(metaObject.getName());
        return office;
    }

    @Override
    public Office updateObject(Office temp, Map<String, String[]> params) {

        if (params.get("name") != null) {
            String name = params.get("name")[0];
            if (name != null && !name.equals(temp.getName()))
                temp.setName(name);
        }

        return temp;
    }
}
