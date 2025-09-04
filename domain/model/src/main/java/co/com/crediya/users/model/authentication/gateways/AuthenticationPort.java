package co.com.crediya.users.model.authentication.gateways;

import reactor.core.publisher.Mono;

public interface AuthenticationPort {
    Mono<Void> authenticate(String email, String password);
}
