package ru.architecture.service;

import java.util.List;

public interface AdminService {
    List<String> readListFiles();
    String readLogs(String logName);
    String getVideo();
    void reboot();
}
