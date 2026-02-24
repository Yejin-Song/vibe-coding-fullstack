package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    public PostService(PostRepository postRepository, PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
    }

    @PostConstruct
    public void init() {
        // 초기 데이터는 src/main/resources/data.sql에서 관리합니다.
    }

    public List<PostListDto> findAllPosts() {
        return postRepository.findAll().stream()
                .map(PostListDto::from)
                .collect(Collectors.toList());
    }

    public PostResponseDto findPostById(Long no) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
            postRepository.update(post);
            
            List<String> tags = postTagRepository.findByPostNo(no).stream()
                    .map(PostTag::getTagName)
                    .collect(Collectors.toList());
            
            return PostResponseDto.from(post, tags);
        }
        return null;
    }

    @Transactional
    public void createPost(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        postRepository.save(post);
        
        saveTags(post.getNo(), createDto.tags());
    }

    @Transactional
    public void updatePost(Long no, PostUpdateDto updateDto) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setTitle(updateDto.title());
            post.setContent(updateDto.content());
            post.setUpdatedAt(LocalDateTime.now());
            postRepository.update(post);
            
            postTagRepository.deleteByPostNo(no);
            saveTags(no, updateDto.tags());
        }
    }

    private void saveTags(Long postNo, String tagsString) {
        if (tagsString == null || tagsString.isBlank()) return;
        
        String[] tags = tagsString.split(",");
        for (String tag : tags) {
            String trimmedTag = tag.trim();
            if (!trimmedTag.isEmpty()) {
                postTagRepository.save(new PostTag(null, postNo, trimmedTag));
            }
        }
    }

    @Transactional
    public void deletePost(Long no) {
        postRepository.deleteById(no);
    }

    public List<PostListDto> findPostsPaged(int page, int size) {
        int offset = (page - 1) * size;
        java.util.Map<String, Object> params = new java.util.HashMap<>();
        params.put("size", size);
        params.put("offset", offset);
        
        return postRepository.findPaged(params).stream()
                .map(PostListDto::from)
                .collect(Collectors.toList());
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.count();
        return (int) Math.ceil((double) totalPosts / size);
    }
}
