package metamodel.dao.impls;


import metamodel.dao.ParamsDao;
import metamodel.dao.mappers.ParamsMapper;
import metamodel.dao.models.Attrs;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ParamsDaoImpl implements ParamsDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParamsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Params> findAll() {
        String query = "SELECT * FROM PARAMS";
        return jdbcTemplate.query(query, new ParamsMapper());
    }

    @Override
    public List<Params> findByObjects(Objects object) {
        return findByObjects(object.getObjectId());
    }

    @Override
    public List<Params> findByObjects(Integer objectId) {
        String query = "SELECT * FROM PARAMS WHERE OBJECT_ID = '" + objectId + "'";
        return jdbcTemplate.query(query, new ParamsMapper());
    }

    @Override
    public List<Params> findByObjectsAndAttrs(Objects object, Attrs attr) {
        return findByObjectsAndAttrs(object.getObjectId(), attr.getAttrId());
    }

    @Override
    public List<Params> findByObjectsAndAttrs(Integer objectId, Integer attrId) {
        String query = "SELECT * FROM PARAMS WHERE OBJECT_ID = " + objectId + " AND ATTR_ID = " + attrId;
        return jdbcTemplate.query(query, new ParamsMapper());
    }

    @Override
    public Params findOneByObjectsAndAttrs(Integer objectId, Integer attrId) {
        try {
            String query = "SELECT * FROM PARAMS WHERE OBJECT_ID = " + objectId + " AND ATTR_ID = " + attrId;
            return jdbcTemplate.queryForObject(query, new ParamsMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return new Params();
    }

    @Override
    public void deleteByObjectId(Integer objectId) {
        Object[] params = { objectId };
        String deleteSql = "DELETE FROM PARAMS WHERE object_id = ?";
        jdbcTemplate.update(deleteSql, params);
    }

    @Override
    public void save(List<Params> params) {
        for(Params param : params)
            save(param);
    }

    @Override
    public void save(Params object, Integer objectId) {
        object.setObjectId(objectId);
        save(object);
    }

    @Override
    public void save(Params object) {
        String saveSql = "INSERT INTO PARAMS(OBJECT_ID, RELATION_ID, ATTR_ID, DESCRIPTION, TEXT_VALUE, NUMBER_VALUE, DATE_VALUE) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {object.getObjectId(), object.getRelationId(), object.getAttrId(), object.getDescription(), object.getTextValue(), object.getNumberValue(), object.getDescription()};
        jdbcTemplate.update(saveSql, params);
    }

    @Override
    public void update(Params object) {
        String saveSql = "UPDATE PARAMS SET  RELATION_ID = ?, ATTR_ID = ?, DESCRIPTION = ?, TEXT_VALUE = ?, NUMBER_VALUE = ?, DATE_VALUE = ?  WHERE OBJECT_ID = ?";
        Object[] params = {object.getRelationId(), object.getAttrId(), object.getDescription(), object.getTextValue(), object.getNumberValue(), object.getDescription(), object.getObjectId()};
        jdbcTemplate.update(saveSql, params);
    }

    @Override
    public void update(List<Params> params) {
        for (Params param : params)
            update(param);
    }
}
