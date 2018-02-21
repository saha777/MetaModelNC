package dao;

import entities.Department;
import metamodel.dao.models.Role;

import java.util.List;

public interface DepartmentDao {
    List<Department> findAll(Role role);

    List<Department> findByParentId(Integer officeId, Role role);

    Department findById(Integer id, Role role);

    Integer save(Department department, Role role);

    void update(Department department, Role role);

    void delete(Integer departmentId, Role role);
}
