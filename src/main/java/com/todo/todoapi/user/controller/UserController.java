package com.todo.todoapi.user.controller;

import com.todo.todoapi.common.ApiResponse;
import com.todo.todoapi.common.Messages;
import com.todo.todoapi.user.dto.in.LoginRequest;
import com.todo.todoapi.user.dto.out.LoginResponse;
import com.todo.todoapi.user.dto.in.RegisterRequest;
import com.todo.todoapi.user.dto.out.UserResponse;
import com.todo.todoapi.user.service.IUserService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService IUserService;
    private final MessageSource messageSource;

    public UserController(IUserService IUserService, MessageSource messageSource) {
        this.IUserService = IUserService;
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
        UserResponse response = IUserService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(msg(Messages.USER_REGISTER_SUCCESS, locale), response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request,
            Locale locale
    ) {
        LoginResponse response = IUserService.login(request);
        return ResponseEntity
                .ok(ApiResponse.success(msg(Messages.USER_LOGIN_SUCCESS, locale), response));
    }

    @GetMapping("/{email}")
    public ResponseEntity<ApiResponse<UserResponse>> getProfile(
            @PathVariable String email,
            Locale locale
    ) {
        UserResponse response = IUserService.getProfile(email);
        return ResponseEntity
                .ok(ApiResponse.success(msg(Messages.USER_PROFILE_FETCHED, locale), response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @RequestBody RegisterRequest updateRequest,
            Locale locale
    ) {
        UserResponse response = IUserService.updateUser(id, updateRequest);
        return ResponseEntity
                .ok(ApiResponse.success(msg(Messages.USER_UPDATE_SUCCESS, locale), response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable Long id,
            Locale locale
    ) {
        IUserService.deleteUser(id);
        return ResponseEntity
                .ok(ApiResponse.success(msg(Messages.USER_DELETE_SUCCESS, locale), null));
    }
}
