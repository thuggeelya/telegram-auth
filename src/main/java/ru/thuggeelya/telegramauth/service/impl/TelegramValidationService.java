package ru.thuggeelya.telegramauth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.thuggeelya.telegramauth.service.ValidationService;

import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.ofNullable;
import static ru.thuggeelya.telegramauth.util.StringUtils.hex;
import static ru.thuggeelya.telegramauth.util.StringUtils.hmacSha256;

@Slf4j
@Service
public class TelegramValidationService implements ValidationService {

    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public boolean validate(final Map<String, String> data) {

        final String hash = ofNullable(data.remove("hash")).orElseThrow(
                () -> new IllegalStateException("Hash is not present")
        );

        final String dataCheckString = String.join(
                "\n", data.entrySet().stream()
                          .sorted(Map.Entry.comparingByKey())
                          .map(e -> e.getKey() + "=" + e.getValue())
                          .toList()
        );

        try {
            final byte[] secretKey = hmacSha256(token.getBytes(UTF_8), "WebAppData".getBytes(UTF_8));
            return hex(hmacSha256(dataCheckString.getBytes(UTF_8), secretKey)).equalsIgnoreCase(hash);
        } catch (final Exception ex) {

            log.error("Couldn't check hash: {}", ex.getMessage(), ex);
            return false;
        }
    }
}
