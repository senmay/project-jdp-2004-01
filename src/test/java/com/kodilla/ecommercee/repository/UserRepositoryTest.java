package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user1;
    private User user2;

    @Before
    public void createUsers() {
        user1 = new User(1L, "Imie1", false, "token1");
        user2 = new User(2L, "imie2", true, "token2");
    }

    @Test
    public void testFindUserSize() {
        //Given
        userRepository.save(user1);
        userRepository.save(user2);
        //When
        List<User> users = userRepository.findAll();
        //Then
        assertEquals(2, users.size());
    }

    @Test
    public void testSaveUser() {
        //Given
        User user = new User();
        //When
        userRepository.save(user);
        Long userId = user.getId();
        //Then
        assertNotEquals(0, (long) userId);
    }

    @Test
    public void testFindUserById() {
        //Given
        userRepository.save(user1);
        userRepository.save(user2);
        Long userIdToFind = user1.getId();
        //When
        Optional<User> userOptional = userRepository.findById(userIdToFind);
        //Then
        assertTrue(userOptional.isPresent());
        assertEquals(user1, userOptional.get());
    }

    @Test
    public void testDeleteById() {
        //Given
        userRepository.save(user1);
        userRepository.save(user2);
        Long userIdNeedToFind = user2.getId();
        Long userIdToDelete= user1.getId();
        //When
        userRepository.deleteById(userIdToDelete);
        List<User> users = userRepository.findAll();
        Optional<User> optionalUser = userRepository.findById(userIdNeedToFind);
        //Then
        assertTrue(optionalUser.isPresent());
        assertEquals(user2, optionalUser.get());
        assertEquals(1, users.size());
    }
}