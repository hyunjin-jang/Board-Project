package hyun.portfolio9.service;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.repositories.PostsRepository;
import hyun.portfolio9.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final JwtProviderService jwtProviderService;

    public String postWrite(WriteDto dto) {
        String findName = jwtProviderService.validate(dto.getJwToken());
        User findUser = userRepository.findByUserName(findName);

        Posts posts = new Posts();
        posts.setPostTitle(dto.getPostTitle());
        posts.setPostContent(dto.getPostContent());
        posts.setPostFile(dto.getPostFile());
        posts.setPostCount(0);
        posts.setUser(findUser);
        posts.setPostCreateTime(LocalDateTime.now());
        postsRepository.save(posts);

        return dto.getPostTitle() + " 작성 완료";
    }

    public List<Posts> postRead() {
        return postsRepository.findAll();
    }
}
