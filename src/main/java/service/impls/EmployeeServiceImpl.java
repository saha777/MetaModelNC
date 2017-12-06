package service.impls;

import dao.EmployeesDao;
import entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeesDao employeesDao;

    @Autowired
    public EmployeeServiceImpl(EmployeesDao employeesDao) {
        this.employeesDao = employeesDao;
    }

    @Override
    public List<Employee> findAll(Integer grant) {
        return employeesDao.findAll(grant);
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId, Integer grant) {
        return employeesDao.findByDepartmentId(departmentId, grant);
    }

    @Override
    public Employee findByObjectId(Integer objectId, Integer grant) {
        return employeesDao.findById(objectId, grant);
    }

    @Override
    public void save(Employee employee, Integer grant) {
        employeesDao.save(employee, grant);
    }

    @Override
    public void update(Employee employee, Integer grant) {
        employeesDao.update(employee, grant);
    }

    @Override
    public void delete(Integer employeeId, Integer grant) {

    }

    @Override
    public void commit() {

    }
}
