package hyun.portfolio9.configures;

import hyun.portfolio9.entities.references.Role;
import hyun.portfolio9.filter.TestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .addFilter(corsFilter)
//                .addFilterAfter(new TestFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/main/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .defaultSuccessUrl("/main/admin")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/fail");
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails hyun = User.builder()
                .username("hyun").password("{noop}1111").roles(String.valueOf(Role.ADMIN)).build();
        UserDetails noah = User.builder()
                .username("noah").password("{noop}1111").roles("ADMIN").build();
        UserDetails ori = User.builder()
                .username("ori").password("{noop}1111").roles("GUEST").build();
        return new InMemoryUserDetailsManager(hyun, noah, ori);
    }
}
