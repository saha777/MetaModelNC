package metamodel.dao.impls;

import metamodel.dao.ObjectTypesDao;
import metamodel.dao.mappers.ObjectTypesMapper;
import metamodel.dao.models.ObjectTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ObjectTypesDaoImpl implements ObjectTypesDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ObjectTypesDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ObjectTypes> findAll() {
        String query = "SELECT * FROM OBJECT_TYPES";
        return jdbcTemplate.query(query, new ObjectTypesMapper());
    }

    public ObjectTypes findByName(String name) {
        try {
            String query = "SELECT * FROM OBJECT_TYPES WHERE NAME = '" + name + "'";

            jdbcTemplate.queryForMap(query);
            return jdbcTemplate.queryForObject(query, new ObjectTypesMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return new ObjectTypes();
    }

    @Override
    public ObjectTypes findById(Integer id) {
        try {
            String query = "SELECT * FROM OBJECT_TYPES WHERE OBJECT_TYPE_ID = " + id;
            return jdbcTemplate.queryForObject(query, new ObjectTypesMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return new ObjectTypes();
    }

    public void deleteById(Integer id) {
        Object[] params = { id };
        String deleteSql = "DELETE FROM OBJECT_TYPES WHERE object_type_id = ?";
        jdbcTemplate.update(deleteSql, params);
    }

    public void deleteByName(String name) {
        Object[] params = { name };
        String deleteSql = "DELETE FROM OBJECT_TYPES WHERE name = ?";
        jdbcTemplate.update(deleteSql, params);
    }

    public void save(ObjectTypes type) {
        String saveSql = "INSERT INTO OBJECT_TYPES(par_type_id, name, description) VALUES (?, ?, ?)";
        Object[] params = {type.getParentId(), type.getName(), type.getDescription()};
        jdbcTemplate.update(saveSql, params);
    }

    public void update(ObjectTypes type) {
        String saveSql = "UPDATE OBJECT_TYPES SET  par_type_id = ?, name = ?, description = ? WHERE object_type_id = ?";
        Object[] params = {type.getParentId(), type.getName(), type.getDescription(), type.getTypeId()};
        jdbcTemplate.update(saveSql, params);
    }
}
