package metamodel.service.impls;

import metamodel.dao.*;
import metamodel.dao.models.Attrs;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Map<String, Attrs> getAttrsMap(Integer typeId, Integer grant) {
        List<Attrs> attrs = attrsDao.findByType(typeId);
        Map<String, Attrs> attrsMap = new HashMap<>(4);

        for(Attrs attr : attrs){
            attrsMap.put(attr.getName(), attr);
        }

        return attrsMap;
    }

    @Override
    public Map<String, Object> getParamsMap(Integer typeId, Integer grant) {
        List<Params> params = paramsDao.findByObjects(typeId);
        Map<String, Object> paramsMap = new HashMap<>();

        for (Params param : params) {

            if(grantsDao.findByAttrId(param.getAttrId()).getR() > grant)
                continue;

            Attrs attr = attrsDao.findById(param.getAttrId());

            if (paramsMap.containsKey(attr.getName())) {

                Object temp = paramsMap.get(attr.getName());

                List<Params> paramValues = null;

                if (temp instanceof List) {
                    paramValues = (List<Params>) temp;
                } else {
                    paramValues = new ArrayList<>();
                    paramValues.add((Params) temp);
                }

                paramValues.add(param);
                paramsMap.put(attr.getName(), paramValues);

                continue;
            }

            paramsMap.put(attr.getName(), param);
        }

        return paramsMap;
    }

    @Override
    public List<Objects> findObjectsByTypeName(String typeName, Integer grant) {
        ObjectTypes type = this.findObjectTypeByTypeName(typeName);
        List<Objects> objects = objectsDao.findByType(type.getTypeId());
        return checkObjectsGrants(objects, grant);
    }

    @Override
    public List<Objects> findObjectsByParentId(Integer parentId, Integer grant) {
        List<Objects> objects = objectsDao.findByParentId(parentId);
        return checkObjectsGrants(objects, grant);
    }

    private List<Objects> checkObjectsGrants(List<Objects> objects, Integer grant) {
        List<Objects> checkedObjects = new ArrayList<>();

        for(Objects object : objects)
            if(grantsDao.findByObjectId(object.getObjectId()).getR() <= grant)
                checkedObjects.add(object);

        return checkedObjects;
    }

    @Override
    public Objects findObjectByObjectId(Integer objectId, Integer grant) {
        if (grantsDao.findByObjectId(objectId).getR() > grant)
            return new Objects();

        return objectsDao.findById(objectId);
    }

    @Override
    public ObjectTypes findObjectTypeByTypeName(String typeName) {
        return objectTypesDao.findByName(typeName);
    }

    @Override
    public Integer saveObject(Objects object, Integer grant) {
        return objectsDao.save(object);
    }

    @Override
    public void saveParams(List<Params> params, Integer grant) {
        paramsDao.save(params);
    }

    @Override
    public void updateObject(Objects object, Integer grant) {
        if(grantsDao.findByObjectId(object.getObjectId()).getW() <= grant)
            objectsDao.update(object);
    }

    @Override
    public void updateParams(List<Params> params, Integer grant) {
        for (Params param : params)
            this.updateParams(param, grant);
    }

    @Override
    public void updateParams(Params param, Integer grant) {
        if(grantsDao.findByObjectId(param.getObjectId()).getW() <= grant)
            paramsDao.update(param);
    }

    @Override
    public void deleteParamsByObjectId(Integer objectId, Integer grant) {
        paramsDao.deleteByObjectId(objectId);
    }

    @Override
    public void deleteObjectById(Integer objectId, Integer grant) {
        objectsDao.deleteById(objectId);
    }
}
