package metamodel.dao.mappers;

import metamodel.dao.models.Grants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrantsMapper implements RowMapper<Grants> {
    @Override
    public Grants mapRow(ResultSet resultSet, int i) throws SQLException {
        Grants grants = new Grants();
        grants.setObjectId(resultSet.getInt("object_id"));
        grants.setAttrId(resultSet.getInt("attr_id"));
        grants.setR(resultSet.getInt("r"));
        grants.setW(resultSet.getInt("w"));
        return grants;
    }
}
