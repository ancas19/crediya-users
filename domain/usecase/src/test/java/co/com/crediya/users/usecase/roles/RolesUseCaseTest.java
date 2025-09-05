package co.com.crediya.users.usecase.roles;

import co.com.crediya.users.model.commos.enums.ErrorMessages;
import co.com.crediya.users.model.commos.utils.MocksTest;
import co.com.crediya.users.model.roles.gateways.RolesRepositoryPort;
import co.com.crediya.users.model.roles.models.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolesUseCaseTest {
    @Mock
    private RolesRepositoryPort rolesRepositoryPort;
    @InjectMocks
    private RolesUseCase rolesUseCase;
    private Roles roles;
    @BeforeEach
    void setUp(){
        roles= MocksTest.createRole();
    }

    @Test
    void findByName() {
        //Arrange
        when(rolesRepositoryPort.findByName(anyString())).thenReturn(Mono.just(roles));
        //Act
        Mono<Roles> roleFound= rolesUseCase.findByName("Admin");
        //Assert
        StepVerifier.create(roleFound)
                .expectNextMatches(role -> {
                    assertNotNull(role);
                    assertNotNull(role.getId());
                    assertEquals(roles.getName(), role.getName());
                    assertEquals(roles.getTitle(), role.getTitle());
                    return true;
                })
                .verifyComplete();
    }


    @Test
    void findByNameNotFound() {
        //Arrange
        when(rolesRepositoryPort.findByName(anyString())).thenReturn(Mono.empty());
        //Act
        String roleName="NonExistentRole";
        Mono<Roles> roleFound= rolesUseCase.findByName(roleName);
        //Assert
        StepVerifier.create(roleFound)
                .expectErrorMatches(throwable ->
                        throwable instanceof Exception &&
                        throwable.getMessage().equals(ErrorMessages.ERROR_MESSAGE_ROLE_NOT_FOUND.getMessage().formatted(roleName))
                )
                .verify();
    }
}