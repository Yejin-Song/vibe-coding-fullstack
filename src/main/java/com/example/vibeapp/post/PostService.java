package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 10; i++) {
            postRepository.save(new Post(
                (long) i,
                "Vibe Coding 게시글 제목 " + i,
                "이것은 세련된 게시글 " + i + "의 내용입니다. Vibe Coding 프로젝트가 원활하게 진행되고 있습니다.",
                LocalDateTime.now().minusDays(10 - i),
                LocalDateTime.now().minusDays(10 - i),
                i * 15
            ));
        }
    }

    public List<PostListDto> findAllPosts() {
        return postRepository.findAll().stream()
                .sorted(Comparator.comparing(Post::getNo).reversed())
                .map(PostListDto::from)
                .collect(Collectors.toList());
    }

    public PostResponseDto findPostById(Long no) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
        return PostResponseDto.from(post);
    }

    public void createPost(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        postRepository.save(post);
    }

    public void updatePost(Long no, PostUpdateDto updateDto) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setTitle(updateDto.getTitle());
            post.setContent(updateDto.getContent());
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteById(no);
    }

    public List<PostListDto> findPostsPaged(int page, int size) {
        List<PostListDto> allPosts = findAllPosts();
        int startIndex = (page - 1) * size;
        if (startIndex >= allPosts.size()) {
            return java.util.Collections.emptyList();
        }
        int endIndex = Math.min(startIndex + size, allPosts.size());
        return allPosts.subList(startIndex, endIndex);
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.findAll().size();
        return (int) Math.ceil((double) totalPosts / size);
    }
}
