package com.demo.flightbooking;

import com.demo.flightbooking.dtos.UserDto;
import com.demo.flightbooking.entities.UserSimplified;
import com.demo.flightbooking.exceptions.DuplicateUsernameException;
import com.demo.flightbooking.repositories.UserSimplifiedRepository;
import com.demo.flightbooking.services.security.UserSimplifiedDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserManagementTest {
    @MockitoBean
    private UserSimplifiedRepository userSimplifiedRepository;

    @Autowired
    private UserSimplifiedDetailsService userSimplifiedDetailsService;

    @Test
    void findByUsernameWithExistingUsername() {
        UserSimplified user = new UserSimplified(1L, "John", "");
        when(userSimplifiedRepository.findByUsername("John")).thenReturn(Optional.of(user));

        UserDetails result = userSimplifiedDetailsService.loadUserByUsername("John");

        assertEquals("John", result.getUsername());
        verify(userSimplifiedRepository, times(1)).findByUsername("John");
    }

    @Test
    void findByUsernameWithNonExistingUsername() {
        when(userSimplifiedRepository.findByUsername("John")).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> userSimplifiedDetailsService.loadUserByUsername("John"));
        assertEquals("User not found", exception.getMessage());
        verify(userSimplifiedRepository, times(1)).findByUsername("John");
    }

    @Test
    void createUserWithNonExistingUsername() {
        when(userSimplifiedRepository.findByUsername("John")).thenReturn(Optional.empty());
        when(userSimplifiedRepository.save(any(UserSimplified.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        UserDto userDto = new UserDto("John", "");

        UserDto result = userSimplifiedDetailsService.createUser(userDto);

        assertEquals("John", result.getUsername());
        assertNull(result.getPassword());
        verify(userSimplifiedRepository, times(1)).findByUsername("John");
    }

    @Test
    void createUserWithExistingUsername() {
        UserSimplified user = new UserSimplified(1L, "John", "");
        when(userSimplifiedRepository.findByUsername("John")).thenReturn(Optional.of(user));
        UserDto userDto = new UserDto("John", "");

        DuplicateUsernameException exception = assertThrows(DuplicateUsernameException.class, () -> {
            userSimplifiedDetailsService.createUser(userDto);
        });
        assertEquals("Error, the username is already in use: John", exception.getMessage());
        verify(userSimplifiedRepository, times(1)).findByUsername("John");
    }
}
