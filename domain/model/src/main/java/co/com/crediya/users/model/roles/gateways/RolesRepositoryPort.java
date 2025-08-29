package co.com.crediya.users.model.roles.gateways;

import co.com.crediya.users.model.roles.models.Roles;
import reactor.core.publisher.Mono;

public interface RolesRepositoryPort {
    Mono<Roles> findByName(String name);
}
