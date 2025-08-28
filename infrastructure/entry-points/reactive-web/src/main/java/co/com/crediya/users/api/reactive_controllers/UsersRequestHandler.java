package co.com.crediya.users.api.reactive_controllers;

import co.com.crediya.users.api.utils.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UsersRequestHandler {

    private final CustomValidator validator;
//private  final UseCase useCase;
//private  final UseCase2 useCase2;
     public Mono<ServerResponse> createUser(ServerRequest  request){

     }
}
