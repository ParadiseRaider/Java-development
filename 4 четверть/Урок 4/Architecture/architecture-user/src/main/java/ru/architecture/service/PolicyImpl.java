package ru.architecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PolicyImpl implements Policy {
    private UserService userService;
    private AdminService adminService;

    @Autowired
    public PolicyImpl(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @Override
    public List<String> readListFiles() {
        return adminService.readListFiles();
    }

    @Override
    public String readLogs(String logName) {
        return adminService.readLogs(logName);
    }

    @Override
    public String getVideo() {
        return adminService.getVideo();
    }

    @Override
    public void reboot() {
        adminService.reboot();
    }

    @Override
    public void withDrawMoney(Integer amount) {
        userService.withDrawMoney(amount);
    }

    @Override
    public Integer depositMoney() {
        return userService.depositMoney();
    }
}
