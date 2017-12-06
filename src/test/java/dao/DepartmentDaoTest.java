package dao;

import config.DaoConfig;
import entities.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class DepartmentDaoTest {
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void findAllTest() {
        List<Department> departments = departmentDao.findAll(5);
        for (Department department : departments) {
            System.out.println(department.toString());
        }
    }

    @Test
    public void findOneTest() {
        Department department = departmentDao.findById(8, 5);
        System.out.println(department.toString());
    }
}
