package com.wasil.journal_app.services;

import com.wasil.journal_app.dto.user.UserRequest;
import com.wasil.journal_app.dto.user.UserResponse;
import com.wasil.journal_app.models.User;
import com.wasil.journal_app.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void getUserById() {
        User user = new User();
        user.setUsername("name");
        user.setPassword("password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponse result = userService.getUserById(1L);
        assertEquals("name", result.getUsername());
        verify(userRepository).findById(1L);
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void createUser() {
        UserRequest request = new UserRequest("name", "password");

        UserResponse result = userService.createUser(request);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(captor.capture());

        User saved = captor.getValue();

        assertEquals("name", saved.getUsername());
        assertEquals("password", saved.getPassword());

        assertNotNull(result);
        assertEquals("name", result.getUsername());
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        user.setUsername("oldName");
        user.setPassword("oldPassword");

        UserRequest request = new UserRequest("newName", "newPassword");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse result = userService.updateUser(userId, request);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);

        assertEquals("newName", result.getUsername());
    }
}