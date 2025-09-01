package co.com.crediya.users.api.reactive_controllers;

import co.com.crediya.users.api.request.IdentificationRequest;
import co.com.crediya.users.api.request.UsersRequest;
import co.com.crediya.users.api.response.UsersResponse;
import co.com.crediya.users.api.services.UsersAppService;
import co.com.crediya.users.api.utils.CustomValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersRequestHandlerTest {
    @Mock
    private ServerRequest serverRequest;
    @Mock
    private UsersAppService usersAppService;
    @Mock
    private   CustomValidator validator;
    @InjectMocks
    private UsersRequestHandler usersRequestHandler;
    private UsersRequest usersRequest;
    private UsersResponse usersResponse;
    private IdentificationRequest identificationRequest;

    @BeforeEach
    void setUp() {
        usersRequest = new UsersRequest();
        usersResponse = new UsersResponse();
        identificationRequest = new IdentificationRequest();
        usersRequestHandler= new UsersRequestHandler(validator, usersAppService);
    }

    @Test
    void createUserReturnsCreatedResponseWhenValidRequest() {
        // Arrange
        when(validator.validate(any(UsersRequest.class))).thenReturn(Mono.just(usersRequest));
        when(usersAppService.createUser(any())).thenReturn(Mono.just(usersResponse));
        when(serverRequest.bodyToMono(UsersRequest.class)).thenReturn(Mono.just(usersRequest));

        // Act & Assert
        StepVerifier.create(usersRequestHandler.createUser(serverRequest))
                .expectNextMatches(serverResponse ->
                        serverResponse.statusCode().equals(HttpStatus.CREATED))
                .verifyComplete();
    }


    @Test
    void finduserByIdentificaction() {
        // Arrange
        identificationRequest.setIdentification(UUID.randomUUID().toString());
        when(validator.validate(any())).thenReturn(Mono.just(identificationRequest));
        when(usersAppService.findByIdentification(any())).thenReturn(Mono.just(usersResponse));
        when(serverRequest.bodyToMono(IdentificationRequest.class)).thenReturn(Mono.just(identificationRequest));

        // Act & Assert
        StepVerifier.create(usersRequestHandler.findUserByIdentification(serverRequest))
                .expectNextMatches(serverResponse -> serverResponse.statusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }


    @Test
    void findAll(){
        // Arrange
        when(usersAppService.findAll()).thenReturn(Mono.just(usersResponse).flux());
        // Act & Assert
        StepVerifier.create(usersRequestHandler.findAll(serverRequest))
                .expectNextMatches(serverResponse ->
                        serverResponse.statusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }

    @Test
    void findById(){
        // Arrange
        when(usersAppService.findById(any())).thenReturn(Mono.just(usersResponse));
        when(serverRequest.pathVariable("id")).thenReturn(UUID.randomUUID().toString());
        // Act & Assert
        StepVerifier.create(usersRequestHandler.findById(serverRequest))
                .expectNextMatches(serverResponse ->
                        serverResponse.statusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }

    @Test
    void updateUser() {
        // Arrange
        when(validator.validate(any(UsersRequest.class))).thenReturn(Mono.just(usersRequest));
        when(usersAppService.updateUser(any())).thenReturn(Mono.just(usersResponse));
        when(serverRequest.bodyToMono(UsersRequest.class)).thenReturn(Mono.just(usersRequest));
        // Act & Assert
        StepVerifier.create(usersRequestHandler.updateUser(serverRequest))
                .expectNextMatches(serverResponse ->
                        serverResponse.statusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }

    @Test
    void deleteUser() {
        // Arrange
        when(usersAppService.deleteUser(any())).thenReturn(Mono.just(usersResponse));
        when(serverRequest.pathVariable("id")).thenReturn(UUID.randomUUID().toString());
        // Act & Assert
        StepVerifier.create(usersRequestHandler.deleteUser(serverRequest))
                .expectNextMatches(serverResponse ->
                        serverResponse.statusCode().equals(HttpStatus.OK))
                .verifyComplete();
    }
}