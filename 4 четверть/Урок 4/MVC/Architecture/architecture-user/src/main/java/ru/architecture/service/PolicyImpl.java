package ru.architecture.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PolicyImpl implements Policy {
    private final UserService userService;
    private final AdminService adminService;

    public PolicyImpl() {
        this.userService = new UserServiceImpl();
        this.adminService = new AdminServiceImpl();
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

    @Override
    public void payService(String s) {
        userService.payService(s);
    }
}
