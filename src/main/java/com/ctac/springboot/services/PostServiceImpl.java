package com.ctac.springboot.services;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.PostRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ctac.springboot.models.Post;
import com.ctac.springboot.models.User;
import com.ctac.springboot.repository.PostRepository;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public List <Post> findLatest5(){
        return this.postRepository.findLatest5Posts(PageRequest.of(0,5));
    }
    
    @Override
    public Post create(Post post) {
    return this.postRepository.save(post);
    }
    
    @Override
    public Post edit(Post post) {
    return this.postRepository.save(post);
    }
    
    @Override
    public void deleteById(Long id) {
    this.postRepository.deleteById(id);
    }

    @Override
    public Page<Post> postPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.postRepository.findAll(pageable);
    }
    
}
