package metamodel.dao.mappers;


import metamodel.dao.models.Params;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParamsMapper implements RowMapper<Params> {
    @Override
    public Params mapRow(ResultSet resultSet, int i) throws SQLException {
        Params params = new Params();
        params.setObjectId(resultSet.getInt("object_id"));
        params.setRelationId(resultSet.getInt("relation_id"));
        params.setAttrId(resultSet.getInt("attr_id"));
        params.setDescription(resultSet.getString("description"));
        params.setTextValue(resultSet.getString("text_value"));
        params.setNumberValue(resultSet.getInt("number_value"));
        params.setDateValue(resultSet.getDate("date_value"));
        return params;
    }
}
