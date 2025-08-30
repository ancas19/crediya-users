package co.com.crediya.users.r2dbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import reactor.core.publisher.Mono;

@Configuration
@EnableR2dbcAuditing(auditorAwareRef = "auditorAware")
public class R2dbcAuditConfig {
    @Bean
    public ReactiveAuditorAware<String> auditorAware() {
        return () -> {
            return Mono.justOrEmpty("ADMIN") // Example
                    .switchIfEmpty(Mono.just("SYSTEM"));
        };
    }
}
