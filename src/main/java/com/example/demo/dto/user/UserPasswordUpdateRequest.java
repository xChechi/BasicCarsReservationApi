package com.example.demo.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserPasswordUpdateRequest {

    @NotBlank
    @Length(min = 8)
    private String password;

}
