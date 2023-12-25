package hyun.portfolio9.configures;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.references.Role;
import hyun.portfolio9.repositories.PostsRepository;
import hyun.portfolio9.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class InitialDataLoader {
    private final PasswordEncoder passwordEncoder;
    @Bean
    public CommandLineRunner initialize(UserRepository userRepository, PostsRepository postsRepository) {
        return args -> {
//            User admin = new User(1L, "admin", passwordEncoder.encode("1111"), "1993.11.21", Role.ADMIN, new ArrayList<>());
//            userRepository.save(admin);
        };
    }
}
