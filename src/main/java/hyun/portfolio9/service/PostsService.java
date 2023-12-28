package hyun.portfolio9.service;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.repositories.PostsRepository;
import hyun.portfolio9.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final JwtProviderService jwtProviderService;
    private final ImageService imageService;

    public String postWrite(WriteDto dto) {
        String findName = jwtProviderService.validate(dto.getJwToken());
        User findUser = userRepository.findByUserName(findName);

        Posts posts = new Posts();
        posts.setPostTitle(dto.getPostTitle());
        posts.setPostContent(dto.getPostContent());
        posts.setPostImageName(dto.getPostImageName());
        posts.setPostCount(0);
        posts.setUser(findUser);
        posts.setPostCreateTime(LocalDateTime.now());
        postsRepository.save(posts);

        return dto.getPostTitle() + " 작성 완료";
    }

    public String postUploadImage(MultipartFile imageFile) {
        String imageName = String.valueOf(imageService.uploadImage(imageFile));
        return imageName;
    }

    public List<Posts> postRead() {
        return postsRepository.findAll();
    }

    public ResponseEntity<Resource> postDownloadImage(String imageName) {
        return imageService.downloadImage(imageName);
    }
}
