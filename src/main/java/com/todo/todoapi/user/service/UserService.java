package com.todo.todoapi.user.service;

import com.todo.todoapi.user.dto.*;
import com.todo.todoapi.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface UserService {
    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    UserResponse getProfile(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void deleteUser(Long userId);
    UserResponse updateUser(Long userId, RegisterRequest updateRequest);
    UserDetails loadUserEntityByUsername(String email);
}
