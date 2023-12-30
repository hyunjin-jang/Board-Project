package hyun.portfolio9.service;

import hyun.portfolio9.entities.EditUserDto;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.JoinDto;
import hyun.portfolio9.entities.dto.ResponseUserInfoDto;
import hyun.portfolio9.entities.references.Role;
import hyun.portfolio9.exceptions.TokenNotFoundException;
import hyun.portfolio9.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProviderService jwtProviderService;

    public String create(JoinDto dto, HttpServletResponse response) {
        if(!(userRepository.findByUserName(dto.getUserName()) == null)){
            return "이미 있는 아이디임";
        }

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));
        user.setUserBirth(dto.getUserBirth());
        user.setUserRole(Role.GUEST);
        userRepository.save(user);

        String jwtToken = jwtProviderService.create(user.getUserName());
        response.addHeader("Authorization", "Bearer " + jwtToken);;
        return "create " + user.getUserRole();
    }

    public ResponseUserInfoDto getUser(String token) throws TokenNotFoundException {
        if(token == null){
            throw new TokenNotFoundException("Token 없어 임마");
        }
        String findUsername = jwtProviderService.validate(token);
        User findUser = userRepository.findByUserName(findUsername);
        ResponseUserInfoDto responseUser = new ResponseUserInfoDto();
        responseUser.setUserName(findUser.getUserName());
        responseUser.setUserBirth(findUser.getUserBirth());
        // 이메일, 전번, 주소는 엔티티에 아직 안만들었음 만들면 추가 하도록!
        System.out.println(responseUser.getUserName());
        return responseUser;
    }

    public String editUser(EditUserDto dto) {
        User user = new User();
        user.setUserPassword(dto.getUserPassword());

        userRepository.save(user);
        return "수정 완료";
    }
}
