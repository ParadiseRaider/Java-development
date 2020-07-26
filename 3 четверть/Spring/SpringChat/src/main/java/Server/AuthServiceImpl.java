package Server;


import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("authService")
public class AuthServiceImpl implements AuthService {
    private Connection connection;
    private Statement stmt;
    private PreparedStatement prstmt;
    private DataSource dataSource;

    public AuthServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            this.connection = dataSource.getConnection();
            stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getNickByLoginAndPass(String login, String pass) throws SQLException {
        login = login.toLowerCase();
        String sql = String.format("SELECT nickname FROM clients where login = '%s' and password = '%s'", login, pass);
        ResultSet rs = stmt.executeQuery(sql);

        if(rs.next()) {
            return rs.getString(1);
        }

        return null;
    }

    public String getNickByLogin(String login) throws SQLException {
        login = login.toLowerCase();
        String sql = String.format("SELECT nickname FROM clients where login = '%s'", login);
        ResultSet rs = stmt.executeQuery(sql);

        if(rs.next()) {
            return rs.getString(1);
        }

        return null;
    }

    public void registerPerson(String login, String pass, String nick) throws SQLException {
        String sql = String.format("INSERT INTO clients (login,password,nickname) VALUES ('%s','%s','%s')",login,pass,nick);
        stmt.execute(sql);
    }

    public List<String> LoadBlackList(String name) {
        List<String> blacklist = new ArrayList<>();
        try {
            String sql = String.format("SELECT c1.nickname FROM clients as c1 Inner Join BlackList as b on c1.id=b.id_block_user Inner Join clients as c2 on c2.id=b.id_user WHERE c2.nickname='%s'",name);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                blacklist.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blacklist;
    }

    public boolean addBlackList(String nickThis, String nickBlack) {
        boolean res = false;
        if (!nickThis.equalsIgnoreCase(nickBlack)) {
            try {
                String id_user;
                String id_block_user;
                String sql = String.format("SELECT clients.id FROM clients WHERE clients.nickname='%s'",nickBlack);
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    sql = String.format("SELECT clients.id FROM clients WHERE clients.nickname='%s'", nickThis);
                    rs = stmt.executeQuery(sql);
                    id_user = rs.getString(1);
                    sql = String.format("SELECT clients.id FROM clients WHERE clients.nickname='%s'", nickBlack);
                    rs = stmt.executeQuery(sql);
                    id_block_user = rs.getString(1);
                    sql = String.format("INSERT INTO BlackList (id_user,id_block_user) VALUES ('%s','%s')", id_user, id_block_user);
                    stmt.execute(sql);
                    res = true;
                } else {
                    res = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            res = false;
        }
        return res;
    }

    public void historyMsg(Long time, String msg) {
        try {
            prstmt = connection.prepareStatement("INSERT INTO history (Date,Message) VALUES (?,?)");
            prstmt.setLong(1,time);
            prstmt.setString(2,msg);
            prstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String loadHistoryMsg() {
        String res="";
        try {
            StringBuilder sb = new StringBuilder();
            ResultSet rs = stmt.executeQuery("SELECT * FROM history order by Date desc limit 50;");
            while (rs.next()) {
                sb.insert(0,rs.getString("Message")+"\n");
            }
            res = sb.toString();
            res = res.substring(0,res.length()-1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
