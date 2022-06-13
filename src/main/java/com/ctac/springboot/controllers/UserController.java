package com.ctac.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ctac.springboot.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String viewUserTable(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "users";
    }
}
