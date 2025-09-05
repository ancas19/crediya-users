package co.com.crediya.users.authentication.adapters;

import co.com.crediya.users.model.authentication.gateways.AuthenticationPort;
import co.com.crediya.users.model.commos.enums.ErrorMessages;
import co.com.crediya.users.model.commos.exception.BadCredentialsException;
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
    public Mono<Boolean> authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(email,password);
        return authenticationManager.authenticate(authToken)
                .map(auth->true)
                .onErrorResume(result->Mono.error(new BadCredentialsException(ErrorMessages.ERROR_MESSAGE_BAD_CREDENTIALS.getMessage())));
    }
}
