package com.REDACTED.fantasy_f1_api.user.mapper;

import com.REDACTED.fantasy_f1_api.user.dto.UserResponse;
import com.REDACTED.fantasy_f1_api.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
