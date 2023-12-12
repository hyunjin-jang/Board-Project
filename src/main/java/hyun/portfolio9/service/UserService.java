package hyun.portfolio9.service;

import hyun.portfolio9.configures.auth.PrincipalDetails;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.JoinDto;
import hyun.portfolio9.entities.dto.LoginDto;
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
                .userPassword(dto.getUserPassword())
//                .UserRole(dto.getUserRole())
                .build();
        userRepository.save(user);
        return "create user";
    }

//    public void login(LoginDto dto) {
//        User foundUser = userRepository.findByUserName(dto.getUserName());
//        PrincipalDetails user = new PrincipalDetails(foundUser);
//    }
}
