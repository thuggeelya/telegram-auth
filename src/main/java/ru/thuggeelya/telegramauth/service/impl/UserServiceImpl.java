package ru.thuggeelya.telegramauth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thuggeelya.telegramauth.dao.UserRepository;
import ru.thuggeelya.telegramauth.dao.entity.User;
import ru.thuggeelya.telegramauth.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(final User user) {
        return userRepository.save(user);
    }
}
