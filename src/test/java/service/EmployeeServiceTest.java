package service;

import config.DaoConfig;
import entities.Employee;
import metamodel.dao.models.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class EmployeeServiceTest {
    @Autowired
    private Service<Employee> employeesService;

    private Role[] grants;
    private static Integer[] ids;

    @Before
    public void createGrants() {
        Role grant1 = new Role();
        grant1.setRoleId(1);
        grant1.setName("ADMIN");

        Role grant2 = new Role();
        grant2.setRoleId(3);
        grant2.setName("ACCOUNTANT");

        Role grant3 = new Role();
        grant3.setRoleId(4);
        grant3.setName("USER");

        grants = new Role[3];
        grants[0] = grant1;
        grants[1] = grant2;
        grants[2] = grant3;
    }

    @Test
    public void getTest() {
        List<Employee> employees = employeesService.getAll(grants[2]);
        employees.forEach((item) -> System.out.println(item.toString()));
    }

    @Test
    public void saveTest() {
//        ids = new Integer[3];

//        Employee employee = new Employee();
//        employee.setName("Employee1");
//        employee.setSpeciality("developer");
//        employee.setSalary(2000);
//        employee.setExperience(5);
//        employee.setAge(30);
//        employee.setHiredate(new Date(System.currentTimeMillis()));
//        ids[0] = employeesService.save(employee, grants[0]);

//        employee = new Employee();
//        employee.setName("Employee2");
//        employee.setSpeciality("manager");
//        employee.setSalary(4000);
//        employee.setExperience(8);
//        employee.setAge(34);
//        employee.setHiredate(new Date(System.currentTimeMillis()));
//        ids[1] = employeesService.save(employee, grants[1]);

//        employee = new Employee();
//        employee.setName("Employee3");
//        employee.setSpeciality("developer");
//        employee.setSalary(1000);
//        employee.setExperience(1);
//        employee.setAge(25);
//        employee.setHiredate(new Date(System.currentTimeMillis()));
//        ids[2] = employeesDao.save(employee, grants[2]);

//        for (Integer id : ids)
//            Assert.assertNotNull(id);
    }

    @Test
    public void updateTest() {
        //ids = new Integer[3];
        //ids[0] = 187;
        //ids[1] = 188;
        //ids[2] = 189;

//        Employee employee = employeesService.get(grants[0], ids[1]);
//        employeesService.get(grants[1], ids[1]);
//        employeesService.get(grants[2], ids[1]);
//        Assert.assertNotNull(employee);
//
//        employee.setAge(33);
//        employeesService.update(grants[0], employee);
//
//        Employee emp1 = employeesService.get(grants[0], ids[1]);
//        Employee emp2 = employeesService.get(grants[1], ids[1]);
//        Employee emp3 = employeesService.get(grants[2], ids[1]);
//
//        Assert.assertEquals(emp1.getAge(), emp2.getAge());
//        Assert.assertEquals(emp1.getAge(), emp3.getAge());
    }

    @Test
    public void deleteTest() {
//        employeesService.delete(ids[0], grants[0]);
//        employeesService.delete(ids[1], grants[1]);
//        employeesService.delete(ids[2], grants[2]);
//
//        Assert.assertNull(employeesService.findById(ids[1], grants[0]));
//        Assert.assertNull(employeesService.findById(ids[2], grants[1]));
//        Assert.assertNull(employeesService.findById(ids[0], grants[2]));
    }
}