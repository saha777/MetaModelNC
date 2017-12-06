package dao;

import entities.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> findAll(Integer grant);

    List<Department> findByOfficeId(Integer officeId, Integer grant);

    Department findById(Integer id, Integer grant);

    void save(Department department, Integer grant);

    void update(Department department, Integer grant);

    void delete(Integer departmentId, Integer grant);

    void commit();
}
