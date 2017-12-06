package metamodel.dao;

import metamodel.dao.models.Grants;

public interface GrantsDao {
    Grants findByObjectId(Integer objectId);
    Grants findByAttrId(Integer attrId);
}
