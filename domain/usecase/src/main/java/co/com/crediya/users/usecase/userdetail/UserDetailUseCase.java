package co.com.crediya.users.usecase.userdetail;

import co.com.crediya.users.model.users.gateways.UsersRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static co.com.crediya.users.model.commos.enums.Constants.ROLE;

@RequiredArgsConstructor
public class UserDetailUseCase implements ReactiveUserDetailsService {

    private final UsersRepositoryPort usersRepositoryPort;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return this.usersRepositoryPort.findByEmail(username)
                .map(usersAuthentication -> User
                        .withUsername(usersAuthentication.getEmail())
                        .password(usersAuthentication.getPassword())
                        .authorities(List.of( new SimpleGrantedAuthority(ROLE.getValue().formatted(usersAuthentication.getRoleName()))))
                        .build()
                );
    }
}
