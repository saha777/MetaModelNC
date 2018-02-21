package dao.impls;

import dao.AbstractDao;
import dao.OfficeDao;
import dao.cache.Cache;
import dao.converter.Converter;
import dao.converter.impls.ConverterImpl;
import metamodel.dao.models.*;
import entities.ObjectType;
import entities.Office;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OfficeDaoImpl extends AbstractDao<Office> implements OfficeDao {
    private static Map<Integer, Cache<Office>> grantCacheMap = new ConcurrentHashMap<>();

    @Autowired
    public OfficeDaoImpl(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService) {
        super(metaModelService, attrsMapperService, paramsMapperService, ObjectType.OFFICE);
    }

    @Override
    protected Integer getObjId(Office temp) {
        return temp.getObjectId();
    }

    @Override
    protected Office insertIdIntoTemplate(Office temp, Integer id) {
        temp.setObjectId(id);
        return temp;
    }

    @Override
    protected Office checkInCache(Objects object, Role role) {
        if (object == null) return null;

        Cache<Office> cache = getCache(role);
        return cache.getByObject(object, role);
    }

    @Override
    protected void deleteFromCache(Integer officeId) {
        for (Integer key : grantCacheMap.keySet()) {
            Cache<Office> cache = grantCacheMap.get(key);
            cache.delete(officeId);
        }
    }

    private Cache<Office> getCache(Role role) {
        if (!grantCacheMap.containsKey(role.getRoleId()))
            grantCacheMap.put(role.getRoleId(), new Cache<>(converter, paramsMapperService, Office.class));

        return grantCacheMap.get(role.getRoleId());
    }
}
