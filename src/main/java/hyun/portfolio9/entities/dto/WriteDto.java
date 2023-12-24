package hyun.portfolio9.entities.dto;

import hyun.portfolio9.entities.User;
import lombok.Data;

@Data
public class WriteDto {
    private String postTitle;
    private String postContent;
    private String postFile;
    private User user;
}
