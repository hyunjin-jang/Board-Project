package hyun.portfolio9;

import hyun.portfolio9.entities.User;
import hyun.portfolio9.repositories.UserRepository;
import hyun.portfolio9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Portfolio9Application {

	public static void main(String[] args) {
		SpringApplication.run(Portfolio9Application.class, args);
	}

}
