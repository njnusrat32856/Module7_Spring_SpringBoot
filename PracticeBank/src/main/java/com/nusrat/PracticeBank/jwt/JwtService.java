package com.nusrat.PracticeBank.jwt;

import com.nusrat.PracticeBank.entity.User;
import com.nusrat.PracticeBank.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private TokenRepository tokenRepository;

    private final String SECREAT_KEY = "dfgkrmkernknnsfn453465ygdfgd45w3rs34rbv3333334vwrs53vb4werf";

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECREAT_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extracAlltClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(User user) {

        String token = Jwts
                .builder()

                .subject(user.getEmail())

                .issuedAt(new Date(System.currentTimeMillis()))

                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 *1000))

                .signWith(getSigninKey())

                .compact();

        return token;
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver) {
        Claims claims = extracAlltClaims(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);

        boolean validToken = tokenRepository
                .findByToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
    }
}
