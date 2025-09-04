package co.com.crediya.users.authentication.adapters;

import co.com.crediya.users.model.authentication.gateways.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthentiocationAdapter implements AuthenticationPort {
    private final ReactiveAuthenticationManager authenticationManager;

    @Override
    public Mono<Void> authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(email,password);
        return authenticationManager.authenticate(authToken)
                .then(Mono.empty());
    }
}
