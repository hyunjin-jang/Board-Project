package hyun.portfolio9.service;

import hyun.portfolio9.entities.EditUserDto;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.JoinDto;
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
        if(!(userRepository.findByUserName(dto.getUserName()) == null)){
            return "이미 있는 아이디임";
        }

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));
        user.setUserBirth(dto.getUserBirth());
        user.setUserRole(Role.GUEST);
        userRepository.save(user);
        return "create " + user.getUserRole();
    }

    public String editUser(EditUserDto dto) {
        User user = new User();
        user.setUserPassword(dto.getUserPassword());

        userRepository.save(user);
        return "수정 완료";
    }
}
