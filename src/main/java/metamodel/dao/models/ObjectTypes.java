package metamodel.dao.models;


public class ObjectTypes {
    private Integer typeId;
    private Integer parentId;
    private String name;
    private String description;

    public ObjectTypes() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ObjectTypes{" +
                "typeId=" + typeId +
                ", parentTypeId=" + parentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
