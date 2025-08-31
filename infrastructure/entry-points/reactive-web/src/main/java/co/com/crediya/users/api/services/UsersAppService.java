package co.com.crediya.users.api.services;

import co.com.crediya.users.api.response.UsersResponse;
import co.com.crediya.users.api.utils.Mapper;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import co.com.crediya.users.model.users.models.Users;
import co.com.crediya.users.usecase.users.UsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersAppService {
    private final UsersUseCase usersUseCase;

    @Transactional("r2dbcTransactionManager")
    public Mono<UsersResponse> createUser(Users userInformation) {
        return usersUseCase.createUser(userInformation)
                .map(Mapper::toResponse)
                .doOnSuccess(user->log.info("User created successfully with id: {} and email {}",user.getId(), user.getEmail()));
    }

    @Transactional("r2dbcTransactionManager")
    public Mono<UsersResponse> findByIdentification( String userIdentification) {
        return usersUseCase.findByIdentification(userIdentification)
                .map(Mapper::toResponse)
                .doOnSuccess(user->log.info("User found successfully with id: {} and email {}",user.getId(), user.getEmail()));
    }

    @Transactional("r2dbcTransactionManager")
    public Flux<UsersResponse> findAll() {
        return usersUseCase.finAll()
                .map(Mapper::toResponse)
                .doOnComplete(()->log.info("All users retrieved successfully"));
    }

    @Transactional("r2dbcTransactionManager")
    public Mono<UsersResponse> findById(UUID id) {
        return usersUseCase.findById(id)
                .map(Mapper::toResponse)
                .doOnSuccess(user->log.info("User found successfully with id: {} and email {}",user.getId(), user.getEmail()));
    }

    @Transactional("r2dbcTransactionManager")
    public Mono<UsersResponse> updateUser(Users userInformation) {
        return usersUseCase.updateUser(userInformation)
                .map(Mapper::toResponse)
                .doOnSuccess(user->log.info("User updated successfully with id: {} and email {}",user.getId(), user.getEmail()));
    }
    @Transactional("r2dbcTransactionManager")
    public Mono<Void> deleteUser(UUID id) {
        return usersUseCase.deleteUser(id)
                .doOnSuccess(v->log.info("User deleted successfully with id: {}",id));
    }
}
