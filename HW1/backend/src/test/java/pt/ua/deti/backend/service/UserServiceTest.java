package pt.ua.deti.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.deti.backend.entity.User;
import pt.ua.deti.backend.repository.UserRepo;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock(lenient = true)
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void whenValidUser_thenUserShouldBeSaved() {
        User user = new User("ze", "ze@example.com");
        when(userRepo.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertEquals(user.getEmail(), savedUser.getEmail());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void whenUserEmail_thenUserShouldBeFound() {
        User user = new User("ze", "ze@example.com");
        when(userRepo.findByEmail(anyString())).thenReturn(user);

        User foundUser = userService.getUserByEmail("ze@example.com");

        assertEquals(user.getEmail(), foundUser.getEmail());
        verify(userRepo, times(1)).findByEmail("ze@example.com");
    }

    @Test
    void whenUserId_thenUserShouldBeFound() {
        User user = new User("ze", "ze@example.com");
        when(userRepo.findById(any())).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertEquals(user.getEmail(), foundUser.getEmail());
        verify(userRepo, times(1)).findById(1);
    }

    @Test
    void whenUserIdNotFound_thenUserShouldNotBeFound() {
        when(userRepo.findById(any())).thenReturn(Optional.empty());

        User foundUser = userService.getUserById(1);

        assertEquals(null, foundUser);
        verify(userRepo, times(1)).findById(1);
    }
}
