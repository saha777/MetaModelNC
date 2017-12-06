package entities;

import entities.anotations.EntityType;
import entities.anotations.Name;
import entities.anotations.ObjectId;
import entities.anotations.ParentId;

@EntityType(type = ObjectType.LOCATION)
public class Location {

    @ObjectId
    private Integer objectId;

    @ParentId
    private Integer parentId;

    @Name
    private String name;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "objectId=" + objectId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                '}';
    }
}
