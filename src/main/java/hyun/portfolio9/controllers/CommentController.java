package hyun.portfolio9.controllers;

import hyun.portfolio9.entities.dto.WriteCommentDto;
import hyun.portfolio9.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public String writeComment(@RequestBody WriteCommentDto dto, HttpServletRequest http) {
        return commentService.writeComment(dto, http);
    }

    @DeleteMapping("/comment/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        return commentService.DeleteComment(commentId);
    }
}
