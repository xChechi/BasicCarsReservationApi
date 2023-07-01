package com.example.demo.service.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserPasswordUpdateRequest;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse findUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {throw new RuntimeException("User not found"); });
        return userConverter.toUserResponse(user);
    }

    @Override
    public UserResponse findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> { throw new RuntimeException("User not found");});
        return userConverter.toUserResponse(user);
    }

    @Override
    public void deleteUserById(Integer id) {

        userRepository.deleteById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.findAll().stream().anyMatch( user -> user.getEmail().equals(email));
    }

    @Override
    public UserResponse createUser(UserRequest request) {

        if (existByEmail(request.getEmail())) {
            return userConverter.toError("User already exist with this email");
        }

        User user = userConverter.createUser(request);
        User savedUser = userRepository.save(user);
        return userConverter.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Integer id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow( () -> { throw new RuntimeException("User not exist"); } );

        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());

        User savedUser = userRepository.save(user);
        return userConverter.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUserPassword(Integer id, UserPasswordUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow( () -> { throw new RuntimeException("User not exist"); } );

        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);
        return userConverter.toUserResponse(savedUser);
    }
}
