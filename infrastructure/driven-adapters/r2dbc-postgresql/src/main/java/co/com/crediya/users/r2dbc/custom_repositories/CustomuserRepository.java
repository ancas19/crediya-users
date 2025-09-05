package co.com.crediya.users.r2dbc.custom_repositories;

import co.com.crediya.users.model.users.models.UsersAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomuserRepository {
    private final DatabaseClient databaseClient;

    public Mono<UsersAuthentication> findByEmail(String email) {
        return databaseClient.sql("""
                SELECT
                    u.id,
                    u.identificacion,
                    u.nombres,
                    u.apellidos,
                    u.correo_electronico,
                    u.password,
                    r.nombre,
                    r.titulo
                FROM clients u
                INNER JOIN roles r ON r.id = u.role_id
                WHERE u.correo_electronico = :email
                  AND u.estado = 'ACTIVO'
                """)
                .bind("email", email)
                .map((row, meta) -> new UsersAuthentication(
                        row.get("id", UUID.class),
                        row.get("identificacion", String.class),
                        row.get("nombres", String.class),
                        row.get("apellidos", String.class),
                        row.get("correo_electronico", String.class),
                        row.get("password", String.class),
                        row.get("nombre", String.class),
                        row.get("titulo", String.class)
                ))
                .one();
    }
}
