package co.com.crediya.users.usecase.jwt;

import co.com.crediya.users.model.authentication.gateways.TokenPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
public class JWTUseCase {
    private final TokenPort tokenPort;

    public Mono<String> generateToken(String subject, Map<String, String> claims) {
        return tokenPort.generateToken(subject, claims);
    }
    public Mono<Boolean> validateJwt(String token){
        return this.tokenPort.validateJwt(token);
    }
    public Map<String, Object>  getClaims(String token){
        return this.tokenPort.getClaimsMap(token);
    }
    public String extractTokenSubject(String token){
        return this.tokenPort.extractTokenSubject(token);
    }

    public String extractTokenClaim(String token, String key){
        return this.tokenPort.extractTokenClaim(token, key);
    }
}
