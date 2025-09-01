package co.com.crediya.users.r2dbc.adapters;

import co.com.crediya.users.model.commos.utils.MocksTest;
import co.com.crediya.users.model.users.models.Users;
import co.com.crediya.users.r2dbc.entity.UsersEntity;
import co.com.crediya.users.r2dbc.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersReactiveRepositoryAdapterTest {
    @Mock
    private UsersRepository repository;
    @Mock
    private ObjectMapper mapper;
    @InjectMocks
    private UsersReactiveRepositoryAdapter adapter;
    private UsersEntity usersEntity;
    private Users users;

    @BeforeEach
    void setUp() {
        users = MocksTest.createUser();
        usersEntity = UsersEntity.builder()
                .id(UUID.randomUUID())
                .identification("1234567890")
                .names("John")
                .lastName("Doe")
                .birthDate(LocalDate.of(1990, 4, 15))
                .address("123 Main St")
                .phone("+12345678901")
                .baseSalary(new BigDecimal("5000.00"))
                .email("john.doe@email.com")
                .status("ACTIVE")
                .password("StrongPass123")
                .roleId(UUID.randomUUID())
                .build();
        adapter = new UsersReactiveRepositoryAdapter(repository, mapper);
    }

    @Test
    void testExistsByEmail() {
        //Arrange
        when(repository.existsByEmail(anyString())).thenReturn(Mono.just(true));
        //Act and Assert
        StepVerifier.create(adapter.existsByEmail(users.getEmail()))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void testExistsByDocumentNumber() {
        //Arrange
        when(repository.existsByIdentification(anyString())).thenReturn(Mono.just(true));
        //Act and Assert
        StepVerifier.create(adapter.existsByDocumentNumber(users.getIdentification()))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void existsByEmailAndNotId() {
        //Arrange
        when(repository.existsByemailAndIdNot(anyString(),any())).thenReturn(Mono.just(true));
        //Act and Assert
        StepVerifier.create(adapter.existsByEmailAndNotId(users.getEmail(), users.getId()))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void existsByDocumentNumberAndNotId() {
        //Arrange
        when(repository.existsByIdentificationAndIdNot(anyString(), any())).thenReturn(Mono.just(true));
        //Act and Assert
        StepVerifier.create(adapter.existsByDocumentNumberAndNotId(users.getIdentification(), users.getId()))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void createUser() {
        //Arrange
        when(repository.save(any())).thenReturn(Mono.just(usersEntity));
        when(mapper.map(users, UsersEntity.class)).thenReturn(usersEntity);
        when(mapper.map(usersEntity, Users.class)).thenReturn(users);
        //Act and Assert
        StepVerifier.create(adapter.createUser(users))
                .expectNextMatches(user -> {
                            assertNotNull(user);
                            assertNotNull(user.getId());
                            return true;
                        }
                ).verifyComplete();
    }

    @Test
    void findByIdentification() {
        //Arrange
        when(repository.findByIdentification(anyString())).thenReturn(Mono.just(usersEntity));
        when(mapper.map(usersEntity, Users.class)).thenReturn(users);
        //Act and Assert
        StepVerifier.create(adapter.findByIdentification("1234567890"))
                .expectNextMatches(user -> {
                            assertNotNull(user);
                            assertNotNull(user.getId());
                            return true;
                        }
                ).verifyComplete();
    }

    @Test
    void finAll() {
        //Arrange
        when(repository.findAll()).thenReturn(Mono.just(usersEntity).flux());
        when(mapper.map(usersEntity, Users.class)).thenReturn(users);
        //Act and Assert
        StepVerifier.create(adapter.finAll())
                .expectNextMatches(user -> {
                            assertNotNull(user);
                            assertNotNull(user.getId());
                            return true;
                        }
                ).verifyComplete();
    }

}