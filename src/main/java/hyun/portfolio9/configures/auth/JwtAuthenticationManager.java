package hyun.portfolio9.configures.auth;

import hyun.portfolio9.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationManager implements AuthenticationManager {
    @Lazy
    private final PrincipalDetailsService principalDetailsService;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("JwtAuthenticationManager 실행했습니다.");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // User 이름이 데이터베이스 안에 있는지 확인
        PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername(username);

        String encodedPassword = userRepository.findByUserName(username).getUserPassword();

        if (!passwordEncoder().matches(password, encodedPassword)) {
            System.out.println("비번 틀림");
            throw new UsernameNotFoundException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
