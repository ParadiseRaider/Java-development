package ru.architecture.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.architecture.model.User;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class IdentityMap {
    private Map<Integer, User> users = new HashMap<>();
    private final DataMapper dataMapper;

    @Autowired
    public IdentityMap(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public User fider(Integer id) throws SQLException {
        User user = users.get(id);
        if (user==null) {
            user = dataMapper.findById(id).orElseThrow(SQLDataException::new);
            users.put(user.getId(),user);
        }
        return user;
    }
}
