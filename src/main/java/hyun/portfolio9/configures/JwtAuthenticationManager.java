package hyun.portfolio9.configures;

import hyun.portfolio9.configures.auth.PrincipalDetails;
import hyun.portfolio9.configures.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements AuthenticationManager {
    @Lazy
    private final PrincipalDetailsService principalDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("JwtAuthenticationManager 작동함");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // User 이름 있는지 확인
        PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername(username);

        if (!password.equals(principalDetails.getPassword())) {
            System.out.println("비번 틀림");
            throw new UsernameNotFoundException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(principalDetails, password, principalDetails.getAuthorities());
    }
}
