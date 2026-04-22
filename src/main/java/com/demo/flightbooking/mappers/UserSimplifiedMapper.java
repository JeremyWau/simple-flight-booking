package com.demo.flightbooking.mappers;

import com.demo.flightbooking.dtos.UserDto;
import com.demo.flightbooking.entities.UserSimplified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSimplifiedMapper implements Mapper<UserDto, UserSimplified> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto mapToDto(UserSimplified userSimplified) {
        UserDto userDto = new UserDto();
        userDto.setUsername(userSimplified.getUsername());
        return userDto;
    }

    @Override
    public UserSimplified mapToEntity(UserDto userDto) {
        UserSimplified userSimplified = new UserSimplified();
        userSimplified.setUsername(userDto.getUsername());
        userSimplified.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userSimplified;
    }
}
