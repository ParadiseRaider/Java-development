package Java_core.Lesson_7.Server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:clients.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) throws SQLException {
        login = login.toLowerCase();
        String sql = String.format("SELECT nickname FROM clients where login = '%s' and password = '%s'", login, pass);
        ResultSet rs = stmt.executeQuery(sql);

        if(rs.next()) {
            return rs.getString(1);
        }

        return null;
    }

    public static String getNickByLogin(String login) throws SQLException {
        login = login.toLowerCase();
        String sql = String.format("SELECT nickname FROM clients where login = '%s'", login);
        ResultSet rs = stmt.executeQuery(sql);

        if(rs.next()) {
            return rs.getString(1);
        }

        return null;
    }

    public static void registerPerson(String login, String pass, String nick) throws SQLException {
        String sql = String.format("INSERT INTO clients (login,password,nickname) VALUES ('%s','%s','%s')",login,pass,nick);
        stmt.execute(sql);
    }


    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
