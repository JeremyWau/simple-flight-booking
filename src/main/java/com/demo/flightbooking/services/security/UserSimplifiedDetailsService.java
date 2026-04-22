package com.demo.flightbooking.services.security;

import com.demo.flightbooking.dtos.UserDto;
import com.demo.flightbooking.entities.UserSimplified;
import com.demo.flightbooking.exceptions.DuplicateUsernameException;
import com.demo.flightbooking.mappers.UserSimplifiedMapper;
import com.demo.flightbooking.repositories.UserSimplifiedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSimplifiedDetailsService implements UserDetailsService {

    @Autowired
    private UserSimplifiedRepository userSimplifiedRepository;

    @Autowired
    private UserSimplifiedMapper userSimplifiedMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSimplified user = userSimplifiedRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles()
                .build();
    }

    public UserDto createUser(UserDto userDto) {
        UserSimplified user = userSimplifiedMapper.mapToEntity(userDto);
        if (userSimplifiedRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateUsernameException(user.getUsername());
        }
        return userSimplifiedMapper.mapToDto(userSimplifiedRepository.save(user));
    }
}
