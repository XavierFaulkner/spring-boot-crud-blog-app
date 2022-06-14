package com.ctac.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}