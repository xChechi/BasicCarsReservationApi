package com.example.demo.dto.reservation;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationPeriodUpdateRequest {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
