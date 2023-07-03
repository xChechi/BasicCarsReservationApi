package com.example.demo.controller;

import com.example.demo.dto.car.CarResponse;
import com.example.demo.dto.reservation.ReservationCarUpdateRequest;
import com.example.demo.dto.reservation.ReservationPeriodUpdateRequest;
import com.example.demo.dto.reservation.ReservationRequest;
import com.example.demo.dto.reservation.ReservationResponse;
import com.example.demo.dto.user.UserPasswordUpdateRequest;
import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.dto.user.UserUpdateRequest;
import com.example.demo.service.CarService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Car;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @Autowired
    ReservationService reservationService;

    //============================================ USER =====================================================

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

    //================================================ CARS ===========================================================

    @GetMapping("/{id}/cars")
    public List<Car> findAllCars () {
        return carService.findAllCars();
    }

    @GetMapping("/{id}/{carId}")
    public ResponseEntity<CarResponse> findCarById (@PathVariable Integer carId) {
        CarResponse carResponse = carService.findCarById(carId);
        return ResponseEntity.status(HttpStatus.FOUND).body(carResponse);
    }

    //============================================== RESERVATIONS =====================================================

    @PostMapping("/{userId}/book")
    public ResponseEntity<String> bookReservation (@PathVariable Integer userId, @Valid @RequestBody ReservationRequest request) {

        ReservationResponse reservationResponse = reservationService.bookReservation(userId, request);

        String response = String.format("Your reservation has been created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}/reservations/{reservationId}")
    public ResponseEntity<ReservationResponse> findReservationById (@PathVariable Integer reservationId) {

        return ResponseEntity.status(HttpStatus.FOUND).body(reservationService.findReservationById(reservationId));
    }

    @DeleteMapping("/{userId}/reservations/{reservationId}/delete-reservation")
    public ResponseEntity<String> deleteReservationById (@PathVariable Integer reservationId) {
        reservationService.deleteReservationById(reservationId);
        return ResponseEntity.status(HttpStatus.OK).body("Reservation has been deleted");
    }

    @PutMapping("/{userId}/reservations/{reservationId}/update-car")
    public ResponseEntity<ReservationResponse> updateCar (@PathVariable Integer reservationId, @Valid @RequestBody ReservationCarUpdateRequest request) {

        ReservationResponse reservationResponse = reservationService.updateCarReservation(reservationId, request);
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponse);
    }

    @PutMapping("/{userId}/reservations/{reservationId}/update-period")
    public ResponseEntity<ReservationResponse> updatePeriod (@PathVariable Integer reservationId, @Valid @RequestBody ReservationPeriodUpdateRequest request) {

        ReservationResponse reservationResponse = reservationService.updatePeriodReservation(reservationId, request);
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponse);
    }
}
