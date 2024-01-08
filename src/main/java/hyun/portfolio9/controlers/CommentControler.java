package hyun.portfolio9.controlers;

import hyun.portfolio9.entities.dto.WriteCommentDto;
import hyun.portfolio9.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentControler {
    private final CommentService commentService;

    @PostMapping("/comment")
    public String writeComment(@RequestBody WriteCommentDto dto, HttpServletRequest http) {
        return commentService.writeComment(dto, http);
    }
}
