package metamodel.dao.models;

public class Grants {
    private Integer objectId;
    private Integer attrId;
    private Integer r;
    private Integer w;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    @Override
    public String toString() {
        return "Grants{" +
                "objectId=" + objectId +
                ", attrId=" + attrId +
                ", r=" + r +
                ", w=" + w +
                '}';
    }
}
