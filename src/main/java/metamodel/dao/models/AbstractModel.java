package metamodel.dao.models;

import java.util.Map;

public class AbstractModel {
    private Objects object;
    private Map<String, Params> paramsMap;

    public AbstractModel(Objects object, Map<String, Params> paramsMap) {
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
}
