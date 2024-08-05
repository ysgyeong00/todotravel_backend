package org.example.todotravel.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private Long userId;
    private String nickname;
    private String role;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpirationTime;
}
