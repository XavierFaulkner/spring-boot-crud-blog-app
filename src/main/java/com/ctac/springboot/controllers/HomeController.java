package com.ctac.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;
import com.ctac.springboot.models.Post;
import com.ctac.springboot.services.PostService;
import org.springframework.ui.Model;





@Controller
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // @GetMapping("/posts")
    // public String posts() {
    //     return "posts";
    // }

    @GetMapping("/create-post")
    public String createPost() {
        return "create-post";
    }

    @GetMapping("/delete-post")
    public String deletePost() {
        return "delete-post";
    }

    @GetMapping("/edit-post")
    public String editPost() {
        return "edit-post";
    }

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(Model model) {
    List<Post> latest5Posts = postService.findLatest5();
    model.addAttribute("latest5posts", latest5Posts);

    List<Post> latest3Posts = latest5Posts.stream()
    .limit(3).collect(Collectors.toList());
    model.addAttribute("latest3posts", latest3Posts);

    return "index";
    }

    @GetMapping("/posts")
    public String viewPostTable (Model model) {
        return postPagination(1, model);
    }

    @GetMapping("/pageview/{pageNo}")
    public String postPagination(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page <Post> pageview = postService.postPagination(pageNo, pageSize);
        List <Post> listPosts = pageview.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", pageview.getTotalPages());
        model.addAttribute("totalItems", pageview.getTotalElements());
        model.addAttribute("listPosts", listPosts);
        
        return "posts";
    }

}