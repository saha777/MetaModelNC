package dao;

import entities.Location;
import metamodel.dao.models.Role;

import java.util.List;

public interface LocationsDao {

    List<Location> findAll(Role role);

    Location findById(Integer id, Role role);

    Integer save(Location location, Role role);

    void update(Location location, Role role);

    void delete(Integer locationId, Role role);
}
