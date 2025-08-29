package co.com.crediya.users.usecase.roles;

import co.com.crediya.users.model.commos.enums.ErrorMessages;
import co.com.crediya.users.model.commos.exception.NotFoundException;
import co.com.crediya.users.model.roles.gateways.RolesRepositoryPort;
import co.com.crediya.users.model.roles.models.Roles;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RolesUseCase {
    private final RolesRepositoryPort rolesRepositoryPort;

    public Mono<Roles> findByName(String name) {
        return rolesRepositoryPort.findByName(name)
                .switchIfEmpty(Mono.error(new NotFoundException(ErrorMessages.ERROR_MESSAGE_ROLE_NOT_FOUND.getMessage().formatted(name))));
    }
}
