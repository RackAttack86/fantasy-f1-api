package com.fantasyf1.fantasy_f1_api.driver.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDriverRequest {
    
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    @Size(min = 3, max = 3, message = "Code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Code must be 3 uppercase letters")
    private String code;

    @Min(value = 1, message = "Number must be at least 1")
    @Max(value = 99, message = "Number must be at most 99")
    private Integer number;

    private UUID teamId;

    @Size(max = 50, message = "Nationality must be at most 50 characters")
    private String nationality;
}
