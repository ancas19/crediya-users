package co.com.crediya.users.api.services;

import co.com.crediya.users.api.response.UsersResponse;
import co.com.crediya.users.api.utils.Mapper;
import org.springframework.transaction.annotation.Transactional;
import co.com.crediya.users.model.users.models.Users;
import co.com.crediya.users.usecase.users.UsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UsersAppService {
    private final UsersUseCase usersUseCase;

    @Transactional("r2dbcTransactionManager")
    public Mono<UsersResponse> createUser(Users userInformation) {
        return usersUseCase.createUser(userInformation)
                .map(Mapper::toResponse);
    }
}
