package com.todo.todoapi.user.controller;

import com.todo.todoapi.common.ApiResponse;
import com.todo.todoapi.common.Messages;
import com.todo.todoapi.user.dto.LoginRequest;
import com.todo.todoapi.user.dto.LoginResponse;
import com.todo.todoapi.user.dto.RegisterRequest;
import com.todo.todoapi.user.dto.UserResponse;
import com.todo.todoapi.user.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;

    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    private String msg(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @RequestBody RegisterRequest request,
            Locale locale
    ) {
        UserResponse response = userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(msg(Messages.USER_REGISTER_SUCCESS, locale), response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request,
            Locale locale
    ) {
        LoginResponse response = userService.login(request);
        return ResponseEntity
                .ok(ApiResponse.success(msg(Messages.USER_LOGIN_SUCCESS, locale), response));
    }

    @GetMapping("/{email}")
    public ResponseEntity<ApiResponse<UserResponse>> getProfile(
            @PathVariable String email,
            Locale locale
    ) {
        UserResponse response = userService.getProfile(email);
        return ResponseEntity
                .ok(ApiResponse.success(msg(Messages.USER_PROFILE_FETCHED, locale), response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @RequestBody RegisterRequest updateRequest,
            Locale locale
    ) {
        UserResponse response = userService.updateUser(id, updateRequest);
        return ResponseEntity
                .ok(ApiResponse.success(msg(Messages.USER_UPDATE_SUCCESS, locale), response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable Long id,
            Locale locale
    ) {
        userService.deleteUser(id);
        return ResponseEntity
                .ok(ApiResponse.success(msg(Messages.USER_DELETE_SUCCESS, locale), null));
    }
}
