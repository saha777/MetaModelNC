package dao;

import entities.Employee;
import metamodel.dao.models.Grants;
import metamodel.dao.models.Role;

import java.util.List;

public interface EmployeesDao {
    List<Employee> findAll();

    List<Employee> findByParentId(Integer departmentId);

    Employee findById(Integer id);

    Integer save(Employee employee);

    void update(Employee employee);

    void delete(Integer empId);
}
