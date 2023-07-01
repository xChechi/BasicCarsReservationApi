package com.example.demo.controller;

import com.example.demo.dto.UserPasswordUpdateRequest;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById (@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@Valid @RequestBody UserRequest request) {
        UserResponse userResponse = userService.createUser(request);

        if(!(userResponse.getError() == null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exist");
        }

        String response = String.format("User with name %s %s has been created", request.getFirstname(), request.getLastname());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}/delete-account")
    public ResponseEntity<String> deleteUserById (@PathVariable Integer id) {
        userService.deleteUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body("User with ID: " + id + " has been deleted");
    }

    @PutMapping("/{id}/update-account")
    public ResponseEntity<UserResponse> updateUser (@PathVariable Integer id, @Valid @RequestBody UserUpdateRequest request) {

        UserResponse userResponse = userService.updateUser(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/{id}/update-password")
    public ResponseEntity<UserResponse> updateUserPassword (@PathVariable Integer id, @Valid @RequestBody UserPasswordUpdateRequest request) {

        UserResponse userResponse = userService.updateUserPassword(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
