package dao.impls;


import dao.AbstractDao;
import dao.DepartmentDao;
import dao.cache.Cache;
import entities.ObjectType;
import metamodel.dao.models.*;
import entities.Department;
import metamodel.dao.models.Objects;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DepartmentsDaoImpls extends AbstractDao<Department> implements DepartmentDao {
    private static Map<Integer, Cache<Department>> grantCacheMap = new ConcurrentHashMap<>();

    @Autowired
    public DepartmentsDaoImpls(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService) {
        super(metaModelService, attrsMapperService, paramsMapperService, ObjectType.DEPARTMENT);
    }

    @Override
    protected Integer getObjId(Department temp) {
        return temp.getObjectId();
    }

    @Override
    protected Department insertIdIntoTemplate(Department temp, Integer id) {
        temp.setObjectId(id);
        return temp;
    }

    @Override
    protected Department checkInCache(Objects object, Role role) {
        if (object == null) return null;

        Cache<Department> cache = getCache(role);
        return cache.getByObject(object, role);
    }

    @Override
    protected void deleteFromCache(Integer departmentId) {
        for (Integer key : grantCacheMap.keySet()) {
            Cache<Department> cache = grantCacheMap.get(key);
            cache.delete(departmentId);
        }
    }

    private Cache<Department> getCache(Role role) {
        if (!grantCacheMap.containsKey(role.getRoleId()))
            grantCacheMap.put(role.getRoleId(), new Cache<>(converter, paramsMapperService, Department.class));

        return grantCacheMap.get(role.getRoleId());
    }

}
