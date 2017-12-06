package dao.impls;

import dao.OfficeDao;
import dao.converter.Converter;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import entities.ObjectType;
import entities.Office;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficeDaoImpl implements OfficeDao {
    private static Map<Integer, Office> cache = new HashMap<>();

    private ObjectTypes objectType;

    private Converter<Office> officeConverter;
    private MetaModelService metaModelService;

    @Autowired
    public OfficeDaoImpl(
            MetaModelService metaModelService,
            Converter<Office> officeConverter) {
        this.metaModelService = metaModelService;
        this.officeConverter = officeConverter;
        this.objectType = metaModelService.findObjectTypeByTypeName(ObjectType.DEPARTMENT.toString());
    }

    @Override
    public List<Office> findAll(Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByTypeName(objectType.getName(), grant);
        List<Office> offices = new ArrayList<>();

        for(Objects object : objects){
            offices.add(checkOfficeInCache(object, grant));
        }

        return offices;
    }

    @Override
    public List<Office> findByLocationId(Integer locationId, Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByParentId(locationId, grant);
        List<Office> offices = new ArrayList<>();

        for(Objects object : objects){
            offices.add(checkOfficeInCache(object, grant));
        }

        return offices;
    }

    @Override
    public Office findById(Integer objectId, Integer grant) {
        Objects object = metaModelService.findObjectByObjectId(objectId, grant);
        return checkOfficeInCache(object, grant);
    }

    private Office checkOfficeInCache(Objects object, Integer grant){
        Office office;
        if(cache.containsKey(object.getObjectId())) {
            office = cache.get(object.getObjectId());
        } else {
            office = officeConverter.convertDataToTemplate(
                    object,
                    metaModelService.getParamsMap(object.getObjectId(), grant),
                    Office.class);

            cache.put(office.getObjectId(), office);
        }
        return office;
    }

    @Override
    public void save(Office employee, Integer grant) {

    }

    @Override
    public void update(Office employee, Integer grant) {

    }

    @Override
    public void delete(Integer empId, Integer grant) {

    }

    @Override
    public void commit() {

    }
}
