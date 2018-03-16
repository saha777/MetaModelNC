package service.impls;

import dao.EmployeesDao;
import entities.Employee;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import service.EmployeesService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeesService {

    private EmployeesDao employeesDao;
    private GrantsDao grantsDao;

    @Autowired
    public EmployeeServiceImpl(EmployeesDao employeesDao, GrantsDao grantsDao) {
        this.employeesDao = employeesDao;
        this.grantsDao = grantsDao;
    }

    @Override
    public List<Employee> getAll(Role role) {
        employeesDao.findAll();
        return null;
    }

    @Override
    public List<Employee> getByParentId(Role role, int parentId) {
        return null;
    }

    @Override
    public Employee get(Role role, int empId) {
        return null;
    }

    @Override
    public Employee update(Role role, Employee emp) {
        return null;
    }
}
