package entities;

public class Department extends AbstractObject {
    private Integer objectId;
    private Integer parentId;
    private String name;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
        this.metaObject.setObjectId(objectId);
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
        this.metaObject.setParentObjectId(parentId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.metaObject.setName(name);
    }

    @Override
    public String toString() {
        return "Department{" +
                "objectId=" + objectId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                '}';
    }
}
