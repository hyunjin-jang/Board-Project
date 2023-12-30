package hyun.portfolio9.configures;

import hyun.portfolio9.filter.JwtAuthenticationFilter;
import hyun.portfolio9.filter.JwtAuthorizationFilter;
import hyun.portfolio9.repositories.UserRepository;
import hyun.portfolio9.service.JwtProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsFilter corsFilter;
    private final JwtProviderService jwtProviderService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers().addHeaderWriter(((request, response) -> {
                   response.addHeader("Access-Control-Expose-Headers", "Authorization");
                }));

        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(corsFilter)
                .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtProviderService))
                .addFilter(new JwtAuthorizationFilter(authenticationManager, jwtProviderService, userRepository))
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                .anyRequest().permitAll();
        return http.build();
    }
}
