package metamodel.dao;

import metamodel.dao.models.Role;

import java.util.List;

public interface GrantsDao {
    List<Role> findAll();
    Role findById(Integer roleId);
    Role findByName(String roleName);
    Boolean isReadableObj(Role role, Integer objId);
    Boolean isWritableObj(Role role, Integer objId);
    Boolean isReadableAttr(Role role, Integer objId, Integer attrId);
    Boolean isWritableAttr(Role role, Integer objId, Integer attrId);
}
