package hyun.portfolio9.entities;

import lombok.Data;

@Data
public class EditUserDto {
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userAddress;
}
