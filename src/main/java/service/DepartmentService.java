package service;

import entities.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll(Integer grant);

    List<Department> findByOfficeId(Integer officeId, Integer grant);

    Department findByObjectId(Integer objectId, Integer grant);

    void save(Department department, Integer grant);

    void update(Department department, Integer grant);

    void delete(Integer departmentId, Integer grant);

    void commit();
}
