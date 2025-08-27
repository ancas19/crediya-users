package co.com.crediya.users.r2dbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "adapters.r2dbc")
public record PostgresqlConnectionProperties(
        String host,
        Integer port,
        String database,
        String schema,
        String username,
        String password,
        Integer poolSize,
        Integer maxSize,
        Integer maxIdleTime,
        String validationQuery) {
}
