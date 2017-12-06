package metamodel.dao.mappers;

import metamodel.dao.models.Objects;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectsMapper implements RowMapper<Objects> {
    @Override
    public Objects mapRow(ResultSet resultSet, int i) throws SQLException {
        Objects object = new Objects();
        object.setObjectId(resultSet.getInt("object_id"));
        object.setName(resultSet.getString("name"));
        object.setDescription(resultSet.getString("description"));
        object.setType(resultSet.getInt("object_type_id"));
        object.setParentObjectId(resultSet.getInt("par_obj_id"));
        return object;
    }
}