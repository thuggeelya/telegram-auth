package ru.thuggeelya.telegramauth.service;

import ru.thuggeelya.telegramauth.dao.entity.User;
import ru.thuggeelya.telegramauth.dto.UserDto;

public interface AuthService {

    User login(final UserDto dto);
}
