package com.example.demo.dto.reservation;

import com.example.demo.entity.Car;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationCarUpdateRequest {

    @NotNull
    private Car car;

}
