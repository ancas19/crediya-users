package co.com.crediya.users.r2dbc.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.ReactiveAuditorAware;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class R2dbcAuditConfigTest {

    @Test
    void returnsSystemWhenNoAuthenticationContext() {
        R2dbcAuditConfig config = new R2dbcAuditConfig();
        ReactiveAuditorAware<String> auditorAware = config.auditorAware();
        StepVerifier.create(auditorAware.getCurrentAuditor())
                .expectNext("SYSTEM")
                .verifyComplete();
    }

    @Test
    void returnsSystemWhenAuthenticationIsNull() {
        R2dbcAuditConfig config = new R2dbcAuditConfig();
        ReactiveAuditorAware<String> auditorAware = config.auditorAware();
        StepVerifier.create(auditorAware.getCurrentAuditor())
                .expectNext("SYSTEM")
                .verifyComplete();
    }

    @Test
    void returnsSystemWhenAuthenticationNameIsNull() {
        R2dbcAuditConfig config = new R2dbcAuditConfig();
        ReactiveAuditorAware<String> auditorAware = config.auditorAware();
        StepVerifier.create(auditorAware.getCurrentAuditor())
                .expectNext("SYSTEM")
                .verifyComplete();
    }

}