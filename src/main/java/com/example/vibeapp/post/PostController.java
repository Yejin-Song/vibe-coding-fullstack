package com.example.vibeapp.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String getPosts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int size = 5;
        List<Post> posts = postService.findPostsPaged(page, size);
        int totalPages = postService.getTotalPages(size);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "post/posts";
    }

    @GetMapping("/posts/{no}")
    public String getPost(@PathVariable("no") Long no, Model model) {
        Post post = postService.findPostById(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/new")
    public String createPostForm() {
        return "post/post_new_form";
    }

    @GetMapping("/posts/{no}/edit")
    public String updatePostForm(@PathVariable("no") Long no, Model model) {
        Post post = postService.findPostById(no);
        model.addAttribute("post", post);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/add")
    public String createPost(@RequestParam("title") String title, @RequestParam("content") String content) {
        postService.createPost(title, content);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{no}/save")
    public String updatePost(@PathVariable("no") Long no, @RequestParam("title") String title, @RequestParam("content") String content) {
        postService.updatePost(no, title, content);
        return "redirect:/posts/" + no;
    }

    @PostMapping("/posts/{no}/delete")
    public String deletePost(@PathVariable("no") Long no) {
        postService.deletePost(no);
        return "redirect:/posts";
    }
}
