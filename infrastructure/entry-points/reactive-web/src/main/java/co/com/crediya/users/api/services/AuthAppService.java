package co.com.crediya.users.api.services;

import co.com.crediya.users.api.response.TokenResponse;
import co.com.crediya.users.api.utils.Mapper;
import co.com.crediya.users.model.commos.models.Authentication;
import co.com.crediya.users.usecase.authentication.AuthenticationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthAppService {
    private final AuthenticationUseCase authenticationUseCase;

    @Transactional("r2dbcTransactionManager")
    public Mono<TokenResponse> login(Authentication authentication) {
        return authenticationUseCase.authenticate(authentication)
                .map(Mapper::toTokenResponse)
                .doOnSuccess(user->log.info("User logged in successfully with email {}",user.getEmail()));
    }
}
