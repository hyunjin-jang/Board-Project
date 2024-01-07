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
    @OneToMany(mappedBy = "posts")
    private List<Comment> commentList = new ArrayList<>();
}
