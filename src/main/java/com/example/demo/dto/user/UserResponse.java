package com.example.demo.dto.user;

import com.example.demo.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {

    private Integer id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Email
    private String email;

    private List<Reservation> reservations;

    @JsonIgnore
    private String error;
    
    public UserResponse(String error) {
        this.error = error;
    }

    public UserResponse(Integer id, String firstname, String lastname, String email, List<Reservation> reservations) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.reservations = reservations;
    }
/*
    public UserResponse(String firstname, String lastname, String email, List<Reservation> reservations) {

    }

 */
}
