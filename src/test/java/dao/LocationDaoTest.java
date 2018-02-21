package dao;

import config.DaoConfig;
import entities.Location;
import metamodel.dao.models.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class LocationDaoTest {
    @Autowired
    private LocationsDao locationsDao;
    private Role role;

    public void roleInit() {
        role = new Role();
        role.setRoleId(2);
        role.setName("PM");
    }

    @Test
    public void findAllTest() {
        List<Location> locations = locationsDao.findAll(role);
        for (Location location : locations) {
            System.out.println(location.toString());
        }
    }

    @Test
    public void findOneTest() {
        Location location = locationsDao.findById(8, role);
        System.out.println(location.toString());
    }

    @Test
    public void saveTest() {
        Location location = new Location();
        location.setName("Berlik");
        locationsDao.save(location, role);
    }

    @Test
    public void updateTest() {
        Location location = locationsDao.findById(52, role);
        location.setName("Dallas");
        locationsDao.update(location, role);
    }

    @Test
    public void deleteTest() {
        locationsDao.delete(68, role);
        locationsDao.delete(70, role);
    }
}
