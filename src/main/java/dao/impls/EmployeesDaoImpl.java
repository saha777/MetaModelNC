package dao.impls;

import dao.AbstractDao;
import entities.ObjectType;
import entities.mappers.impls.EmployeeMapper;
import entities.Employee;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeesDaoImpl extends AbstractDao<Employee> {
    @Autowired
    public EmployeesDaoImpl(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService) {
        super(metaModelService.findObjectTypeByTypeName(
                ObjectType.EMPLOYEE.toString()),
                new EmployeeMapper(),
                metaModelService,
                attrsMapperService,
                paramsMapperService);
    }
}
