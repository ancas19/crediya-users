package co.com.crediya.users.usecase.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JWTUseCase {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Integer expiration;

    public Mono<String> generateToken(String subject, Map<String, String> claims) {
        return Mono.just(
                Jwts
                .builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(Instant.now().plus(expiration, ChronoUnit.MINUTES)))
                .signWith(getSigningKey())
                .compact()
        );
    }

    public Mono<Boolean> validateJwt(String token) {
        return Mono.just(token)
                .map(this::getClaims)
                .map(claims -> claims.getExpiration().after(new Date()))
                .onErrorReturn(false);
    }

    public String extractTokenSubject(String token) {
        return getClaims(token)
                .getSubject();
    }

    public String extractTokenClaim(String token, String key){
        return getClaims(token).get(key).toString();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey(){
        return Optional.ofNullable(secretKey)
                .map(key->key.getBytes())
                .map(bytes-> Keys.hmacShaKeyFor(bytes))
                .orElseThrow(()->new IllegalArgumentException("Secret key is null"));
    }
}
