package ru.architecture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.architecture.service.Policy;

@Controller
public class UserController {
    private final Policy policy;

    @Autowired
    public UserController(Policy policy) {
        this.policy = policy;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/deposit")
    public String depositPage(Model model) {
        model.addAttribute("deposit", policy.depositMoney());
        return "...";
    }

    @PostMapping("/take")
    public String takeMoney(Integer amount, Model model) {
        model.addAttribute("TakeMoney", "money");
        policy.withDrawMoney(amount);
        return "redirect:/...";
    }
}
