package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.anotations.*;
import entities.mappers.MetaObject;
import entities.mappers.ObjectMapper;
import entities.mappers.impls.EmployeeMapper;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.Date;
import java.util.List;
import java.util.Map;

@EntityType(type = ObjectType.EMPLOYEE)
public class Employee extends AbstractObject{
    @ObjectId
    private Integer objectId;

    @ParentId
    private Integer parentId;

    @Name
    private String name;

    @Param(name = "Speciality", type = Type.TEXT)
    private String speciality;

    @Param(name = "Experience", type = Type.NUMBER)
    private Integer experience;

    @Param(name = "Age", type = Type.NUMBER)
    private Integer age;

    @Param(name = "Salary", type = Type.NUMBER)
    private Integer salary;

    @JsonFormat(pattern="dd.MM.yyyy")
    @Param(name = "Hiredate", type = Type.DATE)
    private Date hiredate;

//    @Param(name = "Task", type = Type.NUMBER, count = Count.MANY)
//    private List<Integer> tasks;

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

//    public List<Integer> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Integer> tasks) {
//        this.tasks = tasks;
//    }


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
