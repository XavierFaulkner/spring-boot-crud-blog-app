package com.ctac.springboot.controllers;

import com.ctac.springboot.controllers.dto.UserRegistrationDto;
import com.ctac.springboot.services.UserService;

import com.ctac.springboot.models.User;
//import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

   /*  @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/register?success";
    }*/

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
        BindingResult result, Model model) {

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            model.addAttribute("errorMessage", "A user with this email already exists.");
            return "register";
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(userDto);
        return "redirect:/register?success";
    }
}
