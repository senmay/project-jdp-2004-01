package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "banUser")
    public UserDto banUser(@RequestParam Long userId) {
        return new UserDto(1L, "BannedUser", false, null);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "keyGenerator" )
    public UserDto apiKeyGenerator(@RequestBody UserDto userDto) {
        return new UserDto(2L, "KeyAddedToUser", true, "123asd");
    }
}