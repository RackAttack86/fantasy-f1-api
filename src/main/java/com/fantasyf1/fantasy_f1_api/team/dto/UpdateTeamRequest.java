package com.fantasyf1.fantasy_f1_api.team.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamRequest {
    // Update request fields are all optional (no @NotBlank) to allow partial updates

    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 255, message = "Full name must be at most 255 characters")
    private String fullName;

    @Size(max = 100, message = "Base must be at most 100 characters")
    private String base;

    @Size(max = 100, message = "Team principal must be at most 100 characters")
    private String teamPrincipal;
}
