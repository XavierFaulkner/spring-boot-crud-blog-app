package com.ctac.springboot.services;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ctac.springboot.models.Post;

@Component
public interface PostService {
    List<Post> findAll();
    List<Post> findLatest5();
    Post create(Post post);
    Post edit(Post post);
    void save(Post post);
    void deleteById(Long id);
    Optional<Post> findById(Long id);
}
