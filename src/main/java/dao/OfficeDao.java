package dao;

import entities.Office;

import java.util.List;

public interface OfficeDao {

    List<Office> findAll(Integer grant);

    List<Office> findByLocationId(Integer locationId, Integer grant);

    Office findById(Integer id, Integer grant);

    void save(Office office, Integer grant);

    void update(Office office, Integer grant);

    void delete(Integer officeId, Integer grant);

    void commit();
}
