package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserUpdateRequest {

    @NotBlank
    @Length(min = 2)
    private String firstname;

    @NotBlank
    @Length(min = 2)
    private String lastname;

    @Email
    private String email;

}
