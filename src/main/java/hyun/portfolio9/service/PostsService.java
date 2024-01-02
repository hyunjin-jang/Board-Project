package hyun.portfolio9.service;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.repositories.PostsRepository;
import hyun.portfolio9.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final JwtProviderService jwtProviderService;
    private final ImageService imageService;

    public String postWrite(WriteDto dto, HttpServletRequest http) {
        String jwt = http.getHeader("Authorization").replace("Bearer ", "");
        String findName = jwtProviderService.validate(jwt);
        User findUser = userRepository.findByUserName(findName);

        Posts posts = new Posts();
        posts.setPostTitle(dto.getPostTitle());
        posts.setPostContent(dto.getPostContent());
        posts.setPostImageNames(dto.getPostImageNames());
        posts.setPostCount(0);
        posts.setUser(findUser);
        posts.setPostCreateTime(LocalDateTime.now());
        postsRepository.save(posts);

        return dto.getPostTitle() + " 작성 완료";
    }

    public List<String> postUploadImage(List<MultipartFile> files) {
        List<String> imageNames = new ArrayList<>();
        for (MultipartFile file : files) {
            imageNames.add(String.valueOf(imageService.uploadImage(file)));
        }

        return imageNames;
    }

    public List<Posts> postRead() {
        return postsRepository.findAll();
    }

    public ResponseEntity<Resource> postDownloadImage(String imageName) {
        return imageService.downloadImage(imageName);
    }

    public String postDelete(Long postId) {
        Optional<Posts> findPost = postsRepository.findById(postId);
        postsRepository.deleteById(postId);
        return findPost.get().getPostTitle() + " 포스트를 삭제 했습니다.";
    }
}
