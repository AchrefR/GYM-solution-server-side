package gym_solution.demo.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret is injected from configuration (env: JWT_SECRET). Minimum 32 chars for HS256.
    @Value("${jwt.secret}")
    private String secret;

    // Token lifetime in milliseconds (default 24h). Configurable via env: JWT_EXPIRATION.
    @Value("${jwt.expiration:86400000}")
    private long expirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        return generateToken(username, null);
    }

    /**
     * Generates a token whose subject is the username (email) and which carries the
     * user's role as a custom claim so the client can read it without an extra request.
     */
    public String generateToken(String username, String role) {
        var builder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs));
        if (role != null) {
            builder.claim("role", role);
        }
        return builder.signWith(key).compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    /**
     * Returns true if the token is well-formed, correctly signed and not expired.
     * Never throws — callers can branch safely on the boolean.
     */
    public boolean isTokenValid(String token) {
        try {
            getClaims(token); // throws if signature invalid / expired / malformed
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
