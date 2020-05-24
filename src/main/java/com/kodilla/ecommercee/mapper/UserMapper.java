package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.isActive(),
                userDto.getApiKey());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.isActive(),
                user.getApiKey());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}