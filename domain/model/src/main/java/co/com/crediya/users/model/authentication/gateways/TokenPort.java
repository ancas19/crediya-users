package co.com.crediya.users.model.authentication.gateways;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface TokenPort {
    Mono<String> generateToken(String subject, Map<String, String> claims);

    Mono<Boolean> validateJwt(String token);

    String extractTokenSubject(String token);

    String extractTokenClaim(String token, String key);
    Map<String, Object>  getClaimsMap(String token);
}
