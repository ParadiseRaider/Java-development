package ru.architecture.service;

import ru.architecture.service.commands.Command;
import ru.architecture.service.commands.PayRoom;
import ru.architecture.service.commands.PayTel;
import ru.architecture.service.commands.PayZhkx;

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

    @Override
    public void payService(String s) {
        Command command;
        switch (s) {
            case "Zhkx":
                command = new PayZhkx();
                break;
            case "Tel":
                command = new PayTel();
                break;
            case "Room":
                command = new PayRoom();
                break;
            default:
                command = null;
                break;
        }
        if (command!=null) {
            command.execute();
        }
    }
}
