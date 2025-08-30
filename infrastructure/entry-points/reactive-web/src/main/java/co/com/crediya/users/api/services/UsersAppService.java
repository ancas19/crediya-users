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
import reactor.core.publisher.Mono;

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

    public Mono<UsersResponse> findByIdentification( String userIdentification) {
        return usersUseCase.findByIdentification(userIdentification)
                .map(Mapper::toResponse)
                .doOnSuccess(user->log.info("User found successfully with id: {} and email {}",user.getId(), user.getEmail()));
    }
}
