package entities;

import entities.anotations.EntityType;
import entities.anotations.Name;
import entities.anotations.ObjectId;
import entities.anotations.ParentId;

@EntityType(type = ObjectType.OFFICE)
public class Office {

    @ObjectId
    private Integer objectId;

    @ParentId
    private Integer locationId;

    @Name
    private String name;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Office{" +
                "objectId=" + objectId +
                ", locationId=" + locationId +
                ", name='" + name + '\'' +
                '}';
    }
}
