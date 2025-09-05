package co.com.crediya.users.usecase.authentication;

import co.com.crediya.users.model.authentication.gateways.AuthenticationPort;
import co.com.crediya.users.model.commos.models.Authentication;
import co.com.crediya.users.model.commos.models.Token;
import co.com.crediya.users.usecase.jwt.JWTUseCase;
import co.com.crediya.users.usecase.users.UsersUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static co.com.crediya.users.model.commos.enums.Constants.CALIM_IDENTIFICATION;
import static co.com.crediya.users.model.commos.enums.Constants.CLAIM_ROLE;

@RequiredArgsConstructor
public class AuthenticationUseCase {
    private final AuthenticationPort authenticationPort;
    private final UsersUseCase usersUseCase;
    private final JWTUseCase jwtUseCase;

    public Mono<Token> authenticate(Authentication authenticationInformation) {
        return authenticationPort.authenticate(authenticationInformation.getEmail(), authenticationInformation.getPassword())
                .flatMap(resul -> usersUseCase.findUserByEmail(authenticationInformation.getEmail()))
                .flatMap(user -> {
                    Map<String, String> claimsToSave = new HashMap<>();
                    claimsToSave.put(CALIM_IDENTIFICATION.getValue(), user.getIdentification());
                    claimsToSave.put(CLAIM_ROLE.getValue(), user.getRoleName());
                    return this.jwtUseCase.generateToken(user.getEmail(), claimsToSave)
                            .map(token -> Token.builder()
                                    .names(user.getNames())
                                    .lastName(user.getLastName())
                                    .email(user.getEmail())
                                    .identifcation(user.getIdentification())
                                    .token(token)
                                    .build()
                            );
                });
    }
}
