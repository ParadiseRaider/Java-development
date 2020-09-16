package ru.architecture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.architecture.service.Policy;

@Controller
public class AdminController {
    private final Policy policy;

    @Autowired
    public AdminController(Policy policy) {
        this.policy = policy;
    }

    @GetMapping("/logsFiles")
    public String readLogsPage(Model model) {
        model.addAttribute("listLogFiles", policy.readListFiles());
        return "...";
    }

    @GetMapping("/videoPage")
    public String readVideoPage(Model model) {
        model.addAttribute("videoFiles", policy.getVideo());
        return "...";
    }

    @GetMapping("/reboot")
    public String reboot() {
        policy.reboot();
        return "...";
    }

    @PostMapping("/logFile")
    public String readLogPage(String nameFile, Model model) {
        model.addAttribute("logFile", policy.readLogs(nameFile));
        return "...";
    }
}
