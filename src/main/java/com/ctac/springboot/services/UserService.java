package com.ctac.springboot.services;

import com.ctac.springboot.controllers.dto.UserRegistrationDto;
import com.ctac.springboot.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
