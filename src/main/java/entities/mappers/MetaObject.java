package entities.mappers;

import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.Date;
import java.util.Map;

public class MetaObject {
    private Objects object;
    private Map<String, Params> paramsMap;
    private boolean isChanged = false;

    public MetaObject(Objects object, Map<String, Params> paramsMap) {
        this.object = object;
        this.paramsMap = paramsMap;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged() {
        isChanged = true;
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
        setChanged();
    }

    public Integer getParentObjectId() {
        return object.getParentObjectId();
    }

    public void setParentObjectId(Integer parentObjectId) {
        this.object.setParentObjectId(parentObjectId);
        setChanged();
    }

    public String getName() {
        return object.getName();
    }

    public void setName(String name) {
        this.object.setName(name);
        setChanged();
    }

    public Params getAttr(String attrName) {
        return paramsMap.get(attrName);
    }

    public void setNumberAttr(String attrName, Integer value) {
        paramsMap.put(attrName,
                paramsMap.get(attrName).setNumberValue(value));
        setChanged();
    }

    public void setTextAttr(String attrName, String value) {
        paramsMap.put(attrName,
                paramsMap.get(attrName).setTextValue(value));
        setChanged();
    }

    public void setDateAttr(String attrName, Date value) {
        paramsMap.put(attrName,
                paramsMap.get(attrName).setDateValue(value));
        setChanged();
    }
}
