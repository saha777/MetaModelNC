package service;

import config.DaoConfig;
import dao.converter.Converter;
import entities.Employee;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class CollectorTest {

    @Autowired
    private Converter<Employee> employeeConverter;

    /*@Test
    public void collect(){
        Employee employee = employeeConverter.convertDataToTemplate(10, Employee.class);
        System.out.println(employee.toString());
        List<Params> params = employeeConverter.convertTemplateToParams(employee);
        for(Params param : params){
            System.out.println(param.toString());
        }
        System.out.println(employeeConverter.convertTemplateToObjects(employee).toString());
    }*/
}

