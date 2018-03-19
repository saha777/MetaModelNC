package service.impls;

import dao.Dao;
import entities.Employee;
import entities.mappers.impls.EmployeeMapper;
import metamodel.dao.GrantsDao;
import org.springframework.beans.factory.annotation.Autowired;
import service.AbstractService;

public class EmployeeServiceImpl extends AbstractService<Employee> {

    @Autowired
    public EmployeeServiceImpl(Dao<Employee> employeesDao, GrantsDao grantsDao) {
        super(employeesDao, new EmployeeMapper(), grantsDao);
    }
}
