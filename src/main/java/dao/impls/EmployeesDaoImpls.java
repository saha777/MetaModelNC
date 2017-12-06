package dao.impls;

import dao.EmployeesDao;
import entities.ObjectType;
import dao.converter.Converter;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;
import entities.Employee;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeesDaoImpls implements EmployeesDao{

    private ObjectTypes objectType;

    private MetaModelService metaModelService;
    private Converter<Employee> employeeConverter;

    @Autowired
    public EmployeesDaoImpls(MetaModelService metaModelService, Converter<Employee> employeeConverter) {
        this.metaModelService = metaModelService;
        this.employeeConverter = employeeConverter;
        objectType = metaModelService.findObjectTypeByTypeName(ObjectType.EMPLOYEE.toString());
    }

    @Override
    public List<Employee> findAll(Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByTypeName(objectType.getName(), grant);
        List<Employee> employees = new ArrayList<>();

        for(Objects object : objects){
            employees.add(checkEmployeeInCache(object, grant));
        }

        return employees;
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId, Integer grant) {
        List<Objects> objects = metaModelService.findObjectsByParentId(departmentId, grant);
        List<Employee> employees = new ArrayList<>();

        for(Objects object : objects){
            employees.add(checkEmployeeInCache(object, grant));
        }

        return employees;
    }


    @Override
    public Employee findById(Integer id, Integer grant) {
        return checkEmployeeInCache(metaModelService.findObjectByObjectId(id, grant), grant);
    }

    private Employee checkEmployeeInCache(Objects object, Integer grant){
        Employee employee;

        employee = employeeConverter.convertDataToTemplate(
            object,
            metaModelService.getParamsMap(object.getObjectId(), grant),
            Employee.class);

        return employee;
    }

    @Override
    public void save(Employee employee, Integer grant) {
        Integer objectTypeId = metaModelService.findObjectTypeByTypeName(objectType.getName()).getTypeId();

        Objects object = employeeConverter.convertTemplateToObjects(employee, objectTypeId);

        List<Params> params = employeeConverter.convertTemplateToParams(
                employee,
                objectTypeId,
                metaModelService.getAttrsMap(objectTypeId, grant));

        metaModelService.saveObject(object, grant);
        metaModelService.saveParams(params, grant);
    }

    @Override
    public void update(Employee employee, Integer grant) {
        Integer objectTypeId = metaModelService.findObjectTypeByTypeName(objectType.getName()).getTypeId();

        Objects object = employeeConverter.convertTemplateToObjects(employee, objectTypeId);

        List<Params> params = employeeConverter.convertTemplateToParams(
                employee,
                objectTypeId,
                metaModelService.getAttrsMap(objectTypeId, grant));

        metaModelService.updateObject(object, grant);
        metaModelService.updateParams(params, grant);
    }

    @Override
    public void delete(Integer empId, Integer grant) {

    }

    @Override
    public void commit(){
    }
}
