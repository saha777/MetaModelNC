package service.impls;

import dao.Dao;
import entities.Department;
import entities.mappers.impls.DepartmentMapper;
import metamodel.dao.GrantsDao;
import org.springframework.beans.factory.annotation.Autowired;
import service.AbstractService;

public class DepartmentServiceImpl extends AbstractService<Department> {
    @Autowired
    public DepartmentServiceImpl(Dao<Department> dao, GrantsDao grantsDao) {
        super(dao, new DepartmentMapper(), grantsDao);
    }
}
