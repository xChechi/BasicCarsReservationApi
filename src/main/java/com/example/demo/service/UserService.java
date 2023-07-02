package com.example.demo.service;


import com.example.demo.dto.user.UserPasswordUpdateRequest;
import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.dto.user.UserUpdateRequest;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers ();

    UserResponse findUserById (Integer id);

    UserResponse findUserByEmail (String email);

    void deleteUserById (Integer id);

    boolean existByEmail (String email);

    UserResponse createUser (UserRequest request);

    UserResponse updateUser(Integer id, UserUpdateRequest request);

    UserResponse updateUserPassword (Integer id, UserPasswordUpdateRequest request);
}
