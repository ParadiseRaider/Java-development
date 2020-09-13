package ru.architecture.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.architecture.model.Role;
import ru.architecture.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataMapper {
    private final DataSource dataSource;

    @Autowired
    public DataMapper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<User> findById(int id) {
        Statement stmt;
        ResultSet rs;
        User user;
        Role role;
        List<Role> roles = new ArrayList<>();
        try {
            user = new User();
            Connection connection = dataSource.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * FROM users WHERE id=%d",id));
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            if (user.getId()==0 || user.getName().equals("") || user.getPassword().equals("")) {
                return Optional.empty();
            }
            role = new Role();
            rs = stmt.executeQuery(String.format("SELECT ur.id, ur.name FROM " +
                    "users_roles ur JOIN users u ON ur.user_id=u.id WHERE u.id=%d",user.getId()));
            while (rs.next()) {
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }
            user.setRoles(roles);
            return Optional.of(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Optional.empty();
        }
    }
}
