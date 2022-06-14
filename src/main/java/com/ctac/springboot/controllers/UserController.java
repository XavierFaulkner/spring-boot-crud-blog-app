package com.ctac.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctac.springboot.services.UserService;
import com.ctac.springboot.models.User;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // @GetMapping("/users")
    // public String viewUserTable(Model model) {
    //     model.addAttribute("listUsers", userService.getAllUsers());
    //     return "users";
    // }

    @GetMapping("/users")
    public String viewUserTable(Model model) {
        return userPagination(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String userPagination(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page <User> page = userService.userPagination(pageNo, pageSize);
        List <User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listUsers", listUsers);
        
        return "users";
    }

}
