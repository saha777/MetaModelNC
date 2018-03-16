package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.EmployeesService;
import service.impls.EmployeeServiceImpl;

import javax.inject.Inject;

@Configuration
@Import({MetaModelConfig.class, DaoConfig.class})
public class ServiceConfig {
    private final MetaModelConfig metaModelConfig;
    private final DaoConfig daoConfig;

    @Inject
    public ServiceConfig(MetaModelConfig metaModelConfig, DaoConfig daoConfig) {
        this.metaModelConfig = metaModelConfig;
        this.daoConfig = daoConfig;
    }

    @Bean
    public EmployeesService getEmployeesDao(){
        return new EmployeeServiceImpl(
                daoConfig.getEmployeesDao(),
                metaModelConfig.getGrantsDao());
    }
}
