package entities;

import entities.anotations.EntityType;
import entities.anotations.Name;
import entities.anotations.ObjectId;
import entities.anotations.ParentId;

@EntityType(type = ObjectType.DEPARTMENT)
public class Department {
    @ObjectId
    private Integer objectId;

    @ParentId
    private Integer officeId;

    @Name
    private String name;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "objectId=" + objectId +
                ", officeId=" + officeId +
                ", name='" + name + '\'' +
                '}';
    }
}
