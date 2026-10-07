package com.wasil.journal_app.controllers;

import com.wasil.journal_app.dto.journals.JournalResponse;
import com.wasil.journal_app.dto.user.UserRequest;
import com.wasil.journal_app.dto.user.UserResponse;
import com.wasil.journal_app.services.JournalService;
import com.wasil.journal_app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final JournalService journalService;

    public AdminController(UserService userService, JournalService journalService){
        this.userService = userService;
        this.journalService = journalService;
    }
    @PostMapping("/user")
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }
    @GetMapping("/user")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/user/{userId}")
    public UserResponse getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
    @GetMapping("/journal")
    public List<JournalResponse> getAllJournals() {
        return journalService.getAllJournals();
    }
    @GetMapping("/journal/{journalId}")
    public JournalResponse getJournalById(@PathVariable Long journalId){
        return journalService.getJournalById(journalId);
    }
    @GetMapping("/journal/{userId}")
    public List<JournalResponse> getJournalsByUserId(@PathVariable Long userId){
        return journalService.getJournalsByUserId(userId);
    }
}
