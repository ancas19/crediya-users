package co.com.crediya.users.api.exceptions;

import co.com.crediya.users.api.response.ErrorResponse;
import co.com.crediya.users.model.commos.exception.BadRequestException;
import co.com.crediya.users.model.commos.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomExceptionhandlerTest {
    @Mock
    private ServerWebExchange exchange;
    @Mock
    private ServerHttpRequest serverHttpRequest;
    @Mock
    private URI uri;
    @InjectMocks
    private CustomExceptionhandler customExceptionhandler;

    @BeforeEach
    void setUp(){
        when(exchange.getRequest()).thenReturn(serverHttpRequest);
        when(serverHttpRequest.getURI()).thenReturn(uri);
    }

    @Test
    void handleBadRequestTest(){
        //Arrange
        BadRequestException badRequestException = new BadRequestException("Bad Request");
        //Act
        Mono<ResponseEntity<ErrorResponse>> exceptionMono = customExceptionhandler.handleBadRequest(badRequestException, exchange);
        //Assert
        StepVerifier.create(exceptionMono)
                .expectNextMatches(responseEntity -> {
                    assertNotNull(responseEntity);
                    assertEquals(400, responseEntity.getStatusCodeValue());
                    assertNotNull(responseEntity.getBody());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void handleNotFoundExceptionTest(){
        //Arrange
        NotFoundException notFoundException = new NotFoundException("Not Found");
        //Act
        Mono<ResponseEntity<ErrorResponse>> exceptionMono = customExceptionhandler.handleNotFoundException(notFoundException, exchange);
        //Assert
        StepVerifier.create(exceptionMono)
                .expectNextMatches(responseEntity -> {
                    assertNotNull(responseEntity);
                    assertEquals(404, responseEntity.getStatusCodeValue());
                    assertNotNull(responseEntity.getBody());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void handleGenericExceptionTest(){
        //Arrange
        Exception exception = new Exception("Generic Exception");
        //Act
        Mono<ResponseEntity<ErrorResponse>> exceptionMono = customExceptionhandler.handleGlobalExceptions(exception, exchange);
        //Assert
        StepVerifier.create(exceptionMono)
                .expectNextMatches(responseEntity -> {
                    assertNotNull(responseEntity);
                    assertEquals(500, responseEntity.getStatusCodeValue());
                    assertNotNull(responseEntity.getBody());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void handleNoResourceFoundExceptionTest(){
        //Arrange
        NoResourceFoundException noResourceFoundException = new NoResourceFoundException("No Resource Found");
        //Act
        Mono<ResponseEntity<ErrorResponse>> exceptionMono = customExceptionhandler.handleNoResourceFoundException(noResourceFoundException, exchange);
        //Assert
        StepVerifier.create(exceptionMono)
                .expectNextMatches(responseEntity -> {
                    assertNotNull(responseEntity);
                    assertEquals(404, responseEntity.getStatusCodeValue());
                    assertNotNull(responseEntity.getBody());
                    return true;
                })
                .verifyComplete();
    }
}