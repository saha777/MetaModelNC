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
    private GrantsDao grantsDao;

    @Autowired
    public MetaModelServiceImpl(
            AttrsDao attrsDao,
            ParamsDao paramsDao,
            ObjectTypesDao objectTypesDao,
            ObjectsDao objectsDao,
            GrantsDao grantsDao) {
        this.attrsDao = attrsDao;
        this.paramsDao = paramsDao;
        this.objectTypesDao = objectTypesDao;
        this.objectsDao = objectsDao;
        this.grantsDao = grantsDao;
    }

    @Override
    public List<Objects> findObjectsByTypeId(Integer typeId, Role role) {
        List<Objects> objects = objectsDao.findByType(typeId);
        return checkObjectsGrants(objects, role);
    }

    @Override
    public List<Objects> findObjectsByTypeName(String typeName, Role role) {
        ObjectTypes type = findObjectTypeByTypeName(typeName);
        List<Objects> objects = objectsDao.findByType(type.getTypeId());
        return checkObjectsGrants(objects, role);
    }

    @Override
    public List<Objects> findObjectsByParentId(Integer parentId, Role role) {
        List<Objects> objects = objectsDao.findByParentId(parentId);
        return checkObjectsGrants(objects, role);
    }

    private List<Objects> checkObjectsGrants(List<Objects> objects, Role role) {
        List<Objects> checkedObjects = new ArrayList<>();

        for(Objects object : objects)
            if(grantsDao.isReadableObj(role, object.getObjectId()))
                checkedObjects.add(object);

        return checkedObjects;
    }

    @Override
    public Objects findObjectByObjectId(Integer objectId, Role role) {
        if (!grantsDao.isReadableObj(role, objectId)) return null;

        return objectsDao.findById(objectId);
    }

    @Override
    public ObjectTypes findObjectTypeByTypeName(String typeName) {
        return objectTypesDao.findByName(typeName);
    }

    @Override
    public Integer saveObject(Objects object, Role role) {
        return objectsDao.save(object);
    }

    @Override
    public void saveParams(List<Params> params, Role role) {
        paramsDao.save(params);
    }

    @Override
    public void updateObject(Objects object, Role role) {
        if(grantsDao.isWritableObj(role, object.getObjectId()))
            objectsDao.update(object);
    }

    @Override
    public void updateParams(List<Params> params, Role role) {
        for (Params param : params)
                this.updateParams(param, role);
    }

    @Override
    public void updateParams(Params param, Role role) {
        if(grantsDao.isWritableAttr(role, param.getObjectId(), param.getAttrId())) paramsDao.update(param);
    }

    @Override
    public void deleteParamsByObjectId(Integer objectId, Role role) {
        paramsDao.deleteByObjectId(objectId);
    }

    @Override
    public void deleteObjectById(Integer objectId, Role role) {
        objectsDao.deleteById(objectId);
    }
}
