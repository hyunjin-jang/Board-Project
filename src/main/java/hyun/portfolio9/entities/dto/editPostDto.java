package hyun.portfolio9.entities.dto;

import lombok.Data;

import java.util.List;

@Data
public class editPostDto {
    private Long postId;
    private String postTitle;
    private String postContent;
    private List<String> postImageNames;
}
