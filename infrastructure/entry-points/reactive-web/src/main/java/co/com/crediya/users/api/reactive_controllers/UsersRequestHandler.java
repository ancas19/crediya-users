package co.com.crediya.users.api.reactive_controllers;

import co.com.crediya.users.api.request.UsersRequest;
import co.com.crediya.users.api.services.UsersAppService;
import co.com.crediya.users.api.utils.CustomValidator;
import co.com.crediya.users.api.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UsersRequestHandler {
    private final CustomValidator validator;
    private final UsersAppService usersAppService;

    public Mono<ServerResponse> createUser(ServerRequest request) {
        return request.bodyToMono(UsersRequest.class)
                .flatMap(this.validator::validate)
                .map(Mapper::toModel)
                .flatMap(usersAppService::createUser)
                .flatMap(savedUser->ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedUser)
                );


    }
}
