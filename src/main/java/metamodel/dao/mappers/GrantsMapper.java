package metamodel.dao.mappers;

import metamodel.dao.models.Grants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrantsMapper implements RowMapper<Grants> {
    @Override
    public Grants mapRow(ResultSet resultSet, int i) throws SQLException {
        Grants grant = new Grants();
        grant.setR(resultSet.getByte("r") == 1);
        grant.setW(resultSet.getByte("w") == 1);
        return grant;
    }
}
