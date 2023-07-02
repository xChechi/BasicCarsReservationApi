package com.example.demo.converter;

import com.example.demo.dto.car.CarRequest;
import com.example.demo.dto.car.CarResponse;
import org.springframework.stereotype.Component;
import com.example.demo.entity.Car;

@Component
public class CarConverter {

    public Car addCar (CarRequest request) {

        return Car.builder()
                .make(request.getMake())
                .model(request.getModel())
                .seats(request.getSeats())
                .dailyCharge(request.getDailyCharge())
                .build();
    }

    public CarResponse toCarResponse (Car car) {

        return new CarResponse(car.getId(), car.getMake(), car.getModel(), car.getSeats(), car.getDailyCharge());
    }

}
