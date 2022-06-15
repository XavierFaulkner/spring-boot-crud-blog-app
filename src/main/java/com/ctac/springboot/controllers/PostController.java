package com.ctac.springboot.controllers;

import com.ctac.springboot.models.Post;
import com.ctac.springboot.models.User;
import com.ctac.springboot.services.PostService;
import com.ctac.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    // @GetMapping("/posts")
    // public String viewPostsTable(Model model) {
    //     model.addAttribute("listPosts", postService.findAll());
    //     return "posts";
    // }

    @GetMapping("/posts")
    public String viewUserTable(Model model) {
        return userPagination(1, model);
    }

    @GetMapping("/pages/{pageNo}")
    public String userPagination(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page <Post> pages = postService.postPagination(pageNo, pageSize);
        List <Post> listPosts = pages.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalItems", pages.getTotalElements());
        model.addAttribute("listPosts", listPosts);
        
        return "posts";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable (value = "id") long id, Model model) {
        Post post = postService.findById(id).get();
        model.addAttribute("title", post.getTitle());
        model.addAttribute("author", userService.findById(post.getAuthor().getId()).get().getFirstName());
        model.addAttribute("date", post.getDate());
        model.addAttribute("content", post.getContent());
        model.addAttribute("id", post.getId());
        return "post";
    }

    @GetMapping("/create-post")
    public String createPostPage(Model model) {
        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(authUsername);
        Object title, content;
        String authorFullName = "";
        title = model.getAttribute("title");
        content = model.getAttribute("content");
        Post post = new Post((String)title,(String)content,user, authorFullName);
        model.addAttribute("post", post);
        return "create-post";
    }

    @PostMapping("/create")
    public String savePost(@ModelAttribute("post") Post post) {
        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(authUsername);
        post.setAuthor(user);
        post.setAuthorFullName(user.getFirstName() + " " + user.getLastName());
        postService.create(post);
        return "redirect:/";
    }

    @GetMapping("/delete-post/{id}")
    public String deletePost(@PathVariable (value = "id") long id) {
        this.postService.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/edit-post")
    public String editPost() {
        return "edit-post";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Post> latest5Posts = postService.findLatest5();
        model.addAttribute("latest5posts", latest5Posts);

        List<Post> latest3Posts = latest5Posts.stream()
                .limit(3).collect(Collectors.toList());
        model.addAttribute("latest3posts", latest3Posts);

        return "index";
    }
}
