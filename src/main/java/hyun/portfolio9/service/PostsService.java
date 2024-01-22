package hyun.portfolio9.service;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.ResponsePostFindByIdDto;
import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.entities.dto.editPostDto;
import hyun.portfolio9.repositories.PostsRepository;
import hyun.portfolio9.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
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
    private final S3Service s3Service;

    public String postWrite(WriteDto dto, HttpServletRequest http) {
        String jwt = http.getHeader("Authorization").replace("Bearer ", "");
        String findEmail = jwtProviderService.validate(jwt);
        User findUser = userRepository.findByUserEmail(findEmail);

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
            try {
                imageNames.add(String.valueOf(s3Service.saveFile(file)));
            } catch (Exception e) {
                return null;
            }
        }
        return imageNames;
    }

    public List<Posts> postRead() {
        return postsRepository.findAll();
    }

    public ResponseEntity<String> postDownloadImage(String imageName) {
        return s3Service.imagePreview(imageName);
    }

    public String postDelete(Long postId) {
        Optional<Posts> findPost = postsRepository.findById(postId);
        postsRepository.deleteById(postId);
        return findPost.get().getPostTitle() + " 포스트를 삭제 했습니다.";
    }

    public ResponsePostFindByIdDto findById(Long postId) {
        Optional<Posts> findPost = postsRepository.findById(postId);
        ResponsePostFindByIdDto dto = new ResponsePostFindByIdDto();
        dto.setPostId(postId);
        dto.setPostTitle(findPost.get().getPostTitle());
        dto.setPostContent(findPost.get().getPostContent());
        dto.setPostCount(findPost.get().getPostCount());
        dto.setPostImageNames(findPost.get().getPostImageNames());
        dto.setPostCreateTime(findPost.get().getPostCreateTime());
        dto.setPostModifyTime(findPost.get().getPostModifyTime());
        dto.setUserNickName(findPost.get().getUser().getUserNickName());
        dto.setUserEmail(findPost.get().getUser().getUserEmail());
        dto.setCommentList(findPost.get().getCommentList());
        return dto;
    }

    public String postEdit(editPostDto dto, HttpServletRequest http) {
        String jwt = http.getHeader("Authorization").replace("Bearer ", "");
        Optional<Posts> findPost = postsRepository.findById(dto.getPostId());
        String findEmail = jwtProviderService.validate(jwt);
        User findUser = userRepository.findByUserEmail(findEmail);

        Posts posts = Posts.builder()
                .postId(dto.getPostId())
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .postCount(findPost.get().getPostCount())
                .postImageNames(dto.getPostImageNames())
                .postCreateTime(findPost.get().getPostCreateTime())
                .postModifyTime(LocalDateTime.now())
                .user(findUser)
                .build();
        postsRepository.save(posts);
        return "수정 완료!";
    }

    @Transactional
    public List<Posts> search(String keyworkd) {
        List<Posts> postsList = postsRepository.findByPostTitleContaining(keyworkd);
        return postsList;
    }
}
