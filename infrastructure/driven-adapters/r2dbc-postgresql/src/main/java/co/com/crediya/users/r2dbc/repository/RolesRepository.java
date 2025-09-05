package co.com.crediya.users.r2dbc.repository;

import co.com.crediya.users.r2dbc.entity.RolesEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RolesRepository extends ReactiveCrudRepository<RolesEntity, UUID>, ReactiveQueryByExampleExecutor<RolesEntity> {

    Mono<RolesEntity> findByName(String name);
}
