package dao;

import config.DaoConfig;
import entities.Office;
import metamodel.dao.models.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class OfficeDaoTest {
    @Autowired
    private OfficeDao officeDao;
    private Role role;

    public void roleInit() {
        role = new Role();
        role.setRoleId(2);
        role.setName("PM");
    }

    @Test
    public void findAllTest(){
        List<Office> offices = officeDao.findAll(role);
        for (Office office : offices){
            System.out.println(office.toString());
        }
    }

    @Test
    public void findOneTest(){
        Office employee = officeDao.findById(8, role);
        System.out.println(employee.toString());
    }
}
