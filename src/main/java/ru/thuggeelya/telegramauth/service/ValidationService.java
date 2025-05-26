package ru.thuggeelya.telegramauth.service;

import java.util.Map;

public interface ValidationService {

    boolean validate(final Map<String, String> data);
}
