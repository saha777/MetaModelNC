package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.mappers.MetaObject;

public class AbstractObject {
    @JsonIgnore
    protected transient MetaObject metaObject;

    public MetaObject getMetaObject() {
        return metaObject;
    }

    public void setMetaObject(MetaObject metaObject) {
        this.metaObject = metaObject;
    }
}
