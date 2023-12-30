package hyun.portfolio9.entities.dto;

import lombok.Data;

@Data
public class ResponseUserInfoDto {
    private String userName;
    private String userBirth;
    private String userEmail;
    private String userPhone;
    private String userAddress;
}
