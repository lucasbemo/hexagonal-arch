package com.lz.hexagonal.arch.application.infra;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@PropertySource("classpath:application-repo-${spring.profiles.active}.properties")
@ComponentScan("com.lz.hexagonal.arch")
@EnableJpaRepositories(basePackages = {"com.lz.hexagonal.arch.repo.mysql.person"})
@EntityScan(basePackages = {"com.lz.hexagonal.arch.repo.mysql.person"})
@Configuration
public class BeanConfig {

}
