package co.com.crediya.users.usecase.users;

import co.com.crediya.users.model.commos.enums.ErrorMessages;
import co.com.crediya.users.model.commos.utils.MocksTest;
import co.com.crediya.users.model.users.gateways.UsersRepositoryPort;
import co.com.crediya.users.model.users.models.Users;
import co.com.crediya.users.usecase.roles.RolesUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static co.com.crediya.users.model.commos.enums.Constants.INACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersUseCaseTest {

    @Mock
    private UsersRepositoryPort usersRepositoryPort;
    @Mock
    private RolesUseCase rolesUseCase;
    @InjectMocks
    private UsersUseCase usersUseCase;
    private Users user;

    @BeforeEach
    void setUp() {
        user = MocksTest.createUser();
        usersUseCase = new UsersUseCase(usersRepositoryPort, rolesUseCase);
    }

    @Test
    void createUserTest() {
        //Arrange
        when(usersRepositoryPort.existsByEmail(anyString())).thenReturn(Mono.just(false));
        when(usersRepositoryPort.existsByDocumentNumber(anyString())).thenReturn(Mono.just(false));
        when(rolesUseCase.findByName(anyString())).thenReturn(Mono.just(MocksTest.createRole()));
        when(usersRepositoryPort.createUser(any())).thenReturn(Mono.just(user));
        //Act
        Mono<Users> userCreated = usersUseCase.createUser(user);
        //Assert
        StepVerifier.create(userCreated)
                .expectNextMatches(user -> {
                            assertNotNull(user);
                            assertNotNull(user.getId());
                            return true;
                        }
                ).verifyComplete();
    }

    @Test
    void createUserIdentificationExistsTest() {
        //Arrange
        when(usersRepositoryPort.existsByEmail(anyString())).thenReturn(Mono.just(false));
        when(usersRepositoryPort.existsByDocumentNumber(anyString())).thenReturn(Mono.just(true));
        when(rolesUseCase.findByName(anyString())).thenReturn(Mono.just(MocksTest.createRole()));

        //Act
        Mono<Users> userCreated = usersUseCase.createUser(user);
        //Assert
        StepVerifier.create(userCreated)
                .expectErrorMatches(exception -> exception.getMessage().equals(ErrorMessages.ERROR_MESSAGE_DOCUMENT_ALREADY_EXISTS.getMessage()))
                .verify();
    }


    @Test
    void createUserAgeErrorTest() {
        //Arrange
        when(usersRepositoryPort.existsByEmail(anyString())).thenReturn(Mono.just(false));
        when(usersRepositoryPort.existsByDocumentNumber(anyString())).thenReturn(Mono.just(true));
        when(rolesUseCase.findByName(anyString())).thenReturn(Mono.just(MocksTest.createRole()));
        user.setBirthDate(LocalDate.now());
        //Act
        Mono<Users> userCreated = usersUseCase.createUser(user);
        //Assert
        StepVerifier.create(userCreated)
                .expectErrorMatches(exception -> exception.getMessage().equals(ErrorMessages.ERROR_MESSAGE_UNDERAGE.getMessage()))
                .verify();
    }

    @Test
    void findAll(){
        //Arrange
        when(usersRepositoryPort.finAll()).thenReturn(Mono.just(user).flux());
        //Act
        var usersFound= usersUseCase.finAll();
        //Assert
        StepVerifier.create(usersFound)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void createUserEmailExistsTest() {
        //Arrange
        when(usersRepositoryPort.existsByEmail(anyString())).thenReturn(Mono.just(true));
        when(usersRepositoryPort.existsByDocumentNumber(anyString())).thenReturn(Mono.just(true));
        when(rolesUseCase.findByName(anyString())).thenReturn(Mono.just(MocksTest.createRole()));
        //Act
        Mono<Users> userCreated = usersUseCase.createUser(user);
        //Assert
        StepVerifier.create(userCreated)
                .expectErrorMatches(exception -> exception.getMessage().equals(ErrorMessages.ERROR_MESSAGE_EMAIL_ALREADY_EXISTS.getMessage()))
                .verify();
    }

    @Test
    void findByIdentificationTest() {
        //Arrange
        when(usersRepositoryPort.findByIdentification(anyString())).thenReturn(Mono.just(user));
        //Act
        Mono<Users> userFound = usersUseCase.findByIdentification(user.getIdentification());
        //Assert
        StepVerifier.create(userFound)
                .expectNextMatches(u -> {
                    assertNotNull(u);
                    assertNotNull(u.getId());
                    assertEquals(user.getEmail(), u.getEmail());
                    assertEquals(user.getNames(), u.getNames());
                    assertEquals(user.getLastName(), u.getLastName());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void findByIdentificationNotFoundTest() {
        //Arrange
        when(usersRepositoryPort.findByIdentification(anyString())).thenReturn(Mono.empty());
        //Act
        String userIdentification="NonExistentID";
        Mono<Users> userFound = usersUseCase.findByIdentification(userIdentification);
        //Assert
        StepVerifier.create(userFound)
                .expectErrorMatches(exception -> exception.getMessage().equals(ErrorMessages.ERROR_MESSAGE_USER_NOT_FOUND.getMessage().formatted(userIdentification)))
                .verify();
    }

    @Test
    void upateUserTest() {
        //Arrange
        when(usersRepositoryPort.findById(any())).thenReturn(Mono.just(user));
        when(usersRepositoryPort.existsByEmailAndNotId(anyString(),any())).thenReturn(Mono.just(false));
        when(usersRepositoryPort.existsByDocumentNumberAndNotId(anyString(),any())).thenReturn(Mono.just(false));
        when(rolesUseCase.findByName(anyString())).thenReturn(Mono.just(MocksTest.createRole()));
        when(usersRepositoryPort.createUser(any())).thenReturn(Mono.just(user));
        //Act
        Mono<Users> userCreated = usersUseCase.updateUser(user);
        //Assert
        StepVerifier.create(userCreated)
                .expectNextMatches(user -> {
                            assertNotNull(user);
                            assertNotNull(user.getId());
                            return true;
                        }
                ).verifyComplete();
    }

    @Test
    void upateUserIdentificationExistsTest() {
        //Arrange
        when(usersRepositoryPort.findById(any())).thenReturn(Mono.just(user));
        when(usersRepositoryPort.existsByEmailAndNotId(anyString(),any())).thenReturn(Mono.just(false));
        when(usersRepositoryPort.existsByDocumentNumberAndNotId(anyString(),any())).thenReturn(Mono.just(true));
        when(rolesUseCase.findByName(anyString())).thenReturn(Mono.just(MocksTest.createRole()));
        //Act
        Mono<Users> userCreated = usersUseCase.updateUser(user);
        //Assert
        StepVerifier.create(userCreated)
                .expectErrorMatches(exception -> exception.getMessage().equals(ErrorMessages.ERROR_MESSAGE_DOCUMENT_ALREADY_EXISTS.getMessage()))
                .verify();
    }


    @Test
    void updateUserAgeErrorTest() {
        //Arrange
        when(usersRepositoryPort.findById(any())).thenReturn(Mono.just(user));
        when(usersRepositoryPort.existsByEmailAndNotId(anyString(),any())).thenReturn(Mono.just(false));
        when(usersRepositoryPort.existsByDocumentNumberAndNotId(anyString(),any())).thenReturn(Mono.just(true));
        when(rolesUseCase.findByName(anyString())).thenReturn(Mono.just(MocksTest.createRole()));
        user.setBirthDate(LocalDate.now());
        //Act
        Mono<Users> userCreated = usersUseCase.updateUser(user);
        //Assert
        StepVerifier.create(userCreated)
                .expectErrorMatches(exception -> exception.getMessage().equals(ErrorMessages.ERROR_MESSAGE_UNDERAGE.getMessage()))
                .verify();
    }

    @Test
    void upateUserEmailExistsTest() {
        //Arrange
        when(usersRepositoryPort.findById(any())).thenReturn(Mono.just(user));
        when(usersRepositoryPort.existsByEmailAndNotId(anyString(),any())).thenReturn(Mono.just(true));
        when(usersRepositoryPort.existsByDocumentNumberAndNotId(anyString(),any())).thenReturn(Mono.just(true));
        when(rolesUseCase.findByName(anyString())).thenReturn(Mono.just(MocksTest.createRole()));
        //Act
        Mono<Users> userCreated = usersUseCase.updateUser(user);
        //Assert
        StepVerifier.create(userCreated)
                .expectErrorMatches(exception -> exception.getMessage().equals(ErrorMessages.ERROR_MESSAGE_EMAIL_ALREADY_EXISTS.getMessage()))
                .verify();
    }

    @Test
    void deleteUser(){
        //Arrange
        when(usersRepositoryPort.findById(any())).thenReturn(Mono.just(user));
        when(usersRepositoryPort.createUser(any())).thenReturn(Mono.just(user));
        //Act
        Mono<Users> userDeleted= usersUseCase.deleteUser(user.getId());
        //Assert
        StepVerifier.create(userDeleted)
                .expectNextMatches(u -> {
                    assertNotNull(u);
                    assertNotNull(u.getId());
                    assertEquals(INACTIVE.getValue(), u.getStatus());
                    return true;
                })
                .verifyComplete();
    }

}