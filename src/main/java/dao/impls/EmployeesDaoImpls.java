package dao.impls;

import dao.AbstractDao;
import dao.EmployeesDao;
import dao.cache.Cache;
import dao.converter.impls.ConverterImpl;
import entities.ObjectType;
import dao.converter.Converter;
import metamodel.dao.models.*;
import entities.Employee;
import metamodel.dao.models.Objects;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeesDaoImpls extends AbstractDao<Employee> implements EmployeesDao{
    private static Map<Integer, Cache<Employee>> grantCacheMap = new ConcurrentHashMap<>(10);

    @Autowired
    public EmployeesDaoImpls(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService) {
        super(metaModelService, attrsMapperService, paramsMapperService, ObjectType.EMPLOYEE);
    }

    @Override
    protected Integer getObjId(Employee temp) {
        return temp.getObjectId();
    }

    @Override
    protected Employee insertIdIntoTemplate(Employee temp, Integer id) {
        temp.setObjectId(id);
        return temp;
    }

    @Override
    protected Employee checkInCache(Objects object, Role role) {
        if (object == null) return null;

        Cache<Employee> cache = getCache(role);
        return cache.getByObject(object, role);
    }

    @Override
    protected void deleteFromCache(Integer empId) {
        for (Integer key : grantCacheMap.keySet()) {
            Cache<Employee> cache = grantCacheMap.get(key);
            cache.delete(empId);
        }
    }

    private Cache<Employee> getCache(Role role) {
        if (!grantCacheMap.containsKey(role.getRoleId()))
            grantCacheMap.put(role.getRoleId(), new Cache<>(converter, paramsMapperService, Employee.class));

        return grantCacheMap.get(role.getRoleId());
    }
}
