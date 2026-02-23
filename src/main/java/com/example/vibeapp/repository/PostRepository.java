package com.example.vibeapp.repository;

import com.example.vibeapp.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> database = new ArrayList<>();

    public void save(Post post) {
        if (post.getNo() == null) {
            long maxNo = database.stream()
                    .mapToLong(Post::getNo)
                    .max()
                    .orElse(0L);
            post.setNo(maxNo + 1);
        }
        database.add(post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(database);
    }

    public Post findById(Long no) {
        return database.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst()
                .orElse(null);
    }
}
