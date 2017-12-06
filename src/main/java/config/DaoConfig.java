package config;

import dao.DepartmentDao;
import dao.EmployeesDao;
import dao.LocationsDao;
import dao.OfficeDao;
import dao.converter.Converter;
import dao.converter.impls.ConverterImpl;
import dao.impls.DepartmentsDaoImpls;
import dao.impls.EmployeesDaoImpls;
import dao.impls.LocationsDaoImpl;
import dao.impls.OfficeDaoImpl;
import entities.Department;
import entities.Employee;
import entities.Location;
import entities.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.DepartmentService;
import service.EmployeeService;
import service.LocationService;
import service.OfficeService;
import service.impls.DepartmentServiceImpl;
import service.impls.EmployeeServiceImpl;
import service.impls.LocationServiceImpl;
import service.impls.OfficeServiceImpl;

import javax.inject.Inject;

@Configuration
@Import(MetaModelConfig.class)
public class DaoConfig{

    @Inject
    private MetaModelConfig metaModelConfig;

    @Bean
    public Converter<?> getConverter() {
        return new ConverterImpl<>();
    }

    @Bean
    public EmployeesDao getEmployeesDao(){
        return new EmployeesDaoImpls(
                metaModelConfig.getMetaModelService(),
                (Converter<Employee>) getConverter());
    }

    @Bean
    public DepartmentDao getDepartmentDao(){
        return new DepartmentsDaoImpls(
                metaModelConfig.getMetaModelService(),
                (Converter<Department>) getConverter());
    }

    @Bean
    public LocationsDao getLocationsDao(){
        return new LocationsDaoImpl(
                metaModelConfig.getMetaModelService(),
                (Converter<Location>) getConverter());
    }

    @Bean
    public OfficeDao getOfficeDao(){
        return new OfficeDaoImpl(
                metaModelConfig.getMetaModelService(),
                (Converter<Office>) getConverter());
    }

    @Bean
    public LocationService getLocationService(){
        return new LocationServiceImpl(getLocationsDao());
    }

    @Bean
    public OfficeService getOfficeService(){
        return new OfficeServiceImpl(getOfficeDao());
    }

    @Bean
    public DepartmentService getDepartmentService(){
        return new DepartmentServiceImpl(getDepartmentDao());
    }

    @Bean
    public EmployeeService getEmployeeService(){
        return new EmployeeServiceImpl(getEmployeesDao());
    }
}
