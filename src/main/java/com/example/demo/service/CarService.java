package com.example.demo.service;

import com.example.demo.dto.car.CarRequest;
import com.example.demo.dto.car.CarResponse;
import com.example.demo.dto.car.CarUpdateRequest;
import com.example.demo.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAllCars ();

    CarResponse findCarById (Integer id);

    void deleteCarById (Integer id);

    CarResponse addNewCar (CarRequest request);

    CarResponse updateCar (Integer id, CarUpdateRequest request);

}
