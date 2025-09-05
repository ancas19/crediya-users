package co.com.crediya.users.jwt.secutiry;

import co.com.crediya.users.jwt.config.SecurityProperties;
import co.com.crediya.users.jwt.filters.JwtAuthenticationFilter;
import co.com.crediya.users.usecase.jwt.JWTUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static co.com.crediya.users.model.commos.enums.Endpoints.AUTH_LOGIN;
import static co.com.crediya.users.model.commos.enums.Endpoints.USERS_ENDPOINT;
import static co.com.crediya.users.model.commos.enums.Roles.ADMIN;
import static co.com.crediya.users.model.commos.enums.Roles.ASESOR;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurity {
    private final SecurityProperties securityProperties;

    public WebSecurity(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    public SecurityWebFilterChain httpSecurityFilterChain(ServerHttpSecurity http, ReactiveAuthenticationManager authenticationManager, JWTUseCase jwtService) {
        Set<String> publicUrls = new HashSet<>(securityProperties.getPublicPaths());
        return http.authorizeExchange(
                        exchanges -> exchanges
                                .pathMatchers(HttpMethod.POST, AUTH_LOGIN.getValue()).permitAll()
                                .pathMatchers(HttpMethod.POST, USERS_ENDPOINT.getValue()).hasAnyRole(ADMIN.getValue(), ASESOR.getValue())
                                .anyExchange()
                                .authenticated()
                )
                .cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authenticationManager(authenticationManager)
                .addFilterAt(new JwtAuthenticationFilter(jwtService,publicUrls), SecurityWebFiltersOrder.AUTHENTICATION)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    private CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
