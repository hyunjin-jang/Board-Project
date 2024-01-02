package hyun.portfolio9.entities.dto;

import hyun.portfolio9.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class WriteDto {
    private String postTitle;
    private String postContent;
    private List<String> postImageNames;
}
