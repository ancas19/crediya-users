package co.com.crediya.users.r2dbc.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostgreSQLConnectionTest {
    @Mock
    private ConnectionPool connectionPool;
    private PostgresqlConnectionProperties properties;
    private PostgreSQLConnection postgreSQLConnection;

    @BeforeEach
    void setUp(){
        properties = new PostgresqlConnectionProperties();
        properties.setHost("localhost");
        properties.setDatabase("testdb");
        properties.setUsername("user");
        properties.setPassword("password");
        properties.setPort(5432);
        properties.setMaxSize(10);
        properties.setPoolSize(5);
        properties.setMaxIdleTime(30);
        postgreSQLConnection=new PostgreSQLConnection(properties);
    }

    @Test
    void postgresConnectionFactoryTest(){
        PostgresqlConnectionFactory factory= postgreSQLConnection.postgresConnectionFactory();
        assertNotNull(factory);
    }

    @Test
    void connectionPoolTest(){
        ConnectionPool pool= postgreSQLConnection.connectionPool();
        assertNotNull(pool);
    }

    @Test
     void r2dbcTransactionManagerTest(){
        R2dbcTransactionManager transactionManager= postgreSQLConnection.transactionManager(connectionPool);
        assertNotNull(transactionManager);
    }

    @Test
    void databaseClientTest(){
        ConnectionPool pool= postgreSQLConnection.connectionPool();
         assertNotNull(postgreSQLConnection.databaseClient(pool));
    }

}