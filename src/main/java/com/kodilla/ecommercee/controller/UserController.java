package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<User> getUsers() {
        return dbUserService.getAllUsers();
    }

    @PutMapping(value = "banUser")
    private void banUser(@RequestParam Long userId) {
        dbUserService.banUser(userId);
    }

    @PutMapping(value = "keyGenerator")
    private void apiKeyGenerator(@RequestParam Long userId) {
       dbUserService.generateApiKey(userId);
    }

    @GetMapping(value ="getUser")
    public Optional<User> getUser(@RequestParam Long userId) {
        return dbUserService.getUser(userId);
    }

    @DeleteMapping(value ="deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        dbUserService.deleteUser(userId);
    }
}