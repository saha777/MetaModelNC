package dao;

import dao.cache.Cache;
import dao.converter.Converter;
import dao.converter.impls.ConverterImpl;
import entities.ObjectType;
import metamodel.dao.models.*;
import metamodel.dao.models.Objects;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public abstract class AbstractDao<T> {
    protected ObjectTypes objectType;

    protected MetaModelService metaModelService;
    protected AttrsMapperService attrsMapperService;
    protected ParamsMapperService paramsMapperService;
    protected Converter<T> converter;

    @Autowired
    public AbstractDao(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService,
            ObjectType type) {
        this.metaModelService = metaModelService;
        this.attrsMapperService = attrsMapperService;
        this.paramsMapperService = paramsMapperService;
        this.converter = new ConverterImpl<>();
        objectType = metaModelService.findObjectTypeByTypeName(type.toString());
    }

    public List<T> findAll(Role role) {
        List<Objects> objects = metaModelService.findObjectsByTypeId(objectType.getTypeId(), role);
        return this.listSelection(objects, role);
    }

    public List<T> findByParentId(Integer parentId, Role role) {
        List<Objects> objects = metaModelService.findObjectsByParentId(parentId, role);
        return this.listSelection(objects, role);
    }

    private List<T> listSelection(List<Objects> objects, Role role) {
        List<T> templates = new ArrayList<>();

        for (Objects object : objects) {
            T temp = checkInCache(object, role);
            if(temp == null) continue;
            templates.add(temp);
        }

        return templates;
    }

    public T findById(Integer id, Role role) {
        Objects object = metaModelService.findObjectByObjectId(id, role);

        if (object == null) return null;

        return this.checkInCache(object, role);
    }

    public Integer save(T temp, Role role) {
        Integer objectTypeId = objectType.getTypeId();

        Objects object = converter.convertTemplateToObjects(temp, objectTypeId);
        Integer objectId = metaModelService.saveObject(object, role);

        temp = insertIdIntoTemplate(temp, objectId);

        Map<String, Attrs> attrsMap = attrsMapperService.getAttrsMap(objectTypeId);
        List<Params> params = converter.convertTemplateToParams(temp, objectTypeId, attrsMap);
        metaModelService.saveParams(params, role);

        return objectId;
    }

    public void update(T temp, Role role) {
        Integer objectTypeId = objectType.getTypeId();

        Objects object = converter.convertTemplateToObjects(temp, objectTypeId);
        metaModelService.updateObject(object, role);

        Map<String, Attrs> attrsMap = attrsMapperService.getAttrsMap(objectTypeId);
        List<Params> params = converter.convertTemplateToParams(temp, objectTypeId, attrsMap);
        metaModelService.updateParams(params, role);

        this.deleteFromCache(getObjId(temp));
    }

    public void delete(Integer objId, Role role) {
        metaModelService.deleteParamsByObjectId(objId, role);
        metaModelService.deleteObjectById(objId, role);

        this.deleteFromCache(objId);
    }

    protected abstract Integer getObjId(T temp);
    protected abstract T insertIdIntoTemplate(T temp, Integer id);
    protected abstract T checkInCache(Objects object, Role role);
    protected abstract void deleteFromCache(Integer objId);
}
