package hyun.portfolio9.entities.dto;

import lombok.Data;

@Data
public class EditUserDto {
    private Long userId;
    private String editNickName;
    private String editEmail;
    private String editPhone;
    private String editAddress;
}
