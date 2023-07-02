package com.example.demo.converter;

import com.example.demo.dto.reservation.ReservationRequest;
import com.example.demo.dto.reservation.ReservationResponse;
import com.example.demo.entity.Car;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ReservationConverter {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarConverter carConverter;
    @Autowired
    UserConverter userConverter;

    public Reservation bookReservation (Integer userId, Integer carId, ReservationRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Invalid userId"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Invalid carId"));

        return Reservation.builder()
                .user(user)
                .car(car)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .totalCharge(calculateTotalCharge(request.getStartDate(), request.getEndDate(), car.getDailyCharge()))
                .build();
    }

    public double calculateTotalCharge(LocalDate startDate, LocalDate endDate, double dailyCharge) {
            long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
            return dailyCharge * numOfDays;
    }

    public ReservationResponse toReservationResponse (Reservation reservation) {

        return ReservationResponse.builder()
                .id(reservation.getId())
                .user(userConverter.toUserResponse(reservation.getUser()))
                .car(carConverter.toCarResponse(reservation.getCar()))
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .totalCharge(reservation.getTotalCharge())
                .build();

    }

}
