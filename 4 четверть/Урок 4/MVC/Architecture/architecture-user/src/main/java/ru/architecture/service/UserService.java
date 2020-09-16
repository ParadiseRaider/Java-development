package ru.architecture.service;

public interface UserService {
    void withDrawMoney(Integer amount);
    Integer depositMoney();
    void payService(String s);
}
