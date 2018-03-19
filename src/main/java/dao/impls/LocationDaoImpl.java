package dao.impls;

import dao.AbstractDao;
import entities.Location;
import entities.ObjectType;
import entities.mappers.impls.LocationMapper;
import metamodel.mapper.AttrsMapperService;
import metamodel.mapper.ParamsMapperService;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationDaoImpl extends AbstractDao<Location> {
    @Autowired
    public LocationDaoImpl(
            MetaModelService metaModelService,
            AttrsMapperService attrsMapperService,
            ParamsMapperService paramsMapperService) {
        super(metaModelService.findObjectTypeByTypeName(
                ObjectType.LOCATION.toString()),
                new LocationMapper(),
                metaModelService,
                attrsMapperService,
                paramsMapperService);
    }
}