package co.com.crediya.users.jwt.userdetail_service;

import co.com.crediya.users.usecase.users.UsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.com.crediya.users.model.commos.enums.Constants.ROLE;

@Service
@RequiredArgsConstructor
public class UserDetailUseCase implements ReactiveUserDetailsService {

    private final UsersUseCase usersUseCase;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return this.usersUseCase.findUserByEmail(username)
                .map(usersAuthentication -> User
                        .withUsername(usersAuthentication.getEmail())
                        .password(usersAuthentication.getPassword())
                        .authorities(List.of( new SimpleGrantedAuthority(ROLE.getValue().formatted(usersAuthentication.getRoleName()))))
                        .build()
                );
    }
}
