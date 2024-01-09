package hyun.portfolio9.entities.dto;

import hyun.portfolio9.entities.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResponsePostFindByIdDto {
    private Long postId;
    private String postTitle;
    private String postContent;
    private Integer postCount;
    private List<String> postImageNames; //이미지 이름
    private LocalDateTime postCreateTime;
    private LocalDateTime postModifyTime;
    private String userNickName;
    private String userEmail;
    private List<Comment> commentList = new ArrayList<>();
}
