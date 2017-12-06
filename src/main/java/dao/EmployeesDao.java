package dao;

import entities.Employee;

import java.util.List;

public interface EmployeesDao {
    List<Employee> findAll(Integer grant);

    List<Employee> findByDepartmentId(Integer departmentId, Integer grant);

    Employee findById(Integer id, Integer grant);

    void save(Employee employee, Integer grant);

    void update(Employee employee, Integer grant);

    void delete(Integer empId, Integer grant);

    void commit();
}
