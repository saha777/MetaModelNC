package service.impls;

import dao.LocationsDao;
import entities.Location;
import org.springframework.beans.factory.annotation.Autowired;
import service.LocationService;

import java.util.List;

public class LocationServiceImpl implements LocationService{

    private LocationsDao locationsDao;

    @Autowired
    public LocationServiceImpl(LocationsDao locationsDao) {
        this.locationsDao = locationsDao;
    }

    @Override
    public List<Location> findAll(Integer grant) {
        return locationsDao.findAll(grant);
    }

    @Override
    public Location findById(Integer id, Integer grant) {
        return locationsDao.findById(id, grant);
    }

    @Override
    public void save(Location employee, Integer grant) {

    }

    @Override
    public void update(Location employee, Integer grant) {

    }

    @Override
    public void delete(Integer empId, Integer grant) {

    }

    @Override
    public void commit() {

    }
}
