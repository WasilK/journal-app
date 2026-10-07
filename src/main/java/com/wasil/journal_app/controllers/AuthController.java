package com.wasil.journal_app.controllers;

import com.wasil.journal_app.dto.user.UserRequest;
import com.wasil.journal_app.dto.user.UserResponse;
import com.wasil.journal_app.services.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest request){
        return authService.login(request);
    }
    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserRequest request){
        return authService.signup(request);
    }
}
