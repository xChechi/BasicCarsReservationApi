package com.example.demo.controller;

import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> findAllUsers () {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/users", params = "email")
    public ResponseEntity<UserResponse> findUserByEmail (@RequestParam("email") String email) {
        UserResponse userResponse = userService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(userResponse);
    }


}
