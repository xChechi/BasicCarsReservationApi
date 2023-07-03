package com.example.demo.service.impl;

import com.example.demo.converter.ReservationConverter;
import com.example.demo.dto.reservation.ReservationCarUpdateRequest;
import com.example.demo.dto.reservation.ReservationPeriodUpdateRequest;
import com.example.demo.dto.reservation.ReservationRequest;
import com.example.demo.dto.reservation.ReservationResponse;
import com.example.demo.entity.Car;
import com.example.demo.entity.Reservation;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.exception.ReservationNotFoundException;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationConverter reservationConverter;
    private final CarRepository carRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationConverter reservationConverter, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationConverter = reservationConverter;
        this.carRepository = carRepository;
    }

    @Override
    public List<Reservation> findAllReservations() {

        return reservationRepository.findAll();
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> reservationResponses = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationResponse response = reservationConverter.toReservationResponse(reservation);
            reservationResponses.add(response);
        }

        return reservationResponses;
    }

    @Override
    public ReservationResponse findReservationById(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow( () -> new ReservationNotFoundException("Reservation not exist"));
        return reservationConverter.toReservationResponse(reservation);
    }

    @Override
    public ReservationResponse bookReservation(Integer userId, ReservationRequest request) {
        Reservation reservation = reservationConverter.bookReservation(userId, request);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationConverter.toReservationResponse(savedReservation);
    }

    @Override
    public ReservationResponse updateCarReservation(Integer id, ReservationCarUpdateRequest request) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow( () -> new ReservationNotFoundException("Reservation not exist"));

        Car car = carRepository.findById(request.getCarId()).orElseThrow( () -> new CarNotFoundException("Car not exist"));

        reservation.setCar(car);

        Reservation savedReservation = reservationRepository.save(reservation);

        return reservationConverter.toReservationResponse(savedReservation);
    }

    @Override
    public ReservationResponse updatePeriodReservation(Integer id, ReservationPeriodUpdateRequest request) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow( () -> new ReservationNotFoundException("Reservation not exist"));

        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationConverter.toReservationResponse(savedReservation);
    }

    @Override
    public void deleteReservationById(Integer id) {
        reservationRepository.deleteById(id);
    }
}
