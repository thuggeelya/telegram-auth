package ru.thuggeelya.telegramauth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.thuggeelya.telegramauth.dao.entity.User;
import ru.thuggeelya.telegramauth.dto.UserDto;
import ru.thuggeelya.telegramauth.service.AuthService;
import ru.thuggeelya.telegramauth.service.UserService;

@Service
@RequiredArgsConstructor
public class TelegramAuthService implements AuthService {

    private final UserService userService;

    @Override
    public User login(final UserDto dto) {

        return userService.findById(dto.getId()).orElseGet(
                () -> userService.save(
                        User.builder()
                            .id(dto.getId())
                            .firstName(dto.getFirstName())
                            .lastName(dto.getLastName())
                            .username(dto.getUsername())
                            .photoUrl(dto.getPhotoUrl())
                            .build()
                )
        );
    }
}
