package com.example.demo.service.impl;

import com.example.demo.converter.CarConverter;
import com.example.demo.dto.car.CarRequest;
import com.example.demo.dto.car.CarResponse;
import com.example.demo.dto.car.CarUpdateRequest;
import com.example.demo.entity.Car;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.exception.DuplicateCarException;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public CarResponse findCarById(Integer id) {
        Car car = carRepository.findById(id).orElseThrow( () -> new CarNotFoundException("Car not found"));
        return carConverter.toCarResponse(car);
    }

    @Override
    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarResponse addNewCar(CarRequest request) {

        Car car = carConverter.addCar(request);
        Car savedCar;

        try {
            savedCar = carRepository.save(car);
        } catch (DataIntegrityViolationException e) {

            throw new DuplicateCarException("License plate already exists");
        }

        return carConverter.toCarResponse(savedCar);
    }

    @Override
    public CarResponse updateCar(Integer id, CarUpdateRequest request) {

        Car car = carRepository.findById(id).orElseThrow( () -> new CarNotFoundException("Car not found"));

        car.setMake(request.getMake());
        car.setModel(request.getModel());
        car.setSeats(request.getSeats());
        car.setDailyCharge(request.getDailyCharge());

        Car savedCar = carRepository.save(car);
        return carConverter.toCarResponse(savedCar);
    }
}
