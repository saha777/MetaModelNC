package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import entities.mappers.impls.EmployeeMapper;

import java.util.Date;

public class Employee extends AbstractObject{
    private Integer objectId;
    private Integer parentId;
    private String name;
    private String speciality;
    private Integer experience;
    private Integer age;
    private Integer salary;

    @JsonFormat(pattern="dd.MM.yyyy")
    private Date hiredate;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
        this.metaObject.setObjectId(objectId);
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
        this.metaObject.setParentObjectId(parentId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.metaObject.setName(name);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
        this.metaObject.setTextAttr(EmployeeMapper.SPECIALITY_FIELD, speciality);
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
        this.metaObject.setNumberAttr(EmployeeMapper.EXPERIENCE_FIELD, experience);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
        this.metaObject.setNumberAttr(EmployeeMapper.AGE_FIELD, age);
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
        this.metaObject.setNumberAttr(EmployeeMapper.SALARY_FIELD, salary);
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
        this.metaObject.setDateAttr(EmployeeMapper.HIREDATE_FIELD, hiredate);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "objectId=" + objectId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", experience=" + experience +
                ", age=" + age +
                ", salary=" + salary +
                ", hiredate=" + hiredate +
                '}';
    }
}
