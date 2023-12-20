package hyun.portfolio9.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postTitle;
    private String postContent;
    private LocalDateTime postCreateTime;
    private LocalDateTime postModifyTime;
    @ManyToOne
    private User user;
}
