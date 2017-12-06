package metamodel.dao.mappers;

import metamodel.dao.models.Attrs;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttrsMapper implements RowMapper<Attrs> {
    @Override
    public Attrs mapRow(ResultSet resultSet, int i) throws SQLException {
        Attrs attrs = new Attrs();
        attrs.setAttrId(resultSet.getInt("attr_id"));
        attrs.setTypeId(resultSet.getInt("object_type_id"));
        attrs.setName(resultSet.getString("name"));
        return attrs;
    }
}
