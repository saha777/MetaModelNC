package service;

import entities.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll(Integer grant);

    Location findById(Integer id, Integer grant);

    void save(Location employee, Integer grant);

    void update(Location employee, Integer grant);

    void delete(Integer empId, Integer grant);

    void commit();
}
