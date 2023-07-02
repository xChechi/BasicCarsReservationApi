package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationCarUpdateRequest;
import com.example.demo.dto.reservation.ReservationPeriodUpdateRequest;
import com.example.demo.dto.reservation.ReservationRequest;
import com.example.demo.dto.reservation.ReservationResponse;
import com.example.demo.entity.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAllReservations ();

    List<ReservationResponse> getAllReservations();

    ReservationResponse findReservationById (Integer id);

    ReservationResponse bookReservation (Integer userId, Integer carId, ReservationRequest request);

    ReservationResponse updateCarReservation (Integer id, ReservationCarUpdateRequest request);

    ReservationResponse updatePeriodReservation (Integer id, ReservationPeriodUpdateRequest request);

    void deleteReservationById (Integer id);
}
