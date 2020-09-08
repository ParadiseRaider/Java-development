package ru.architecture.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Override
    public void withDrawMoney(Integer amount) {
        //Код чтоб положить денег
    }

    @Override
    public Integer depositMoney() {
        //Код чтоб узнать количество денег на счету
        return null;
    }
}
