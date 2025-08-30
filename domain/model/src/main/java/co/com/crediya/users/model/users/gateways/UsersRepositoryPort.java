package co.com.crediya.users.model.users.gateways;

import co.com.crediya.users.model.users.models.Users;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UsersRepositoryPort {
    Mono<Boolean> existsByEmail(String email);
    Mono<Boolean> existsByDocumentNumber(String documentNumber);
    Mono<Users> createUser(Users userInformation);
    Mono<Users> findByIdentification(String userIdentification);
    Flux<Users> finAll();
    Mono<Users> findById(UUID id);
}
