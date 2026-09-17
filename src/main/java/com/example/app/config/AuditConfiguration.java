package com.example.app.config;

import com.example.app.entity.User;
import io.vhs.security.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(
        auditorAwareRef = "userProvider",
        dateTimeProviderRef = "dateTimeProvider",
        modifyOnCreate = false)
public class AuditConfiguration {

    @Bean
    AuditorAware<User> userProvider() {
        return () -> Optional.ofNullable(Session.<User>getSession().getUser());
    }

    @Bean
    DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }
}
