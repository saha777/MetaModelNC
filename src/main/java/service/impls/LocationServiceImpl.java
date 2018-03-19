package service.impls;

import dao.Dao;
import entities.Location;
import entities.mappers.impls.LocationMapper;
import metamodel.dao.GrantsDao;
import org.springframework.beans.factory.annotation.Autowired;
import service.AbstractService;

public class LocationServiceImpl extends AbstractService<Location> {
    @Autowired
    public LocationServiceImpl(Dao<Location> dao, GrantsDao grantsDao) {
        super(dao, new LocationMapper(), grantsDao);
    }
}