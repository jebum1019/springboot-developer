package me.shinsunyuong.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyuong.springbootdeveloper.domain.RefreshToken;
import me.shinsunyuong.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("몰라용"));
    }
}
