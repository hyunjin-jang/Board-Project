package hyun.portfolio9.controlers;

import hyun.portfolio9.entities.dto.JoinDto;
import hyun.portfolio9.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControler {
    private final UserService userService;

    @PostMapping("/user")
    private String create(@RequestBody JoinDto dto) {
        return userService.create(dto);
    }

    @GetMapping("/user")
    private String outPut() {
        return "제대로 작동됨";
    }
}
