package co.com.crediya.users.api.reactive_controllers;

import co.com.crediya.users.api.request.AuthenticationRequest;
import co.com.crediya.users.api.request.IdentificationRequest;
import co.com.crediya.users.api.request.UsersRequest;
import co.com.crediya.users.api.response.UsersResponse;
import co.com.crediya.users.api.services.UsersAppService;
import co.com.crediya.users.api.utils.CustomValidator;
import co.com.crediya.users.api.utils.Mapper;
import co.com.crediya.users.usecase.authentication.AuthenticationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationRequestHandler {
    private final CustomValidator customValidator;
    private final AuthenticationUseCase authenticationUseCase;

    public Mono<ServerResponse> login(ServerRequest request){
        return request.bodyToMono(AuthenticationRequest.class)
                .flatMap(customValidator::validate)
                .map(Mapper::toAuthenticationModel)
                .flatMap(authenticationUseCase::authnticate)
                .flatMap(tokenCreated->ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(tokenCreated)
                );

    }

}
