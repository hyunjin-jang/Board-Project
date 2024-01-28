package hyun.portfolio9.controllers;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.dto.PostPagingDto;
import hyun.portfolio9.entities.dto.ResponsePostFindByIdDto;
import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.entities.dto.editPostDto;
import hyun.portfolio9.repositories.PostsRepository;
import hyun.portfolio9.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;
    private final PostsRepository postsRepository;

    @GetMapping("/post/{page}")
    public Page<Posts> findAllPosts(@PathVariable int page) {
        return postsService.findAllPosts(page);
    }

    @PostMapping("/posts/image")
    public List<String> uploadImage(@RequestParam List<MultipartFile> files) {
        return postsService.postUploadImage(files);
    }

    @GetMapping("/posts/image/{imageName}")
    public ResponseEntity<String> downloadImage(@PathVariable String imageName) {
        return postsService.postDownloadImage(imageName);
    }

    @PostMapping("/posts")
    public String writePost(@RequestBody WriteDto dto, HttpServletRequest http) {
        System.out.println("이미지 이름들 저장  " + dto.getPostImageNames());
        return postsService.postWrite(dto, http);
    }

    @GetMapping("/posts")
    public List<Posts> readPost() {
        return postsRepository.findAll();
    }

    @GetMapping("/posts/{postId}")
    public ResponsePostFindByIdDto readPost(@PathVariable Long postId) {
        return postsService.findById(postId);
    }

    @PutMapping("/posts")
    public String editPost(@RequestBody editPostDto dto, HttpServletRequest http) {
        return postsService.postEdit(dto, http);
    }

    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable Long postId) {
        return postsService.postDelete(postId);
    }

    @GetMapping("/posts/search/{keyword}")
    public List<Posts> searchList(@PathVariable String keyword){
        List<Posts> postsList = postsService.search(keyword);
        return postsList;
    }
}
