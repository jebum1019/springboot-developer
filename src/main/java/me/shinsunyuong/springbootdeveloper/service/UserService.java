package me.shinsunyuong.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyuong.springbootdeveloper.domain.User;
import me.shinsunyuong.springbootdeveloper.dto.AddUSerRequest;
import me.shinsunyuong.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUSerRequest dto) {
        return userRepository.save(User.builder().email(dto.getEmail())
                //패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("몰라용"));
    }
}
