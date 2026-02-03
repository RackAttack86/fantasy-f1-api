package com.fantasyf1.fantasy_f1_api.driver.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.StandardException;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDriverRequest {
    
    @NotBlank(message = "First name is required")
    @Size(max = 50,  message = "First name must be at most 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    @NotBlank(message = "Code is required")
    @Size(min = 3, max = 3, message = "Code must be exactly 3 characters")
    @Pattern(regexp = "[A-Z]{3}$", message = "Code must be 3 uppercase letters")
    private String code;

    @NotNull(message = "Number is required")
    @Min(value = 1, message = "Number must be at least 1")
    @Max(value = 99, message = "Number must be at most 99")
    private Integer number;

    private UUID teamId;

    @Size(max = 50, message = "Nationality must be at most 50 characters")
    private String nationality;
}
