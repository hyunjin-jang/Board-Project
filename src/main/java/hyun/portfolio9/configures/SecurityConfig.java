package hyun.portfolio9.configures;

import hyun.portfolio9.filter.JwtAuthenticationFilter;
import hyun.portfolio9.filter.TestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(new TestFilter(), SecurityContextPersistenceFilter.class)
                .csrf().disable()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/main/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        hyun.portfolio9.entities.User user = userRepository.findByUserName(username);
//        UserDetails hyun = User.builder()
//                .username("hyun").password("{noop}1111").roles(String.valueOf(Role.ADMIN)).build();
//        UserDetails noah = User.builder()
//                .username("noah").password("{noop}1111").roles("ADMIN").build();
//        UserDetails ori = User.builder()
//                .username("ori").password("{noop}1111").roles("GUEST").build();
//        return new InMemoryUserDetailsManager(hyun, noah, ori);
//    }
}
