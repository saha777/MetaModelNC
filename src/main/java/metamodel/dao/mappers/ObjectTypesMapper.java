package metamodel.dao.mappers;

import metamodel.dao.models.ObjectTypes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectTypesMapper implements RowMapper<ObjectTypes> {
    @Override
    public ObjectTypes mapRow(ResultSet resultSet, int i) throws SQLException {
        ObjectTypes type = new ObjectTypes();
        type.setTypeId(resultSet.getInt("object_type_id"));
        type.setParentId(resultSet.getInt("par_type_id"));
        type.setName(resultSet.getString("name"));
        type.setDescription(resultSet.getString("description"));
        return type;
    }
}
