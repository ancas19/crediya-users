package co.com.crediya.users.r2dbc.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ConfigurationProperties(prefix = "adapters.r2dbc")
public class PostgresqlConnectionProperties{
    private String host;
    private Integer port;
    private String database;
    private String schema;
    private String username;
    private String password;
    private Integer poolSize;
    private Integer maxSize;
    private Integer maxIdleTime;
    private String validationQuery;
}
