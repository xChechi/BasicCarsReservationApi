package com.example.demo.converter;

import com.example.demo.dto.UserRequest;
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
}
