package dao;

import entities.Location;

import java.util.List;

public interface LocationsDao {

    List<Location> findAll(Integer grant);

    Location findById(Integer id, Integer grant);

    void save(Location location, Integer grant);

    void update(Location location, Integer grant);

    void delete(Integer locationId, Integer grant);
}
