package ru.ryazan.senatorshop.core.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "ru.ryazan.senatorshop.core.repository")
@ComponentScan(basePackages = {"ru.ryazan.senatorshop.core"})
@EntityScan("ru.ryazan.senatorshop.core")
@EnableAutoConfiguration
@PropertySource("application-core.properties")
public class CoreConfig {
    public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
