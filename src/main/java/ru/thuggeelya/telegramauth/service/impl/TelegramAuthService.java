package ru.thuggeelya.telegramauth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thuggeelya.telegramauth.dao.entity.User;
import ru.thuggeelya.telegramauth.dto.UserDto;
import ru.thuggeelya.telegramauth.service.AuthService;
import ru.thuggeelya.telegramauth.service.UserService;

@Service
@RequiredArgsConstructor
public class TelegramAuthService implements AuthService {

    private final UserService userService;

    @Override
    @Transactional
    public User login(final UserDto dto) {

        final User user = userService.findById(dto.getId())
                                     .map(existing -> {

                                         existing.setFirstName(dto.getFirstName());
                                         existing.setLastName(dto.getLastName());
                                         existing.setUsername(dto.getUsername());
                                         existing.setPhotoUrl(dto.getPhotoUrl());

                                         return existing;
                                     })
                                     .orElseGet(
                                             () -> User.builder()
                                                       .id(dto.getId())
                                                       .firstName(dto.getFirstName())
                                                       .lastName(dto.getLastName())
                                                       .username(dto.getUsername())
                                                       .photoUrl(dto.getPhotoUrl())
                                                       .build()
                                     );

        return userService.save(user);
    }
}
