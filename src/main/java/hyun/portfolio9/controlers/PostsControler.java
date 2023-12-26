package hyun.portfolio9.controlers;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.repositories.PostsRepository;
import hyun.portfolio9.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsControler {
    private final PostsService postsService;
    private final PostsRepository postsRepository;

    @PostMapping("/posts")
    public String write(@RequestBody WriteDto dto) {
        return postsService.postWrite(dto);
    }

    @GetMapping("/posts")
    public List<Posts> read() {
        return postsRepository.findAll();
    }
}
