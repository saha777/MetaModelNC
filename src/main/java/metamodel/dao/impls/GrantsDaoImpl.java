package metamodel.dao.impls;

import metamodel.dao.GrantsDao;
import metamodel.dao.mappers.AttrsMapper;
import metamodel.dao.mappers.GrantsMapper;
import metamodel.dao.mappers.RoleMapper;
import metamodel.dao.models.Grants;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrantsDaoImpl implements GrantsDao {

    private static Map<Integer, Grants> objGrantMap = new HashMap<>();
    private static Map<Integer, Grants> attrGrantMap = new HashMap<>();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GrantsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> findAll() {
        String query = "SELECT ROLE_ID, NAME, DESCRIPTION FROM ROLES";
        return jdbcTemplate.query(query, new RoleMapper());
    }

    @Override
    public Role findById(Integer roleId) {
        try {
            String query = "SELECT ROLE_ID, NAME, DESCRIPTION FROM ROLES WHERE ROLE_ID = " + roleId;
            return jdbcTemplate.queryForObject(query, new RoleMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role findByName(String roleName) {
        try {
            String query = "SELECT ROLE_ID, NAME, DESCRIPTION FROM ROLES WHERE NAME = '" + roleName + "'";
            return jdbcTemplate.queryForObject(query, new RoleMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean isReadableObj(Role role, Integer objId) {
        return isAvailableObj(role, objId, true);
    }

    @Override
    public Boolean isWritableObj(Role role, Integer objId) {
        return isAvailableObj(role, objId, false);
    }

    @Override
    public Boolean isReadableAttr(Role role, Integer attrId) {
        return isAvailableAttr(role, attrId, true);
    }

    @Override
    public Boolean isWritableAttr(Role role, Integer attrId) {
        return isAvailableAttr(role, attrId, false);
    }

    private Boolean isAvailableAttr(Role role, Integer attrId, boolean read) {
        //Boolean objectGrant = isAvailable(role, objId, read);

        String attrQuery = "SELECT R, W " +
                "FROM GRANTS " +
                "WHERE ROWNUM <= 1 " +
                "AND ROLE_ID = " + role.getRoleId() + " " +
                "AND ATTR_ID = " + attrId;
        Grants attrGrant;

        try {
            attrGrant = jdbcTemplate.queryForObject(attrQuery, new GrantsMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        if(read) return attrGrant.getR();
        return attrGrant.getW();
//        if(read) return attrGrant.getR() && objectGrant;
//        return attrGrant.getW() && objectGrant;
    }

    private Boolean isAvailableObj(Role role, Integer objId, Boolean read) {
        String typeQuery = "SELECT g.R, g.W " +
                "FROM GRANTS g INNER JOIN (" +
                    "SELECT OBJECT_TYPE_ID, LEVEL RANG " +
                    "FROM OBJECT_TYPES " +
                    "START WITH OBJECT_TYPES.OBJECT_TYPE_ID = (" +
                        "SELECT OBJECT_TYPE_ID " +
                        "FROM OBJECTS " +
                        "WHERE OBJECT_ID = " + objId + ")" +
                    "CONNECT BY PRIOR PAR_TYPE_ID = OBJECT_TYPE_ID) t " +
                "ON g.OBJECT_TYPE_ID = t.OBJECT_TYPE_ID " +
                "WHERE g.ROLE_ID = " + role.getRoleId() +
                " ORDER BY t.RANG";
        Grants typeGrant;

        try {
            typeGrant = jdbcTemplate.query(typeQuery, new GrantsMapper()).get(0);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

        String objQuery = "SELECT R, W " +
                "FROM GRANTS g INNER JOIN (" +
                "SELECT OBJECTS.OBJECT_ID, LEVEL RANG FROM OBJECTS " +
                "START WITH OBJECTS.OBJECT_ID = " + objId + " " +
                "CONNECT BY PRIOR PAR_OBJ_ID = OBJECTS.OBJECT_ID) ob " +
                "ON g.OBJECT_ID = ob.OBJECT_ID " +
                "WHERE ROLE_ID = " + role.getRoleId() +
                " ORDER BY ob.RANG";
        Grants objGrant;

        try {
            objGrant = jdbcTemplate.query(objQuery, new GrantsMapper()).get(0);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

        if(read) return objGrant.getR() && typeGrant.getR();
        return objGrant.getW() && typeGrant.getW();
    }
}
