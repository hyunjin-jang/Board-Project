package hyun.portfolio9.entities.dto;

import lombok.Data;

@Data
public class ResponseUserInfoDto {
    private Long userId;
    private String userNickName;
    private String userBirth;
    private String userEmail;
    private String userPhone;
    private String userAddress;
}
