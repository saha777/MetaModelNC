package dao;

import entities.Employee;
import metamodel.dao.models.Grants;
import metamodel.dao.models.Role;

import java.util.List;

public interface EmployeesDao {
    List<Employee> findAll(Role role);

    List<Employee> findByParentId(Integer departmentId, Role role);

    Employee findById(Integer id, Role role);

    Integer save(Employee employee, Role role);

    void update(Employee employee, Role role);

    void delete(Integer empId, Role role);
}
