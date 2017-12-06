package metamodel.dao.models;

public class Attrs {

    private Integer attrId;
    private Integer typeId;
    private String name;

    public Attrs() {
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Attrs{" +
                "attrId=" + attrId +
                ", type=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }
}
