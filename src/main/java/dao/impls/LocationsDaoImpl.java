package dao.impls;

import dao.AbstractDao;
import dao.LocationsDao;
import dao.cache.Cache;
import dao.converter.Converter;
import dao.converter.impls.ConverterImpl;
import entities.Location;
import metamodel.dao.models.*;
import entities.ObjectType;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocationsDaoImpl extends AbstractDao<Location> implements LocationsDao {
    private static Map<Integer, Cache<Location>> grantCacheMap = new ConcurrentHashMap<>();

    @Autowired
    public LocationsDaoImpl(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService) {
        super(metaModelService, attrsMapperService, paramsMapperService, ObjectType.LOCATION);
    }


    @Override
    protected Integer getObjId(Location temp) {
        return temp.getObjectId();
    }

    @Override
    protected Location insertIdIntoTemplate(Location temp, Integer id) {
        temp.setObjectId(id);
        return temp;
    }

    @Override
    protected Location checkInCache(Objects object, Role role) {
        if (object == null) return null;
        Cache<Location> cache = getCache(role);
        return cache.getByObject(object, role);
    }

    @Override
    protected void deleteFromCache(Integer locationId) {
        for (Integer key : grantCacheMap.keySet()) {
            Cache<Location> cache = grantCacheMap.get(key);
            cache.delete(locationId);
        }
    }

    private Cache<Location> getCache(Role role) {
        if (!grantCacheMap.containsKey(role.getRoleId()))
            grantCacheMap.put(role.getRoleId(), new Cache<>(converter, paramsMapperService, Location.class));

        return grantCacheMap.get(role.getRoleId());
    }

}
