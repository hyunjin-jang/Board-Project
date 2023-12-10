package hyun.portfolio9.service;

import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.JoinDto;
import hyun.portfolio9.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String create(JoinDto dto) {
        User user = User.builder()
                .userName(dto.getUserName())
//                .UserRole(dto.getUserRole())
                .build();
        userRepository.save(user);
        return "create user";
    }
}
