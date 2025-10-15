package com.todo.todoapi.user.service;

import com.todo.todoapi.user.dto.in.LoginRequest;
import com.todo.todoapi.user.dto.in.RegisterRequest;
import com.todo.todoapi.user.dto.out.LoginResponse;
import com.todo.todoapi.user.dto.out.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;


public interface IUserService {
    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    UserResponse getProfile(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    void deleteUser(Long userId);

    UserResponse updateUser(Long userId, RegisterRequest updateRequest);

    UserDetails loadUserEntityByUsername(String email);
}
