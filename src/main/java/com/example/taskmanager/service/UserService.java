package com.example.taskmanager.service;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(String name) {
        return userRepository.save(new User(name));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с ID: " + id + " не найдён"));
    }

    public void deleteUser(Long id) {
        User taskForDelete = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с ID: " + id + " не найдён"));
        userRepository.delete(taskForDelete);
    }
}
