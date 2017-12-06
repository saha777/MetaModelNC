package dao.impls;

import dao.LocationsDao;
import dao.converter.Converter;
import entities.Location;
import metamodel.dao.models.*;
import entities.ObjectType;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationsDaoImpl implements LocationsDao {

    private static Map<Integer, Location> cache = new HashMap<>();

    private ObjectTypes objectType;

//    private final ObjectTypesDao objectTypesDao;
//    private final AttrsDao attrsDao;
//    private final ParamsDao paramsDao;
//    private final ObjectsDao objectsDao;
    private Converter<Location> locationConverter;
    private MetaModelService metaModelService;

    @Autowired
    public LocationsDaoImpl(
            MetaModelService metaModelService,
            Converter<Location> locationConverter) {
        this.metaModelService = metaModelService;
        this.locationConverter = locationConverter;
        this.objectType = metaModelService.findObjectTypeByTypeName(ObjectType.LOCATION.toString());
    }

    @Override
    public List<Location> findAll(Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByTypeName(objectType.getName(), grant);
        List<Location> locations = new ArrayList<>();
        for(Objects object : objects){
            locations.add(checkLocationInCache(object, grant));
        }
        return locations;
    }

    @Override
    public Location findById(Integer id, Integer grant) {
        Objects object = metaModelService.findObjectByObjectId(id, grant);
        return checkLocationInCache(object, grant);
    }

    private Location checkLocationInCache(Objects object, Integer grant){
        Location location;
        if(cache.containsKey(object.getObjectId())) {
            location = cache.get(object.getObjectId());
        } else {
            location = locationConverter.convertDataToTemplate(
                    object,
                    metaModelService.getParamsMap(object.getObjectId(), grant),
                    Location.class);

            cache.put(location.getObjectId(), location);
        }
        return location;
    }

    @Override
    public void save(Location location, Integer grant) {
        Integer objectTypeId = objectType.getTypeId();

        location.setObjectId(
                metaModelService.saveObject(
                        locationConverter.convertTemplateToObjects(
                            location,
                            objectTypeId
                        ),
                        grant
                ));

        metaModelService.saveParams(
                locationConverter.convertTemplateToParams(
                    location,
                    objectTypeId,
                    metaModelService.getAttrsMap(objectTypeId, grant)
                ),
                grant
        );

        cache.put(location.getObjectId(), location);
    }

    @Override
    public void update(Location location, Integer grant) {
        metaModelService.updateObject(
                locationConverter.convertTemplateToObjects(
                        location,
                        objectType.getTypeId()
                ),
                grant);

        metaModelService.updateParams(
                locationConverter.convertTemplateToParams(
                        location,
                        objectType.getTypeId(),
                        metaModelService.getAttrsMap(objectType.getTypeId(), grant)
                ),
                grant);

        cache.put(location.getObjectId(), location);
    }

    @Override
    public void delete(Integer locationId, Integer grant) {
        metaModelService.deleteParamsByObjectId(locationId, grant);
        metaModelService.deleteObjectById(locationId, grant);
        cache.remove(locationId);
    }

}
