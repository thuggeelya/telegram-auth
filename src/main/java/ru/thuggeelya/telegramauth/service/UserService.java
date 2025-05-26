package ru.thuggeelya.telegramauth.service;

import ru.thuggeelya.telegramauth.dao.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(final Long id);

    User save(final User user);
}
