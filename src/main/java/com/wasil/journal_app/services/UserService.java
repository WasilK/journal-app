package com.wasil.journal_app.services;

import com.wasil.journal_app.dto.user.UserRequest;
import com.wasil.journal_app.dto.user.UserResponse;
import com.wasil.journal_app.exceptions.ResourceNotFoundException;
import com.wasil.journal_app.models.User;
import com.wasil.journal_app.respository.JournalsRepository;
import com.wasil.journal_app.respository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JournalsRepository journalsRepository;

    public UserService(UserRepository userRepository, JournalsRepository journalsRepository){
        this.userRepository = userRepository;
        this.journalsRepository = journalsRepository;
    }
    public UserResponse getUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
        return response(user);
    }
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(this::response).toList();
    }
    public UserResponse createUser(UserRequest user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return response(newUser);
    }
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
    public UserResponse updateUser(Long userId, UserRequest userRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this id : " + userId));
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        return response(user);
    }
    private UserResponse response(User user){
        return new UserResponse(user.getUserId(), user.getUsername(), user.getPassword());
    }
}
