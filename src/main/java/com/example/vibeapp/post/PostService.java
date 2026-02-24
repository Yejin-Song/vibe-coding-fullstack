package com.example.vibeapp.post;

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

    public List<Post> getAllPosts() {
        return postRepository.findAll().stream()
                .sorted(Comparator.comparing(Post::getNo).reversed())
                .collect(Collectors.toList());
    }

    public Post getPost(Long no) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
        return post;
    }

    public void addPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }

    public void updatePost(Long no, String title, String content) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteById(no);
    }

    public List<Post> getPostsPaged(int page, int size) {
        List<Post> allPosts = getAllPosts();
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
