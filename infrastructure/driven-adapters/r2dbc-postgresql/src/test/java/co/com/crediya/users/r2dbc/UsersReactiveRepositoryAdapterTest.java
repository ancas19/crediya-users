package co.com.crediya.users.r2dbc;

import co.com.crediya.users.r2dbc.adapters.UsersReactiveRepositoryAdapter;
import co.com.crediya.users.r2dbc.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersReactiveRepositoryAdapterTest {
    // TODO: change four you own tests

    @InjectMocks
    UsersReactiveRepositoryAdapter repositoryAdapter;

    @Mock
    UsersRepository repository;

    @Mock
    ObjectMapper mapper;


}
