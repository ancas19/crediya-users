package co.com.crediya.users.api.services;

import co.com.crediya.users.model.commos.utils.MocksTest;
import co.com.crediya.users.model.users.models.Users;
import co.com.crediya.users.usecase.users.UsersUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersAppServiceTest {
    @Mock
    private UsersUseCase usersUseCase;
    @InjectMocks
    private UsersAppService usersAppService;
    private Users userInformation;

    @BeforeEach
    void setUp(){
        userInformation= MocksTest.createUser();
    }

    @Test
    void createUser(){
        //Arrange
        when(usersUseCase.createUser(userInformation)).thenReturn(Mono.just(userInformation));
        //Act and Assert
        StepVerifier.create(this.usersAppService.createUser(userInformation))
                .expectNextMatches(user -> {
                    assertNotNull(user);
                    assertNotNull(user.getId());
                    assertEquals(userInformation.getEmail(), user.getEmail());
                    assertEquals(userInformation.getNames(), user.getNames());
                    assertEquals(userInformation.getLastName(), user.getLastName());
                    assertEquals(userInformation.getIdentification(), user.getIdentification());
                    assertEquals(userInformation.getAddress(), user.getAddress());
                    assertEquals(userInformation.getPhone(), user.getPhone());
                    assertEquals(userInformation.getBirthDate(), user.getBirthDate());
                    assertEquals(userInformation.getBaseSalary(), user.getBaseSalary());
                    return true;
                })
                .verifyComplete();
    }
    @Test
    void findByIdentification(){
        //Arrange
        when(usersUseCase.findByIdentification(userInformation.getIdentification())).thenReturn(Mono.just(userInformation));
        //Act and Assert
        StepVerifier.create(this.usersAppService.findByIdentification(userInformation.getIdentification()))
                .expectNextMatches(user -> {
                    assertNotNull(user);
                    assertNotNull(user.getId());
                    assertEquals(userInformation.getEmail(), user.getEmail());
                    assertEquals(userInformation.getNames(), user.getNames());
                    assertEquals(userInformation.getLastName(), user.getLastName());
                    assertEquals(userInformation.getIdentification(), user.getIdentification());
                    assertEquals(userInformation.getAddress(), user.getAddress());
                    assertEquals(userInformation.getPhone(), user.getPhone());
                    assertEquals(userInformation.getBirthDate(), user.getBirthDate());
                    assertEquals(userInformation.getBaseSalary(), user.getBaseSalary());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void findByAll(){
        //Arrange
        when(usersUseCase.finAll()).thenReturn(Mono.just(userInformation).flux());
        //Act and Assert
        StepVerifier.create(this.usersAppService.findAll())
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findById(){
        //Arrange
        when(usersUseCase.findById(userInformation.getId())).thenReturn(Mono.just(userInformation));
        //Act and Assert
        StepVerifier.create(this.usersAppService.findById(userInformation.getId()))
                .expectNextMatches(user -> {
                    assertNotNull(user);
                    assertNotNull(user.getId());
                    assertEquals(userInformation.getEmail(), user.getEmail());
                    assertEquals(userInformation.getNames(), user.getNames());
                    assertEquals(userInformation.getLastName(), user.getLastName());
                    assertEquals(userInformation.getIdentification(), user.getIdentification());
                    assertEquals(userInformation.getAddress(), user.getAddress());
                    assertEquals(userInformation.getPhone(), user.getPhone());
                    assertEquals(userInformation.getBirthDate(), user.getBirthDate());
                    assertEquals(userInformation.getBaseSalary(), user.getBaseSalary());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void updateUser(){
        //Arrange
        when(usersUseCase.updateUser(any())).thenReturn(Mono.just(userInformation));
        //Act and Assert
        StepVerifier.create(this.usersAppService.updateUser(userInformation))
                .expectNextMatches(user -> {
                    assertNotNull(user);
                    assertNotNull(user.getId());
                    assertEquals(userInformation.getEmail(), user.getEmail());
                    assertEquals(userInformation.getNames(), user.getNames());
                    assertEquals(userInformation.getLastName(), user.getLastName());
                    assertEquals(userInformation.getIdentification(), user.getIdentification());
                    assertEquals(userInformation.getAddress(), user.getAddress());
                    assertEquals(userInformation.getPhone(), user.getPhone());
                    assertEquals(userInformation.getBirthDate(), user.getBirthDate());
                    assertEquals(userInformation.getBaseSalary(), user.getBaseSalary());
                    return true;
                })
                .verifyComplete();
    }


    @Test
    void deleteUser(){
        //Arrange
        when(usersUseCase.deleteUser(userInformation.getId())).thenReturn(Mono.empty());
        //Act and Assert
        StepVerifier.create(this.usersAppService.deleteUser(userInformation.getId()))
                .verifyComplete();
    }
}