package metamodel.dao.impls;


import metamodel.dao.AttrsDao;
import metamodel.dao.mappers.AttrsMapper;
import metamodel.dao.models.Attrs;
import metamodel.dao.models.ObjectTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AttrsDaoImpl implements AttrsDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AttrsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Attrs> findAll() {
        String findAllSql = "SELECT * FROM ATTRS";
        return jdbcTemplate.query(findAllSql, new AttrsMapper());
    }

    @Override
    public List<Attrs> findByType(ObjectTypes type) {
        return findByType(type.getTypeId());
    }

    @Override
    public List<Attrs> findByType(Integer typeId) {
        String query = "SELECT * FROM ATTRS WHERE OBJECT_TYPE_ID = " + typeId;
        return jdbcTemplate.query(query, new AttrsMapper());
    }

    @Override
    public Attrs findById(Integer attrId) {
        String query = "SELECT * FROM ATTRS WHERE ATTR_ID = " + attrId;
        return jdbcTemplate.queryForObject(query, new AttrsMapper());
    }

    @Override
    public Attrs findByTypeAndName(Integer type, String name) {
        try {
            String query = "SELECT * FROM ATTRS WHERE OBJECT_TYPE_ID = " + type + " AND NAME = '" + name + "'";
            return jdbcTemplate.queryForObject(query, new AttrsMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return new Attrs();
    }

    @Override
    public Attrs findByName(String name) {
        try{
            String query = "SELECT * FROM ATTRS WHERE NAME = '" + name + "'";
            return jdbcTemplate.queryForObject(query, new AttrsMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return new Attrs();
    }

    @Override
    public void deleteById(Integer attrId) {
        Object[] params = { attrId };
        String deleteSql = "DELETE FROM ATTRS WHERE object_id = ?";
        jdbcTemplate.update(deleteSql, params);
    }

    @Override
    public void save(Attrs attr) {
        String saveSql = "INSERT INTO ATTRS(OBJECT_TYPE_ID, NAME) VALUES (?, ?)";
        Object[] params = {attr.getTypeId(), attr.getName()};
        jdbcTemplate.update(saveSql, params);
    }

    @Override
    public void update(Attrs attr) {
        String saveSql = "UPDATE ATTRS SET  OBJECT_TYPE_ID = ?, NAME = ? WHERE ATTR_ID = ?";
        Object[] params = {attr.getTypeId(), attr.getName(), attr.getAttrId()};
        jdbcTemplate.update(saveSql, params);
    }
}
