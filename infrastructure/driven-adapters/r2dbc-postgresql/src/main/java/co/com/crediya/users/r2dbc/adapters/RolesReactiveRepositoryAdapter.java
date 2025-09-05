package co.com.crediya.users.r2dbc.adapters;

import co.com.crediya.users.model.roles.gateways.RolesRepositoryPort;
import co.com.crediya.users.model.roles.models.Roles;
import co.com.crediya.users.r2dbc.entity.RolesEntity;
import co.com.crediya.users.r2dbc.helper.ReactiveAdapterOperations;
import co.com.crediya.users.r2dbc.repository.RolesRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class RolesReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Roles,
        RolesEntity,
        UUID,
        RolesRepository
> implements RolesRepositoryPort {
    public RolesReactiveRepositoryAdapter(RolesRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Roles.class/* change for domain model */));
    }

    @Override
    public Mono<Roles> findByName(String name) {
        return repository.findByName(name)
                .map(rolesFound->mapper.map(rolesFound,Roles.class));
    }
}
