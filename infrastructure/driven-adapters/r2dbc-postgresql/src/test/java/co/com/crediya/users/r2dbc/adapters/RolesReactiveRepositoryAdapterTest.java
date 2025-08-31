package co.com.crediya.users.r2dbc.adapters;

import co.com.crediya.users.model.commos.utils.MocksTest;
import co.com.crediya.users.model.roles.models.Roles;
import co.com.crediya.users.r2dbc.entity.RolesEntity;
import co.com.crediya.users.r2dbc.repository.RolesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolesReactiveRepositoryAdapterTest {
    @Mock
    private RolesRepository repository;
    @Mock
    private ObjectMapper mapper;
    @InjectMocks
    private RolesReactiveRepositoryAdapter adapter;
    private RolesEntity rolesEntity;
    private Roles roles;

    @BeforeEach
    void setUp() {
        roles= MocksTest.createRole();
        rolesEntity = new RolesEntity(UUID.randomUUID(), "Admin", "Administrator");
        adapter = new RolesReactiveRepositoryAdapter(repository, mapper);
    }

    @Test
    void  findByName() {
        //Arrange
        when(repository.findByName(anyString())).thenReturn(Mono.just(rolesEntity));
        when(mapper.map(rolesEntity, Roles.class)).thenReturn(roles);
        //Act and Assert
        StepVerifier.create(adapter.findByName("Admin"))
                .expectNextMatches(role -> {
                    assertNotNull(role);
                    assertNotNull(role.getId());
                    assertEquals(roles.getName(), role.getName());
                    assertEquals(roles.getTitle(), role.getTitle());
                    return true;
                })
                .verifyComplete();
    }
}