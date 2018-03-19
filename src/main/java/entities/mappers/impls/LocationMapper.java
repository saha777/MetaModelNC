package entities.mappers.impls;

import entities.Location;
import entities.Office;
import entities.mappers.MetaObject;
import entities.mappers.ObjectMapper;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.Map;

public class LocationMapper implements ObjectMapper<Location> {
    @Override
    public Location mapObject(Objects objects, Map<String, Params> paramsMap) {
        MetaObject metaObject = new MetaObject(objects, paramsMap);
        return mapObject(metaObject);
    }

    @Override
    public Location mapObject(MetaObject metaObject) {
        Location location = new Location();
        location.setMetaObject(metaObject);
        location.setObjectId(metaObject.getObjectId());
        location.setParentId(metaObject.getParentObjectId());
        location.setName(metaObject.getName());
        return location;
    }

    @Override
    public Location updateObject(Location temp, Map<String, String[]> params) {
        if (params.get("name") != null) {
            String name = params.get("name")[0];
            if (name != null && !name.equals(temp.getName()))
                temp.setName(name);
        }

        return temp;
    }
}
