package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.isActive(),
                userDto.getApiKey());
    }

//    public UserDto mapToUserDto(final User user) { czy ten mapper jest potrzebny?
//        return new UserDto(
//                user.getId(),
//                user.getName(),
//                user.isActive(),
//                user.getApiKey());
//    }

}