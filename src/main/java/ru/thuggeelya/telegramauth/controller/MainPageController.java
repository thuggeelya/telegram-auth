package ru.thuggeelya.telegramauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.thuggeelya.telegramauth.service.UserService;

import static ru.thuggeelya.telegramauth.util.PageValues.INDEX;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final UserService userService;

    @GetMapping("/")
    public String indexPage(final Model model) {
        return INDEX;
    }
}
