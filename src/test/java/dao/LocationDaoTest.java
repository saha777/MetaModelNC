package dao;

import config.DaoConfig;
import entities.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.LocationService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class LocationDaoTest {
    @Autowired
    private LocationsDao locationsDao;

    @Autowired
    private LocationService locationService;

    @Test
    public void findAllTest() {
        List<Location> locations = locationService.findAll(5);
        for (Location location : locations) {
            System.out.println(location.toString());
        }
    }

    @Test
    public void findOneTest() {
        Location location = locationsDao.findById(8, 5);
        System.out.println(location.toString());
    }

    @Test
    public void saveTest() {
        Location location = new Location();
        location.setName("Berlik");
        locationsDao.save(location, 5);
    }

    @Test
    public void updateTest() {
        Location location = locationsDao.findById(52, 5);
        location.setName("Dallas");
        locationsDao.update(location, 5);
    }

    @Test
    public void deleteTest() {
        locationsDao.delete(68, 5);
        locationsDao.delete(70, 5);
    }
}
