package config;

import dao.*;
import dao.converter.Converter;
import dao.converter.impls.ConverterImpl;
import dao.impls.DepartmentsDaoImpls;
import dao.impls.EmployeesDaoImpls;
import dao.impls.LocationsDaoImpl;
import dao.impls.OfficeDaoImpl;
import entities.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
                metaModelConfig.getAttrsMapperService(),
                metaModelConfig.getParamsMapperService());
    }

    @Bean
    public DepartmentDao getDepartmentDao(){
        return new DepartmentsDaoImpls(
                metaModelConfig.getMetaModelService(),
                metaModelConfig.getAttrsMapperService(),
                metaModelConfig.getParamsMapperService());
    }

    @Bean
    public LocationsDao getLocationsDao(){
        return new LocationsDaoImpl(
                metaModelConfig.getMetaModelService(),
                metaModelConfig.getAttrsMapperService(),
                metaModelConfig.getParamsMapperService());
    }

    @Bean
    public OfficeDao getOfficeDao(){
        return new OfficeDaoImpl(
                metaModelConfig.getMetaModelService(),
                metaModelConfig.getAttrsMapperService(),
                metaModelConfig.getParamsMapperService());
    }
}
