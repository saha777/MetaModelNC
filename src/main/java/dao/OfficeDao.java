package dao;

import entities.Office;
import metamodel.dao.models.Role;

import java.util.List;

public interface OfficeDao {

    List<Office> findAll(Role role);

    List<Office> findByParentId(Integer locationId, Role role);

    Office findById(Integer id, Role role);

    Integer save(Office office, Role role);

    void update(Office office, Role role);

    void delete(Integer officeId, Role role);

}
