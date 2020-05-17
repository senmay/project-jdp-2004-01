package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DbUserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(final Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(final Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }

    public void banUser(final Long id) {
        if (userRepository.findById(id).isPresent()) {
            User userToBan = userRepository.findById(id).get();
            if (userToBan.isActive()) {
                userToBan.setActive(false);
            } else {
                userToBan.setActive(false);
            }
            userRepository.save(userToBan);
        }
    }

    public void generateApiKey(final Long id){
        if (userRepository.findById(id).isPresent()) {
            User userById = userRepository.findById(id).get();
            userById.setApiKey(tokenGeneration());
            userRepository.save(userById);
        }
    }

    private String tokenGeneration() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[30];
        random.nextBytes(randomBytes);
        return Arrays.toString(randomBytes);
    }
}