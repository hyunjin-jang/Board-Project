package hyun.portfolio9.service;

import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.JoinDto;
import hyun.portfolio9.entities.dto.LoginDto;
import hyun.portfolio9.entities.references.Role;
import hyun.portfolio9.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String create(JoinDto dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));

        userRepository.save(user);
        return "create" + user.getUserRole();
    }

//    public void login(LoginDto dto) {
//        User foundUser = userRepository.findByUserName(dto.getUserName());
//        PrincipalDetails user = new PrincipalDetails(foundUser);
//    }
}
