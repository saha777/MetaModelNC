package service.impls;

import dao.OfficeDao;
import entities.Office;
import org.springframework.beans.factory.annotation.Autowired;
import service.OfficeService;

import java.util.List;

public class OfficeServiceImpl implements OfficeService{

    private OfficeDao officeDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    @Override
    public List<Office> findAll(Integer grant) {
        return officeDao.findAll(grant);
    }

    @Override
    public List<Office> findByLocationId(Integer locationId, Integer grant) {
        return officeDao.findByLocationId(locationId, grant);
    }

    @Override
    public Office findByObjectId(Integer objectId, Integer grant) {
        return officeDao.findById(objectId, grant);
    }

    @Override
    public void save(Office office, Integer grant) {

    }

    @Override
    public void update(Office office, Integer grant) {

    }

    @Override
    public void delete(Integer officeId, Integer grant) {

    }

    @Override
    public void commit() {

    }
}
