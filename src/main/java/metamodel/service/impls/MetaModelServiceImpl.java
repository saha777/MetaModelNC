package metamodel.service.impls;

import metamodel.dao.*;
import metamodel.dao.models.*;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaModelServiceImpl implements MetaModelService {
    private AttrsDao attrsDao;
    private ParamsDao paramsDao;
    private ObjectTypesDao objectTypesDao;
    private ObjectsDao objectsDao;

    @Autowired
    public MetaModelServiceImpl(
            AttrsDao attrsDao,
            ParamsDao paramsDao,
            ObjectTypesDao objectTypesDao,
            ObjectsDao objectsDao) {
        this.attrsDao = attrsDao;
        this.paramsDao = paramsDao;
        this.objectTypesDao = objectTypesDao;
        this.objectsDao = objectsDao;
    }

    @Override
    public List<Objects> findObjectsByTypeId(Integer typeId) {
        return objectsDao.findByType(typeId);
    }

    @Override
    public List<Objects> findObjectsByTypeName(String typeName) {
        ObjectTypes type = findObjectTypeByTypeName(typeName);
        return objectsDao.findByType(type.getTypeId());
    }

    @Override
    public List<Objects> findObjectsByParentId(Integer parentId) {
        return objectsDao.findByParentId(parentId);
    }

    @Override
    public Objects findObjectByObjectId(Integer objectId) {
        return objectsDao.findById(objectId);
    }

    @Override
    public ObjectTypes findObjectTypeByTypeName(String typeName) {
        return objectTypesDao.findByName(typeName);
    }

    @Override
    public Integer saveObject(Objects object) {
        return objectsDao.save(object);
    }

    @Override
    public void saveParams(List<Params> params) {
        paramsDao.save(params);
    }

    @Override
    public void updateObject(Objects object) {
        objectsDao.update(object);
    }

    @Override
    public void updateParams(List<Params> params) {
        for (Params param : params)
            this.updateParams(param);
    }

    @Override
    public void updateParams(Params param) {
        paramsDao.update(param);
    }

    @Override
    public void deleteParamsByObjectId(Integer objectId) {
        paramsDao.deleteByObjectId(objectId);
    }

    @Override
    public void deleteObjectById(Integer objectId) {
        objectsDao.deleteById(objectId);
    }
}
