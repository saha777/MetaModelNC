package metamodel.dao.models;

import java.util.Date;

public class Params {
    private Integer objectId;
    private Integer relationId;
    private Integer attrId;
    private String description;
    private String textValue;
    private Integer numberValue;
    private Date dateValue;
    private String attrName;

    public Params() {
    }

    public Integer getObjectId() {
        return objectId;
    }

    public Params setObjectId(Integer objectId) {
        this.objectId = objectId;
        return this;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public Params setRelationId(Integer relationId) {
        this.relationId = relationId;
        return this;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public Params setAttrId(Integer attrId) {
        this.attrId = attrId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Params setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTextValue() {
        return textValue;
    }

    public Params setTextValue(String textValue) {
        this.textValue = textValue;
        return this;
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public Params setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
        return this;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public Params setDateValue(Date dateValue) {
        this.dateValue = dateValue;
        return this;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    @Override
    public String toString() {
        return "Params{" +
                "object=" + objectId +
                ", relationId=" + relationId +
                ", attr=" + attrId +
                ", description='" + description + '\'' +
                ", textValue='" + textValue + '\'' +
                ", numberValue=" + numberValue +
                ", dateValue=" + dateValue +
                '}';
    }
}
