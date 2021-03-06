package dao.impls;

import dao.AbstractDao;
import entities.Employee;
import entities.ObjectType;
import entities.Office;
import entities.mappers.impls.EmployeeMapper;
import entities.mappers.impls.OfficeMapper;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

public class OfficeDaoImpl extends AbstractDao<Office> {
    @Autowired
    public OfficeDaoImpl(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService) {
        super(metaModelService.findObjectTypeByTypeName(
                ObjectType.OFFICE.toString()),
                new OfficeMapper(),
                metaModelService,
                attrsMapperService,
                paramsMapperService);
    }
}
