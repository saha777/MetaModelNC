package metamodel.dao.impls;

import metamodel.dao.ObjectsDao;
import metamodel.dao.mappers.ObjectsMapper;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ObjectsDaoImpl implements ObjectsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ObjectsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Objects> findAll() {
        String findAllSql = "SELECT * FROM objects";
        return jdbcTemplate.query(findAllSql, new ObjectsMapper());
    }

    @Override
    public List<Objects> findByParentId(Integer parentId) {
        String query = "SELECT * FROM OBJECTS WHERE PAR_OBJ_ID = " + parentId;
        return jdbcTemplate.query(query, new ObjectsMapper());
    }

    @Override
    public List<Objects> findByType(ObjectTypes type) {
        return findByType(type.getTypeId());
    }

    @Override
    public List<Objects> findByType(Integer typeId) {
        String query = "SELECT * FROM OBJECTS WHERE OBJECT_TYPE_ID = " + typeId;
        return jdbcTemplate.query(query, new ObjectsMapper());
    }

    @Override
    public Objects findById(Integer objectId) {
        try {
            String query = "SELECT * FROM OBJECTS WHERE OBJECT_ID = " + objectId;
            return jdbcTemplate.queryForObject(query, new ObjectsMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return new Objects();
    }

    @Override
    public Objects findByName(String name) {
        try {
            String query = "SELECT * FROM OBJECTS WHERE NAME = '" + name + "'";
            return jdbcTemplate.queryForObject(query, new ObjectsMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return new Objects();
    }

    @Override
    public void deleteById(Integer objectId) {
        Object[] params = { objectId };
        String deleteSql = "DELETE FROM OBJECTS WHERE object_id = ?";
        jdbcTemplate.update(deleteSql, params);
    }

    @Override
    public Integer save(Objects object) {
        String saveSql = "INSERT INTO OBJECTS(PAR_OBJ_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) VALUES (?, ?, ?, ?)";

        if(object.getParentObjectId() != null && object.getParentObjectId() == 0)
            object.setParentObjectId(null);

        Object[] params = {object.getParentObjectId(), object.getType(), object.getName(), object.getDescription()};
        jdbcTemplate.update(saveSql, params);

        String savedIdSql = "SELECT MAX(object_id) FROM objects";
        return jdbcTemplate.queryForObject(savedIdSql, Integer.class);
    }

    @Override
    public void update(Objects object) {
        String saveSql = "UPDATE OBJECTS SET  PAR_OBJ_ID = ?, OBJECT_TYPE_ID = ?, NAME = ?, DESCRIPTION = ? WHERE OBJECT_ID = ?";

        if(object.getParentObjectId() == 0)
            object.setParentObjectId(null);

        Object[] params = {object.getParentObjectId(), object.getType(), object.getName(), object.getDescription(), object.getObjectId()};
        jdbcTemplate.update(saveSql, params);
    }
}
