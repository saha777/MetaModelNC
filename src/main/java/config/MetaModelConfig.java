package config;

import metamodel.dao.*;
import metamodel.dao.impls.*;
import metamodel.service.MetaModelService;
import metamodel.service.impls.MetaModelServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
public class MetaModelConfig {
    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("ALEX");
        dataSource.setPassword("alex");
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        Locale.setDefault(Locale.ENGLISH);
        return dataSource;
    }

    @Bean
    public GrantsDao getGrantsDao() {
        return new GrantsDaoImpl(getJdbcTemplate());
    }

    @Bean
    public ObjectTypesDao getObjectTypesDao(){
        return new ObjectTypesDaoImpl(getJdbcTemplate());
    }

    @Bean
    public ObjectsDao getObjectsDao(){
        return new ObjectsDaoImpl(getJdbcTemplate());
    }

    @Bean
    public AttrsDao getAttrsDao() {
        return new AttrsDaoImpl(getJdbcTemplate());
    }

    @Bean
    public ParamsDao getParamsDao() {
        return new ParamsDaoImpl(getJdbcTemplate());
    }

    @Bean
    public MetaModelService getMetaModelService() {
        return new MetaModelServiceImpl(getAttrsDao(), getParamsDao(), getObjectTypesDao(), getObjectsDao(), getGrantsDao());
    }
}
