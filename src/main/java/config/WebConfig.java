package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "controller")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver getViewResolver(){
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setOrder(1);
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setPrefix("");
        return freeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer getFreemarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths(
                "/",
                "/WEB-INF/views/",
                "/WEB-INF/views/employee",
                "/WEB-INF/views/department",
                "/WEB-INF/views/location",
                "/WEB-INF/views/office");
        return freeMarkerConfigurer;
    }
}
