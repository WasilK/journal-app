package com.wasil.journal_app.controllers;

import com.wasil.journal_app.dto.user.UserRequest;
import com.wasil.journal_app.dto.user.UserResponse;
import com.wasil.journal_app.services.QuoteService;
import com.wasil.journal_app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final QuoteService quoteService;

    public UserController(UserService userService, QuoteService quoteService){
        this.userService = userService;
        this.quoteService = quoteService;
    }
    @GetMapping("/me")
    public UserResponse getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getMyProfile(username);
    }
    @PutMapping("/me")
    public UserResponse updateUser(@RequestBody UserRequest userRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.updateUser(username, userRequest);
    }
    @GetMapping("/quote")
    public ResponseEntity<?> getQuote(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>("Hi, " + username + " Here's a quick advice for you! " + quoteService.getQuote().getAdvice(), HttpStatus.OK);
    }
}
