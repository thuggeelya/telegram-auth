package ru.thuggeelya.telegramauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.thuggeelya.telegramauth.dao.entity.User;
import ru.thuggeelya.telegramauth.dto.UserDto;
import ru.thuggeelya.telegramauth.service.AuthService;
import ru.thuggeelya.telegramauth.service.ValidationService;

import java.util.Map;

import static ru.thuggeelya.telegramauth.util.PageValues.INDEX;
import static ru.thuggeelya.telegramauth.util.StringUtils.parseTelegramData;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final AuthService authService;
    private final ObjectMapper objectMapper;
    private final ValidationService validationService;

    @GetMapping("/")
    public String indexPage(@RequestParam(value = "initData", required = false) final String data, final Model model) {

        final Map<String, String> dataMap = parseTelegramData(data);

        if (dataMap == null) {
            return INDEX;
        }

        if (!validationService.validate(dataMap)) {

            log.error("Invalid data");
            model.addAttribute("error", "Invalid data");
            return INDEX;
        }

        final User user;

        try {
            user = authService.login(objectMapper.readValue(dataMap.get("user"), UserDto.class));
        } catch (final JsonProcessingException ex) {

            log.error("Error mapping to user: {}", ex.getMessage(), ex);
            model.addAttribute("error", "Invalid data");
            return INDEX;
        }

        model.addAttribute("user", user);

        return INDEX;
    }
}
