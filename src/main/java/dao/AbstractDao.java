package dao;

import dao.cache.Cache;
import entities.AbstractObject;
import entities.mappers.MetaObject;
import entities.mappers.ObjectMapper;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AbstractObject> implements Dao<T> {
    protected ObjectTypes objectType;

    protected Cache<T> cache;
    protected ObjectMapper<T> mapper;

    protected MetaModelService metaModelService;
    protected AttrsMapperService attrsMapperService;
    protected ParamsMapperService paramsMapperService;

    public AbstractDao(ObjectTypes objectType,
                       ObjectMapper<T> mapper,
                       MetaModelService metaModelService,
                       AttrsMapperService attrsMapperService,
                       ParamsMapperService paramsMapperService) {
        this.objectType = objectType;
        this.mapper = mapper;
        this.metaModelService = metaModelService;
        this.attrsMapperService = attrsMapperService;
        this.paramsMapperService = paramsMapperService;
    }

    @Override
    public List<T> findAll() {
        List<Objects> objects = metaModelService.findObjectsByTypeId(objectType.getTypeId());
        return listSelection(objects);
    }

    @Override
    public List<T> findByParentId(Integer departmentId) {
        List<Objects> objects = metaModelService.findObjectsByParentId(departmentId);
        return listSelection(objects);
    }

    private List<T> listSelection(List<Objects> objects) {
        List<T> templates = new ArrayList<>();

        for (Objects object : objects) {
            T temp = checkInCache(object);
            if(temp == null) continue;
            templates.add(temp);
        }

        return templates;
    }

    @Override
    public T findById(Integer id) {
        Objects object = metaModelService.findObjectByObjectId(id);

        if (object == null) return null;

        return this.checkInCache(object);
    }

    @Override
    public void update(T temp) {
        MetaObject metaObject = temp.getMetaObject();

        if (!metaObject.isChanged()) return;

        Objects object = metaObject.getObject();
        metaModelService.updateObject(object);

        List<Params> params = new ArrayList<>(metaObject.getParamsMap().values());
        metaModelService.updateParams(params);

        getCache().delete(object.getObjectId());
    }

    private T checkInCache(Objects object) {
        if (object == null) return null;

        return getCache().getByObject(object);
    }

    private Cache<T> getCache() {
        if (cache == null)
            cache = new Cache<>(mapper, paramsMapperService);
        return cache;
    }
}
