package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationCarUpdateRequest;
import com.example.demo.dto.reservation.ReservationPeriodUpdateRequest;
import com.example.demo.dto.reservation.ReservationRequest;
import com.example.demo.dto.reservation.ReservationResponse;
import com.example.demo.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    List<ReservationResponse> getAllReservations();

    ReservationResponse findReservationById (Integer id);

    ReservationResponse bookReservation (Integer userId, ReservationRequest request);

    ReservationResponse updateCarReservation (Integer id, ReservationCarUpdateRequest request);

    ReservationResponse updatePeriodReservation (Integer id, ReservationPeriodUpdateRequest request);

    void deleteReservationById (Integer id);

    List<ReservationResponse> searchByUser (Integer userId);

    List<ReservationResponse> searchByCar (Integer carId);

    List<Reservation> findReservationsWithinPeriod(LocalDate startDate, LocalDate endDate);
}
