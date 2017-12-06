package dao;

import config.DaoConfig;
import entities.Office;
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

    @Test
    public void findAllTest(){
        List<Office> offices = officeDao.findAll(5);
        for (Office office : offices){
            System.out.println(office.toString());
        }
    }

    @Test
    public void findOneTest(){
        Office employee = officeDao.findById(8, 5);
        System.out.println(employee.toString());
    }
}
