package com.example.demo.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarUpdateRequest {

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @NotNull
    private int seats;

    @NotNull
    private double dailyCharge;

}
