package hyun.portfolio9.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postTitle;
    private String postContent;
    private Integer postCount;
    private List<String> postImageNames; //이미지 이름
    private LocalDateTime postCreateTime;
    private LocalDateTime postModifyTime;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    public Posts(Posts posts) {
        this.postId = posts.getPostId();
        this.postTitle = posts.getPostTitle();
        this.postContent = posts.getPostContent();
        this.postCount = posts.getPostCount();
        this.postImageNames = posts.getPostImageNames();
        this.postCreateTime = posts.getPostCreateTime();
        this.postModifyTime = posts.getPostModifyTime();
        this.user = posts.getUser();
        this.commentList = posts.getCommentList();
    }
}
