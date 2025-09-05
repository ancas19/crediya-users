package co.com.crediya.users.r2dbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;

import java.util.Objects;

@Configuration
@EnableR2dbcAuditing(auditorAwareRef = "auditorAware")
public class R2dbcAuditConfig {
    @Bean
    public ReactiveAuditorAware<String> auditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext()
                .map(ctx -> {
                    if (Objects.isNull(ctx.getAuthentication())|| Objects.isNull(ctx.getAuthentication().getName())) {
                        return "SYSTEM";
                    }
                    return ctx.getAuthentication().getName();
                })
                .defaultIfEmpty("SYSTEM");
    }
}
