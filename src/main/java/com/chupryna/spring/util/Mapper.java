package com.chupryna.spring.util;

import com.chupryna.spring.dto.UserDto;
import com.chupryna.spring.models.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User dtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getBirthday(),
                userDto.getRole()
        );
    }

    public UserDto userToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getRole()
        );
    }
}
