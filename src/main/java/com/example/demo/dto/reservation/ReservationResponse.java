package com.example.demo.dto.reservation;

import com.example.demo.dto.car.CarResponse;
import com.example.demo.dto.user.UserResponse;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponse {

    @NotNull
    private Integer id;

    @NotNull
    private UserResponse user;

    @NotNull
    private CarResponse car;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private double totalCharge;
}
