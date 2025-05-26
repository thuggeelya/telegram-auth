package ru.thuggeelya.telegramauth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.thuggeelya.telegramauth.dao.UserRepository;
import ru.thuggeelya.telegramauth.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
}
