package config;

import dao.*;
import dao.impls.DepartmentDaoImpl;
import dao.impls.EmployeesDaoImpl;
import dao.impls.LocationDaoImpl;
import dao.impls.OfficeDaoImpl;
import entities.Department;
import entities.Employee;
import entities.Location;
import entities.Office;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.Service;
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
    public Dao<Employee> getEmployeesDao(){
        return new EmployeesDaoImpl(
                metaModelConfig.getMetaModelService(),
                metaModelConfig.getAttrsMapperService(),
                metaModelConfig.getParamsMapperService());
    }

    @Bean
    public Dao<Department> getDepartmentDao(){
        return new DepartmentDaoImpl(
                metaModelConfig.getMetaModelService(),
                metaModelConfig.getAttrsMapperService(),
                metaModelConfig.getParamsMapperService());
    }

    @Bean
    public Dao<Location> getLocationDao(){
        return new LocationDaoImpl(
                metaModelConfig.getMetaModelService(),
                metaModelConfig.getAttrsMapperService(),
                metaModelConfig.getParamsMapperService());
    }

    @Bean
    public Dao<Office> getOfficeDao(){
        return new OfficeDaoImpl(
                metaModelConfig.getMetaModelService(),
                metaModelConfig.getAttrsMapperService(),
                metaModelConfig.getParamsMapperService());
    }

    @Bean
    public Service<Employee> getEmployeesService(){
        return new EmployeeServiceImpl(
                getEmployeesDao(),
                metaModelConfig.getGrantsDao());
    }

    @Bean
    public Service<Department> getDepartmentService(){
        return new DepartmentServiceImpl(
                getDepartmentDao(),
                metaModelConfig.getGrantsDao());
    }

    @Bean
    public Service<Location> getLocationService(){
        return new LocationServiceImpl(
                getLocationDao(),
                metaModelConfig.getGrantsDao());
    }

    @Bean
    public Service<Office> getOfficeService(){
        return new OfficeServiceImpl(
                getOfficeDao(),
                metaModelConfig.getGrantsDao());
    }
}
