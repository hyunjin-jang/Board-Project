package hyun.portfolio9.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtProviderService {
    @Value("${JWT.SECURITY_KEY}")
    private String SECURITY_KEY;

    public String create(String username) {
        Date exprTime = Date.from(Instant.now().plus(30, ChronoUnit.DAYS));

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(exprTime)
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                .compact();
    }

    public String validate(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean isExpired(String jwtToken) {
        return Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(jwtToken)
                .getBody().getExpiration().before(new Date());
    }
}
