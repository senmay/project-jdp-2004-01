package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    public void deleteUser(final Long id) throws UserNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }

    @Transactional
    public void banUser(final Long id) throws UserNotFoundException {
        Optional<User> userFoundById = userRepository.findById(id);
        if (userFoundById.isPresent()) {
            User userToBan = userFoundById.get();
            userToBan.setActive(true);
            userRepository.save(userToBan);
        } else {
            throw new UserNotFoundException();
        }
    }

    public String generateApiKey (final Long id) throws UserNotFoundException{
        Optional<User> userFoundById = userRepository.findById(id);
        if (userFoundById.isPresent()) {
            User userById = userFoundById.get();
            String token = tokenGeneration();
            userById.setApiKey(token);
            userRepository.save(userById);
            return token;
        } else {
            throw new UserNotFoundException();
        }
    }

    private String tokenGeneration() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[30];
        random.nextBytes(randomBytes);
        return Arrays.toString(randomBytes);
    }
}