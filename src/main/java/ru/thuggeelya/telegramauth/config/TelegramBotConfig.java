package ru.thuggeelya.telegramauth.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    private String token;
}
