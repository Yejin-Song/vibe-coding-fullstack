package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import java.time.LocalDateTime;

public record PostResponseDto(
    Long no,
    String title,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Integer views,
    java.util.List<String> tags
) {
    public static PostResponseDto from(Post post, java.util.List<String> tags) {
        if (post == null) return null;
        return new PostResponseDto(
            post.getNo(),
            post.getTitle(),
            post.getContent(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getViews(),
            tags
        );
    }
}
