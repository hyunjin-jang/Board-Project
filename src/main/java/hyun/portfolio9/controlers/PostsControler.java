package hyun.portfolio9.controlers;

import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostsControler {
    private final PostsService postsService;

    @PostMapping("/posts")
    public String write(@RequestBody WriteDto dto) {
        return postsService.postWrite(dto);
    }
}
