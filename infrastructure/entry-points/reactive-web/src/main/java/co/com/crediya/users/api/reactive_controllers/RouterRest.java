package co.com.crediya.users.api.reactive_controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    private final UsersRequestHandler usersRequestHandler;
    private final AuthenticationRequestHandler authenticationRequestHandler;

    public RouterRest(UsersRequestHandler usersRequestHandler, AuthenticationRequestHandler authenticationRequestHandler) {
        this.usersRequestHandler = usersRequestHandler;
        this.authenticationRequestHandler = authenticationRequestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .POST("/users",usersRequestHandler::createUser)
                .GET("/users",usersRequestHandler::findAll)
                .GET("/users/{id}",usersRequestHandler::findById)
                .POST("/users/documents", usersRequestHandler::findUserByIdentification)
                .PUT("/users", usersRequestHandler::updateUser)
                .DELETE("/users/{id}", usersRequestHandler::deleteUser)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerAuthenticationFunction(){
        return route()
                .POST("/auth/login", authenticationRequestHandler::login)
                .build();
    }
}
