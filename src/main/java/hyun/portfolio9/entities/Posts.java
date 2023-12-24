package hyun.portfolio9.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String postFile;
    private LocalDateTime postCreateTime;
    private LocalDateTime postModifyTime;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
