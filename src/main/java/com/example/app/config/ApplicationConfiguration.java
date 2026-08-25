package com.example.app.config;

import com.example.app.VhsApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = VhsApplication.class)
@EntityScan(basePackageClasses = VhsApplication.class)
public class ApplicationConfiguration {
}
