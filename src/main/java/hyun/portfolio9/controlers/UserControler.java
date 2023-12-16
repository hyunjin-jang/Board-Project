package hyun.portfolio9.controlers;

import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.JoinDto;
import hyun.portfolio9.entities.dto.LoginDto;
import hyun.portfolio9.repositories.UserRepository;
import hyun.portfolio9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class UserControler {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/user")
    private String create(@RequestBody JoinDto dto) {
        return userService.create(dto);
    }

//    @PostMapping("/login")
//    private void login() {
//        System.out.println("Use Cotroller Login ");
//    }

    @GetMapping("/blob/{name}")
    private String blob(@PathVariable String name) {
        User user = userRepository.findByUserName(name);
        if(user == null) {
            return "user정보 없음!";
        }
        return user.getUserRole();
    }

    @GetMapping("/hello")
    private String hello(){
        return "HELLO";
    }

    @GetMapping("/user")
    private String guest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String userName = authentication.getName();
            Object principal = authentication.getPrincipal();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            return "User : " + userName + " Role : " + authorities + " Guest Page";
        }

        return "guest 제대로 작동됨";
    }

    @GetMapping("/admin")
    private String admin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String userName = authentication.getName();
            Object principal = authentication.getPrincipal();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            return "User : " + userName + " Role : " + authorities + " Admin Page";
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        String password = userDetails.getPassword();

        return email + "의 admin 제대로 작동됨";
    }
}
