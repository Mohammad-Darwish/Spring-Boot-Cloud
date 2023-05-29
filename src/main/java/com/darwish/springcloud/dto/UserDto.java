package com.darwish.springcloud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
    description = "UserDto model information"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @Schema(
        description = "User First name"
    )
    @NotEmpty(message = "user first name should not be null or empty")
    private String firstName;

    @Schema(
        description = "User Last name"
    )
    @NotEmpty(message = "user last name should not be null or empty")
    private String lastName;

    @Schema(
        description = "User Email Address"
    )
    @NotEmpty(message = "user email should not be null or empty")
    @Email(message = "Email address should be well valid")
    private String email;

}
