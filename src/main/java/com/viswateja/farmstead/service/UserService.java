package com.viswateja.farmstead.service;

import com.viswateja.farmstead.Repository.UserRepository;
import com.viswateja.farmstead.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User getUserWithEmailAddress(String email) {
        return userRepository.findUserByEmailAddress(email);
    }

    public Optional<User> getUserByID(Long id) {
        return userRepository.findById(id);
    }
}
