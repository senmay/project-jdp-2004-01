package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserNotFoundException;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    DbUserService dbUserService;
    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        dbUserService.saveUser(userMapper.mapToUser(userDto));
    }

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(dbUserService.getAllUsers());
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(dbUserService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }
    @PutMapping(value = "banUser")
    private void banUser(@RequestParam Long userId) throws UserNotFoundException {
        dbUserService.banUser(userId);
    }

    @PutMapping(value = "keyGenerator")
    private String apiKeyGenerator(@RequestParam Long userId) throws UserNotFoundException {
       return dbUserService.generateApiKey(userId);
    }

    @DeleteMapping(value ="deleteUser")
    public void deleteUser(@RequestParam Long userId) throws UserNotFoundException {
        dbUserService.deleteUser(userId);
    }
}