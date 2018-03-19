package entities.mappers.impls;

import entities.AbstractObject;
import entities.Employee;
import entities.mappers.MetaObject;
import entities.mappers.ObjectMapper;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        employee.setMetaObject(metaObject);
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

    public Employee updateObject(Employee employee, Map<String, String[]> params) {
        if (params.get("name") != null) {
            String name = params.get("name")[0];
            if (name != null && !name.equals(employee.getName()))
                employee.setName(name);
        }

        if (params.get(SPECIALITY_FIELD.toLowerCase()) != null) {
            String speciality = params.get(SPECIALITY_FIELD.toLowerCase())[0];
            if (speciality != null && !speciality.equals(employee.getSpeciality()))
                employee.setSpeciality(speciality);
        }

        if (params.get(EXPERIENCE_FIELD.toLowerCase()) != null) {
            int experience = Integer.parseInt(params.get(EXPERIENCE_FIELD.toLowerCase())[0]);
            if (experience != employee.getExperience())
                employee.setExperience(experience);
        }

        if (params.get(AGE_FIELD.toLowerCase()) != null) {
            int age = Integer.parseInt(params.get(AGE_FIELD.toLowerCase())[0]);
            if (age != employee.getAge())
                employee.setAge(age);
        }

        if (params.get(SALARY_FIELD.toLowerCase()) != null) {
            int salary = Integer.parseInt(params.get(SALARY_FIELD.toLowerCase())[0]);
            if (salary != employee.getSalary())
                employee.setSalary(salary);
        }

        if (params.get(HIREDATE_FIELD.toLowerCase()) != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date hiredate = sdf.parse(params.get(HIREDATE_FIELD.toLowerCase())[0]);
                if (!hiredate.equals(employee.getHiredate()))
                    employee.setHiredate(hiredate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return employee;
    }
}
