package service.impls;

import dao.DepartmentDao;
import entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> findAll(Integer grant) {
        return departmentDao.findAll(grant);
    }

    @Override
    public List<Department> findByOfficeId(Integer officeId, Integer grant) {
        return departmentDao.findByOfficeId(officeId, grant);
    }

    @Override
    public Department findByObjectId(Integer objectId, Integer grant) {
        return departmentDao.findById(objectId, grant);
    }

    @Override
    public void save(Department department, Integer grant) {

    }

    @Override
    public void update(Department department, Integer grant) {

    }

    @Override
    public void delete(Integer departmentId, Integer grant) {

    }

    @Override
    public void commit() {

    }
}
