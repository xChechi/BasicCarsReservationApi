package com.example.demo.controller;

import com.example.demo.dto.car.CarRequest;
import com.example.demo.dto.car.CarResponse;
import com.example.demo.dto.car.CarUpdateRequest;
import com.example.demo.dto.reservation.ReservationResponse;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entity.Car;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateCarException;
import com.example.demo.service.CarService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @Autowired
    ReservationService reservationService;

    //========================================== USER =================================================

    @GetMapping("/users")
    public List<User> findAllUsers () {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/users", params = "email")
    public ResponseEntity<UserResponse> findUserByEmail (@RequestParam("email") String email) {
        UserResponse userResponse = userService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(userResponse);
    }

    //========================================== CARS =================================================

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

    //======================================= RESERVATIONS =====================================================

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> findAllReservations () {
        List<ReservationResponse> response = reservationService.getAllReservations();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationResponse> findReservationById (@PathVariable Integer id) {

        ReservationResponse reservationResponse = reservationService.findReservationById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponse);
    }

    @GetMapping(value = "/reservations", params = "userId")
    public ResponseEntity<List<ReservationResponse>> searchByUser (@RequestParam("userId") Integer userId) {

        List<ReservationResponse> reservationResponse = reservationService.searchByUser(userId);

        if (reservationResponse.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponse);
        }
    }

    @GetMapping(value = "/reservations", params = "carId")
    public ResponseEntity<List<ReservationResponse>> searchByCar (@RequestParam("carId") Integer carId) {

        List<ReservationResponse> reservationResponses = reservationService.searchByCar(carId);

        if(reservationResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
        }
    }

    @GetMapping("/reservations/period")
    public ResponseEntity<List<Reservation>> findReservationsWithinPeriod (
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Reservation> reservations = reservationService.findReservationsWithinPeriod(startDate, endDate);

        if(reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(reservations);
        }
    }
}
