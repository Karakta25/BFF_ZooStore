package com.example.bff.core.operations.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bff.api.operations.user.logInUser.LogInUserInput;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Component
public class JwtService {

   // private static final String SECRET_KEY = "A9E3F55F4B8217AD52AFEA58C8872";
    private final Duration TOKEN_VALIDITY = Duration.of(30, ChronoUnit.DAYS);
    private final ApplicationUserDetailsService applicationUserDetailsService;
    //@Value("${jwt-secret}")

    private final String jwtSecret = "A9E3F55F4B8217AD52AFEA58C8872";
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }

//    public String getEmail(String jwt) {
//        DecodedJWT decoded = JWT.require(Algorithm.HMAC256(jwtSecret))
//                .withClaimPresence("email")
//                .build()
//                .verify(jwt);
//
//        return decoded.getClaim("email").asString();
//    }
    //   public String generateToken(UserDetails userDetails) {
    //     return generateToken(new HashMap<>(), userDetails);
    //}

    public String generateJwt(LogInUserInput input) {

        UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(input.getEmail());

        return JWT.create()
                .withClaim("email", userDetails.getUsername())
                .withClaim("roles", userDetails.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority).toList())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(TOKEN_VALIDITY))
                .sign(Algorithm.HMAC256(jwtSecret));
    }


//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
        public String getEmail(String jwt) {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC256(jwtSecret))
                .withClaimPresence("email")
                .build()
                .verify(jwt);

        return decoded.getClaim("email").asString();
    }
}
