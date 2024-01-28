package hyun.portfolio9.controllers;

import hyun.portfolio9.entities.dto.EditUserDto;
import hyun.portfolio9.entities.dto.JoinDto;
import hyun.portfolio9.entities.dto.ResponseUserInfoDto;
import hyun.portfolio9.repositories.UserRepository;
import hyun.portfolio9.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/user")
    private String create(@RequestBody JoinDto dto, HttpServletResponse response) {
        return userService.create(dto, response);
    }

    @PutMapping("/user")
    private String editUser(@RequestBody EditUserDto dto) {
        return userService.editUser(dto);
    }

    @GetMapping("/user")
    private ResponseUserInfoDto getUser(HttpServletRequest http) {
        String token = http.getHeader("Authorization");
        String jwt = token.replace("Bearer ", "");
        System.out.println("토큰 들어감!!!  "+token);
        return userService.getUser(jwt);
    }

    @DeleteMapping("/user/{id}")
    private String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "삭제 성공";
    }




//    @GetMapping("/user")
//    private String guest() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null) {
//            String userName = authentication.getName();
//            Object principal = authentication.getPrincipal();
//
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//            return "User : " + userName + " Role : " + authorities + " Guest Page";
//        }
//
//        return "guest 제대로 작동됨";
//    }

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
