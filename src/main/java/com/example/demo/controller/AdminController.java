package com.example.demo.controller;

import com.example.demo.dto.car.CarRequest;
import com.example.demo.dto.car.CarResponse;
import com.example.demo.dto.car.CarUpdateRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entity.Car;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateCarException;
import com.example.demo.service.CarService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
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
    @Autowired
    CarService carService;

    @GetMapping("/users")
    public List<User> findAllUsers () {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/users", params = "email")
    public ResponseEntity<UserResponse> findUserByEmail (@RequestParam("email") String email) {
        UserResponse userResponse = userService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(userResponse);
    }

    //============================= CARS ==============================

    @GetMapping("/cars")
    public List<Car> findAllCars () {
        return carService.findAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponse> findCarById (@PathVariable Integer id) {
        CarResponse carResponse = carService.findCarById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(carResponse);
    }

    @PostMapping("/cars")
    public ResponseEntity<String> addNewCar (@Valid @RequestBody CarRequest request) {

        try {
            CarResponse carResponse = carService.addNewCar(request);
            String response = String.format("%s %s has been created", request.getMake(), request.getModel());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicateCarException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCarById (@PathVariable Integer id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<CarResponse> updateCarById (@PathVariable Integer id, @Valid @RequestBody CarUpdateRequest request) {

        CarResponse carResponse = carService.updateCar(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }

}
