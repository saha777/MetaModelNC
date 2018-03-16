package service;

import entities.Employee;
import metamodel.dao.models.Role;

import java.util.List;

public interface EmployeesService {
    List<Employee> getAll(Role role);
    List<Employee> getByParentId(Role role, int parentId);
    Employee get(Role role, int empId);
    Employee update(Role role, Employee emp);
}
