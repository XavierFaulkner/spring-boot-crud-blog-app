package com.ctac.springboot.services;

import com.ctac.springboot.controllers.dto.UserRegistrationDto;
import com.ctac.springboot.models.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    List<User> getAllUsers();

    User findByEmail(String email);

    Page<User> userPagination(int pageNo, int pageSize);
  
    User getUserByUsername(String username);
}
