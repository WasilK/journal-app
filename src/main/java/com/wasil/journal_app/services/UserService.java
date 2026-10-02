package com.wasil.journal_app.services;

import com.wasil.journal_app.dto.user.UserRequest;
import com.wasil.journal_app.dto.user.UserResponse;
import com.wasil.journal_app.models.User;
import com.wasil.journal_app.respository.JournalsRepository;
import com.wasil.journal_app.respository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JournalsRepository journalsRepository;

    public UserService(UserRepository userRepository, JournalsRepository journalsRepository){
        this.userRepository = userRepository;
        this.journalsRepository = journalsRepository;
    }

    public UserResponse createUser(UserRequest user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return response(newUser);
    }

    private UserResponse response(User user){
        return new UserResponse(user.getUserId(), user.getUsername(), user.getPassword());
    }
}
