package com.frt.api.models.dtos.auth;

public record TokenResponseDTO(
    String accessToken,
    String tokenType,
    String role,
    Long userId,
    String userName
) {
    // No additional constructor is needed as the record already provides a canonical constructor
}