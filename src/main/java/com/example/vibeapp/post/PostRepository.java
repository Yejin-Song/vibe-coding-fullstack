package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface PostRepository {
    List<Post> findAll();
    Post findById(Long no);
    void save(Post post);
    void update(Post post);
    void deleteById(Long no);
    List<Post> findPaged(Map<String, Object> params);
    int count();
}
