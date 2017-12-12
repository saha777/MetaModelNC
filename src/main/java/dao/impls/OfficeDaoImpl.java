package dao.impls;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dao.OfficeDao;
import dao.converter.Converter;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import entities.ObjectType;
import entities.Office;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class OfficeDaoImpl implements OfficeDao {
    private static Map<Integer, OfficeCache> grantCacheMap = new HashMap<>();

    private ObjectTypes objectType;

    private Converter<Office> officeConverter;
    private MetaModelService metaModelService;

    @Autowired
    public OfficeDaoImpl(
            MetaModelService metaModelService,
            Converter<Office> officeConverter) {
        this.metaModelService = metaModelService;
        this.officeConverter = officeConverter;
        this.objectType = metaModelService.findObjectTypeByTypeName(ObjectType.DEPARTMENT.toString());
    }

    @Override
    public List<Office> findAll(Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByTypeName(objectType.getName(), grant);
        List<Office> offices = new ArrayList<>();

        for(Objects object : objects){
            offices.add(getFromCache(object, grant));
        }

        return offices;
    }

    @Override
    public List<Office> findByLocationId(Integer locationId, Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByParentId(locationId, grant);
        List<Office> offices = new ArrayList<>();

        for(Objects object : objects){
            offices.add(getFromCache(object, grant));
        }

        return offices;
    }

    @Override
    public Office findById(Integer objectId, Integer grant) {
        Objects object = metaModelService.findObjectByObjectId(objectId, grant);
        return getFromCache(object, grant);
    }

    @Override
    public void save(Office office, Integer grant) {
        Integer objectTypeId = objectType.getTypeId();

        office.setObjectId(
                metaModelService.saveObject(
                        officeConverter.convertTemplateToObjects(
                                office,
                                objectTypeId
                        ),
                        grant
                ));

        metaModelService.saveParams(
                officeConverter.convertTemplateToParams(
                        office,
                        objectTypeId,
                        metaModelService.getAttrsMap(objectTypeId, grant)
                ),
                grant
        );

        getOfficeCache(grant).put(office);
    }

    @Override
    public void update(Office office, Integer grant) {
        metaModelService.updateObject(
                officeConverter.convertTemplateToObjects(
                        office,
                        objectType.getTypeId()
                ),
                grant);

        metaModelService.updateParams(
                officeConverter.convertTemplateToParams(
                        office,
                        objectType.getTypeId(),
                        metaModelService.getAttrsMap(objectType.getTypeId(), grant)
                ),
                grant);

        getOfficeCache(grant).put(office);
    }

    @Override
    public void delete(Integer officeId, Integer grant) {
        metaModelService.deleteParamsByObjectId(officeId, grant);
        metaModelService.deleteObjectById(officeId, grant);

        getOfficeCache(grant).delete(officeId);
    }



    private Office getFromCache(Objects object, Integer grant){
        OfficeCache locationCache = getOfficeCache(grant);
        Office location = locationCache.getByObject(object, grant);

        return location;
    }

    private OfficeCache getOfficeCache(Integer grant) {
        OfficeCache locationCache = grantCacheMap.get(grant);

        if (locationCache == null)
            grantCacheMap.put(grant, new OfficeCache());

        return grantCacheMap.get(grant);
    }

    class OfficeCache {

        private LoadingCache<Integer, Office> cache;
        private Objects object;
        private Integer grant;

        public OfficeCache() {
            cache = CacheBuilder.newBuilder()
                    .maximumSize(100)                             // maximum 100 records can be cached
                    .expireAfterAccess(5, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
                    .build(new CacheLoader<Integer, Office>() {  // build the cacheloader

                        @Override
                        public Office load(Integer locId) throws Exception {
                            return officeConverter.convertDataToTemplate(
                                    object,
                                    metaModelService.getParamsMap(locId, grant),
                                    Office.class);
                        }
                    });
        }

        Office getByObject(Objects object, Integer grant){
            try {
                this.object = object;
                this.grant = grant;
                return cache.get(object.getObjectId());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return new Office();
        }

        void put(Office office) {
            cache.put(office.getObjectId(), office);
        }

        void delete(Integer locationId) {
            cache.invalidate(locationId);
        }
    }
}
