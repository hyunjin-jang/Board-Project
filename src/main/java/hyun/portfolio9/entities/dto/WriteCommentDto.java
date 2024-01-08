package hyun.portfolio9.entities.dto;

import hyun.portfolio9.entities.Posts;
import lombok.Data;

@Data
public class WriteCommentDto {
    private String comment;
    private Posts posting;
}
