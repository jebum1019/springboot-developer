package me.shinsunyuong.springbootdeveloper.repository;

import me.shinsunyuong.springbootdeveloper.domain.RefreshToken;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Override
    Optional<RefreshToken> findById(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
