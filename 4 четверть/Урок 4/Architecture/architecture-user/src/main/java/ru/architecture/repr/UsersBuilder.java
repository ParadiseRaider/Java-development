package ru.architecture.repr;

import ru.architecture.model.Role;
import ru.architecture.model.User;

import java.util.List;

public class UsersBuilder {
    private User user;

    public UsersBuilder() {
        user = new User();
    }

    public UsersBuilder id(Integer id) {
        user.setId(id);
        return this;
    }

    public UsersBuilder name(String name) {
        user.setName(name);
        return this;
    }

    public UsersBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public UsersBuilder roles(List<Role> roles) {
        user.setRoles(roles);
        return this;
    }

    public User build() {
        return user;
    }
}
