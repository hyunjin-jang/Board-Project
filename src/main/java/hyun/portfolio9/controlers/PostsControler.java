package hyun.portfolio9.controlers;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.repositories.PostsRepository;
import hyun.portfolio9.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsControler {
    private final PostsService postsService;
    private final PostsRepository postsRepository;

    @PostMapping("/posts/image")
    public List<String> uploadImage(@RequestParam List<MultipartFile> files) {
        return postsService.postUploadImage(files);
    }

    @PostMapping("/posts")
    public String write(@RequestBody WriteDto dto, HttpServletRequest http) {
        System.out.println("이미지 이름들 저장  " + dto.getPostImageNames());
        return postsService.postWrite(dto, http);
    }

    @GetMapping("/posts/image/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName) {
        return postsService.postDownloadImage(imageName);
    }

    @GetMapping("/posts")
    public List<Posts> read() {
        return postsRepository.findAll();
    }

    @DeleteMapping("/posts/{postId}")
    public String postDelete(@PathVariable Long postId) {
        return postsService.postDelete(postId);
    }
}
