package entities.mappers.impls;

import entities.AbstractObject;
import entities.Employee;
import entities.mappers.MetaObject;
import entities.mappers.ObjectMapper;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.Map;

public class EmployeeMapper implements ObjectMapper<Employee> {
    public static final String SPECIALITY_FIELD = "Speciality";
    public static final String EXPERIENCE_FIELD = "Experience";
    public static final String AGE_FIELD = "Age";
    public static final String SALARY_FIELD = "Salary";
    public static final String HIREDATE_FIELD = "Hiredate";

    @Override
    public Employee mapObject(Objects objects, Map<String, Params> paramsMap) {
        MetaObject metaObject = new MetaObject(objects, paramsMap);
        return mapObject(metaObject);
    }

    @Override
    public Employee mapObject(MetaObject metaObject) {
        Employee employee = new Employee();
        employee.setObjectId(metaObject.getObjectId());
        employee.setParentId(metaObject.getParentObjectId());
        employee.setName(metaObject.getName());
        employee.setSpeciality(metaObject.getAttr(EmployeeMapper.SPECIALITY_FIELD).getTextValue());
        employee.setExperience(metaObject.getAttr(EmployeeMapper.EXPERIENCE_FIELD).getNumberValue());
        employee.setAge(metaObject.getAttr(EmployeeMapper.AGE_FIELD).getNumberValue());
        employee.setSalary(metaObject.getAttr(EmployeeMapper.SALARY_FIELD).getNumberValue());
        employee.setHiredate(metaObject.getAttr(EmployeeMapper.HIREDATE_FIELD).getDateValue());
        return employee;
    }

    @Override
    public MetaObject getMetaObject(AbstractObject object) {
        return object.getMetaObject();
    }
}
