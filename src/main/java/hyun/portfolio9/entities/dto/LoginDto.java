package hyun.portfolio9.entities.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class LoginDto {
    private String userNickName;
    private String userPassword;
}
