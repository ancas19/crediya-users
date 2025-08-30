package co.com.crediya.users.r2dbc.adapters;

import co.com.crediya.users.model.users.gateways.UsersRepositoryPort;
import co.com.crediya.users.model.users.models.Users;
import co.com.crediya.users.r2dbc.entity.UsersEntity;
import co.com.crediya.users.r2dbc.repository.UsersRepository;
import co.com.crediya.users.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UsersReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Users,
        UsersEntity,
        UUID,
        UsersRepository
> implements UsersRepositoryPort {
    public UsersReactiveRepositoryAdapter(UsersRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Users.class/* change for domain model */));
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Mono<Boolean> existsByDocumentNumber(String documentNumber) {
        return repository.existsByIdentification(documentNumber);
    }

    @Override
    public Mono<Users> createUser(Users userInformation) {
        return this.repository.save(this.mapper.map(userInformation, UsersEntity.class))
                .map(entity -> this.mapper.map(entity, Users.class));
    }

    @Override
    public Mono<Users> findByIdentification(String userIdentification) {
        return this.repository.findByIdentification(userIdentification)
                .map(entity -> this.mapper.map(entity, Users.class));
    }
}
