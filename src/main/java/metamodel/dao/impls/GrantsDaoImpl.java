package metamodel.dao.impls;

import metamodel.dao.GrantsDao;
import metamodel.dao.mappers.GrantsMapper;
import metamodel.dao.models.Grants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
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
    public Grants findByObjectId(Integer objectId) {
        String query = "SELECT * FROM GRANTS WHERE OBJECT_ID = " + objectId;

        if(objGrantMap.containsKey(objectId))
            return objGrantMap.get(objectId);

        Grants grant = getGrant(query);
        objGrantMap.put(objectId, grant);

        return grant;
    }

    @Override
    public Grants findByAttrId(Integer attrId) {
        String query = "SELECT * FROM GRANTS WHERE ATTR_ID = " + attrId;

        if(attrGrantMap.containsKey(attrId))
            return attrGrantMap.get(attrId);

        Grants grant = getGrant(query);
        attrGrantMap.put(attrId, grant);

        return grant;
    }

    public Grants getGrant(String query) {
        Grants grants;
        try {
            grants = jdbcTemplate.queryForObject(query, new GrantsMapper());
        } catch (EmptyResultDataAccessException e) {
            //e.printStackTrace();
            grants = new Grants();
            grants.setR(1);
            grants.setW(1);
        }
        return grants;
    }
}
