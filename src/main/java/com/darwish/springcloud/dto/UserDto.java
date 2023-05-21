package com.darwish.springcloud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "user first name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "user last name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "user email should not be null or empty")
    @Email(message = "Email address should be well valid")
    private String email;

}
