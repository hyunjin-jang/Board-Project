package hyun.portfolio9.configures;

import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.references.Role;
import hyun.portfolio9.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class InitialDataLoader {
    @Bean
    public CommandLineRunner initialize(UserRepository userRepository) {
        return args -> {
            User admin = new User(1L, "admin", "1111", Arrays.asList(Role.ADMIN, Role.GUEST));
            userRepository.save(admin);
        };
    }
}
