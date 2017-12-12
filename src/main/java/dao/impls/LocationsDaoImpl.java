package dao.impls;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dao.LocationsDao;
import dao.converter.Converter;
import entities.Department;
import entities.Location;
import metamodel.dao.models.*;
import entities.ObjectType;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LocationsDaoImpl implements LocationsDao {
    private static Map<Integer, LocationCache> grantCacheMap = new HashMap<>();

    private ObjectTypes objectType;

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
            locations.add(getFromCache(object, grant));
        }
        return locations;
    }

    @Override
    public Location findById(Integer id, Integer grant) {
        Objects object = metaModelService.findObjectByObjectId(id, grant);
        return getFromCache(object, grant);
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

        getLocationCache(grant).put(location);
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

        getLocationCache(grant).put(location);
    }

    @Override
    public void delete(Integer locationId, Integer grant) {
        metaModelService.deleteParamsByObjectId(locationId, grant);
        metaModelService.deleteObjectById(locationId, grant);

        getLocationCache(grant).delete(locationId);
    }

    private Location getFromCache(Objects object, Integer grant){
        LocationCache locationCache = getLocationCache(grant);
        Location location = locationCache.getByObject(object, grant);

        return location;
    }

    private LocationCache getLocationCache(Integer grant) {
        LocationCache locationCache = grantCacheMap.get(grant);

        if (locationCache == null)
            grantCacheMap.put(grant, new LocationCache());

        return grantCacheMap.get(grant);
    }

    class LocationCache {

        private LoadingCache<Integer, Location> cache;
        private Objects object;
        private Integer grant;

        public LocationCache() {
            cache = CacheBuilder.newBuilder()
                    .maximumSize(100)                             // maximum 100 records can be cached
                    .expireAfterAccess(5, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
                    .build(new CacheLoader<Integer, Location>() {  // build the cacheloader

                        @Override
                        public Location load(Integer locId) throws Exception {
                            return locationConverter.convertDataToTemplate(
                                    object,
                                    metaModelService.getParamsMap(locId, grant),
                                    Location.class);
                        }
                    });
        }

        Location getByObject(Objects object, Integer grant){
            try {
                this.object = object;
                this.grant = grant;
                return cache.get(object.getObjectId());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return new Location();
        }

        void put(Location location) {
            cache.put(location.getObjectId(), location);
        }

        void delete(Integer locationId) {
            cache.invalidate(locationId);
        }
    }

}
