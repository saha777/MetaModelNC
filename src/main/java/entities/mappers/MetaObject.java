package entities.mappers;

import entities.anotations.Type;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.Date;
import java.util.Map;

public class MetaObject {
    private Objects object;
    private Map<String, Params> paramsMap;

    public MetaObject(Objects object, Map<String, Params> paramsMap) {
        this.object = object;
        this.paramsMap = paramsMap;
    }

    public Objects getObject() {
        return object;
    }

    public void setObject(Objects object) {
        this.object = object;
    }

    public Map<String, Params> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, Params> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public Integer getObjectId() {
        return object.getObjectId();
    }

    public void setObjectId(Integer objectId) {
        this.object.setObjectId(objectId);
    }

    public Integer getParentObjectId() {
        return object.getParentObjectId();
    }

    public void setParentObjectId(Integer parentObjectId) {
        this.object.setParentObjectId(parentObjectId);
    }

    public String getName() {
        return object.getName();
    }

    public void setName(String name) {
        this.object.setName(name);
    }

    public Params getAttr(String attrName) {
        return paramsMap.get(attrName);
    }

    public void setNumberAttr(String attrName, Integer value) {
        Params param = paramsMap.get(attrName);
        param.setNumberValue(value);
        paramsMap.put(param.getAttrName(), param);
    }

    public void setTextAttr(String attrName, String value) {
        Params param = paramsMap.get(attrName);
        param.setTextValue(value);
        paramsMap.put(param.getAttrName(), param);
    }

    public void setDateAttr(String attrName, Date value) {
        Params param = paramsMap.get(attrName);
        param.setDateValue(value);
        paramsMap.put(param.getAttrName(), param);
    }

}
