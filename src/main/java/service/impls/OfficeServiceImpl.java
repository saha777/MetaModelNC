package service.impls;

import dao.Dao;
import entities.Office;
import entities.mappers.impls.OfficeMapper;
import metamodel.dao.GrantsDao;
import org.springframework.beans.factory.annotation.Autowired;
import service.AbstractService;

public class OfficeServiceImpl extends AbstractService<Office> {
    @Autowired
    public OfficeServiceImpl(Dao<Office> dao, GrantsDao grantsDao) {
        super(dao, new OfficeMapper(), grantsDao);
    }
}