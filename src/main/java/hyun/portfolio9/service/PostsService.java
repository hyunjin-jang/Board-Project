package hyun.portfolio9.service;

import hyun.portfolio9.entities.Posts;
import hyun.portfolio9.entities.dto.WriteDto;
import hyun.portfolio9.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;

    public String postWrite(WriteDto dto) {
        Posts posts = new Posts();
        posts.setPostTitle(dto.getPostTitle());
        posts.setPostContent(dto.getPostContent());
        posts.setPostFile(dto.getPostFile());
        posts.setUser(dto.getUser());
        posts.setPostCreateTime(LocalDateTime.now());
        postsRepository.save(posts);

        return dto.getPostTitle() + " 작성 완료";
    }
}
