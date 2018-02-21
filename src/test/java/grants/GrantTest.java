package grants;

import config.DaoConfig;
import dao.EmployeesDao;
import entities.Employee;
import metamodel.dao.GrantsDao;
import metamodel.dao.ObjectsDao;
import metamodel.dao.models.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class GrantTest {
    @Autowired
    private EmployeesDao employeesDao;

    @Autowired
    private ObjectsDao objectsDao;

    @Autowired
    private GrantsDao grantsDao;

    @Test
    public void getEmp() {
        Role role = grantsDao.findById(4);
        Employee employee = employeesDao.findById(7, role);

        System.out.println(employee);

        employee.setName("Bender Stiv");

        employeesDao.update(employee, role);

        System.out.println(objectsDao.findById(employee.getObjectId()));

        employee = employeesDao.findById(7, role);

        System.out.println(employee);
    }
}
