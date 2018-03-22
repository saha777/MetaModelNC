package service;

import dao.Dao;
import entities.AbstractObject;
import entities.mappers.MetaObject;
import entities.mappers.ObjectMapper;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;
import metamodel.dao.models.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractService<T extends AbstractObject> implements Service<T> {

    protected Dao<T> dao;
    protected ObjectMapper<T> mapper;

    protected GrantsDao grantsDao;

    public AbstractService(Dao<T> dao, ObjectMapper<T> mapper, GrantsDao grantsDao) {
        this.dao = dao;
        this.mapper = mapper;
        this.grantsDao = grantsDao;
    }

    @Override
    public List<T> getAll(Role role) {
        List<T> temps = dao.findAll();
        List<T> checkedTemps = new ArrayList<>();
        for (T temp : temps) {
            T checkedTemp = checkPermission(role, temp);
            if (checkedTemp != null)
                checkedTemps.add(checkedTemp);
        }
        return checkedTemps;
    }

    @Override
    public List<T> getByParentId(Role role, int parentId) {
        List<T> temps = dao.findByParentId(parentId);
        List<T> checkedTemps = new ArrayList<>();
        for (T temp : temps) {
            T checkedTemp = checkPermission(role, temp);
            if (checkedTemp != null)
                checkedTemps.add(checkedTemp);
        }
        return checkedTemps;
    }

    @Override
    public T get(Role role, int tempId) {
        return checkPermission(role, dao.findById(tempId));
    }

    protected T checkPermission(Role role, T temp) {
        MetaObject metaObject = temp.getMetaObject();
        return checkReadPermission(role, metaObject);
    }

    @Override
    public T getForUpdate(Role role, int tempId) {
        T employee = dao.findById(tempId);
        MetaObject metaObject = employee.getMetaObject();
        return checkWritePermission(role, metaObject);
    }

    @Override
    public void update(Role role, int tempId, Map<String, String[]> params) {
        T temp = mapper.updateObject(dao.findById(tempId), params);
        dao.update(temp);
    }

    protected T checkReadPermission(Role role, MetaObject metaObject) {
        Objects object = metaObject.getObject();
        if(grantsDao.isReadableObj(role, object.getObjectId())) {
            Map<String, Params> paramsMap = new HashMap<>();
            for (Params param : metaObject.getParamsMap().values()) {
                Boolean permission = grantsDao.isReadableAttr(role, param.getAttrId());
                if (permission == null) permission = true;
                if (permission) paramsMap.put(param.getAttrName(), param);
                else paramsMap.put(param.getAttrName(), new Params());
            }
            return mapper.mapObject(object, paramsMap);
        }
        return null;
    }

    protected T checkWritePermission(Role role, MetaObject metaObject) {
        Objects object = metaObject.getObject();
        if(grantsDao.isWritableObj(role, object.getObjectId())) {
            Map<String, Params> paramsMap = new HashMap<>();
            for (Params param : metaObject.getParamsMap().values()) {
                Boolean permission = grantsDao.isWritableAttr(role, param.getAttrId());
                if (permission == null) permission = true;
                if (permission) paramsMap.put(param.getAttrName(), param);
                else paramsMap.put(param.getAttrName(), new Params());
            }
            return mapper.mapObject(object, paramsMap);
        }
        return null;
    }
}
