package hyun.portfolio9.service;

import hyun.portfolio9.entities.Comment;
import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.WriteCommentDto;
import hyun.portfolio9.repositories.CommentRepository;
import hyun.portfolio9.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final JwtProviderService jwtProviderService;

    public String writeComment(WriteCommentDto dto, HttpServletRequest http) {
        String token = http.getHeader("Authorization").replace("Bearer ", "");
        String userEmail = jwtProviderService.validate(token);
        User user = userRepository.findByUserEmail(userEmail);

        Comment comment = Comment.builder()
                .commentContent(dto.getComment())
                .commentCreateTime(LocalDateTime.now())
                .posts(dto.getPosting())
                .user(user)
                .build();
        commentRepository.save(comment);
        return comment.getCommentContent()+" 댓글 작성완료!";
    }
}
