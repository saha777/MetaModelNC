package metamodel.dao.models;


public class Objects {
    private Integer objectId;
    private Integer parentObjectId;
    private Integer typeId;
    private String name;
    private String description;

    public Objects() {
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(Integer parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    public Integer getType() {
        return typeId;
    }

    public void setType(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Objects{" +
                "objectId=" + objectId +
                ", parentObjectId=" + parentObjectId +
                ", type=" + typeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
