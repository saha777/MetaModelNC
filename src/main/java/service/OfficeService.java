package service;

import entities.Office;

import java.util.List;

public interface OfficeService {
    List<Office> findAll(Integer grant);

    List<Office> findByLocationId(Integer locationId, Integer grant);

    Office findByObjectId(Integer objectId, Integer grant);

    void save(Office office, Integer grant);

    void update(Office office, Integer grant);

    void delete(Integer officeId, Integer grant);

    void commit();
}
