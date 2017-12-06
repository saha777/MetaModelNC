package service;

import entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll(Integer grant);

    List<Employee> findByDepartmentId(Integer departmentId, Integer grant);

    Employee findByObjectId(Integer objectId, Integer grant);

    void save(Employee employee, Integer grant);

    void update(Employee employee, Integer grant);

    void delete(Integer employeeId, Integer grant);

    void commit();
}
