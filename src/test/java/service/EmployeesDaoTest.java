package service;

import config.DaoConfig;
import dao.EmployeesDao;
import entities.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class EmployeesDaoTest {
    @Autowired
    private EmployeesDao employeesDao;

    @Test
    public void findAllTest(){
        List<Employee> employees = employeesDao.findAll(5);
        for (Employee employee : employees){
            System.out.println(employee.toString());
        }
    }

    @Test
    public void findOneTest(){
        Employee employee = employeesDao.findById(9,5);
        System.out.println(employee.toString());
    }
}
