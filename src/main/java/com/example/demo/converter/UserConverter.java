package com.example.demo.converter;

import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User createUser (UserRequest request) {

        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword())
                .reservations(request.getReservations())
                .build();

    }

    public UserResponse toUserResponse (User user) {

        return new UserResponse(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getReservations());

    }

    public UserResponse toError(String error) {

        return new UserResponse(error);
    }
}
