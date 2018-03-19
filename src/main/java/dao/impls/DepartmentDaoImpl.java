package dao.impls;

import dao.AbstractDao;
import entities.Department;
import entities.ObjectType;
import entities.mappers.impls.DepartmentMapper;
import entities.mappers.impls.EmployeeMapper;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentDaoImpl extends AbstractDao<Department> {
    @Autowired
    public DepartmentDaoImpl(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService) {
        super(metaModelService.findObjectTypeByTypeName(
                ObjectType.DEPARTMENT.toString()),
                new DepartmentMapper(),
                metaModelService,
                attrsMapperService,
                paramsMapperService);
    }
}
