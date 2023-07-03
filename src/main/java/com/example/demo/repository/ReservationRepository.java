package com.example.demo.repository;

import com.example.demo.dto.reservation.ReservationResponse;
import com.example.demo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r JOIN r.user u WHERE u.id = :userId")
    List<ReservationResponse> searchByUser (Integer userId);

    @Query("SELECT r FROM Reservation r JOIN r.car c WHERE c.id = :carId")
    List<ReservationResponse> searchByCar (Integer carId);

    @Query("SELECT r FROM Reservation r WHERE r.startDate <= :endDate AND r.endDate >= :startDate")
    List<Reservation> findReservationsWithinPeriod (LocalDate startDate, LocalDate endDate);
}
