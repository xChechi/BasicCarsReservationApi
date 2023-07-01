package com.example.demo.dto;

import com.example.demo.entity.Reservation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequest {

    @NotBlank
    @Length(min = 2)
    private String firstname;

    @NotBlank
    @Length(min = 2)
    private String lastname;

    @Email
    private String email;

    @NotBlank
    @Length(min = 8)
    private String password;

    private List<Reservation> reservations;

}
