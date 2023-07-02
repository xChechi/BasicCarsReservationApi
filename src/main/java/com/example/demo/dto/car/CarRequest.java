package com.example.demo.dto.car;

import com.example.demo.entity.Reservation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarRequest {

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @NotNull
    private int seats;

    @NotNull
    private double dailyCharge;

    private List<Reservation> reservations;

}
