package Server;


import java.sql.SQLException;
import java.util.List;

public interface AuthService {
    public String getNickByLoginAndPass(String login, String pass) throws SQLException;
    public String getNickByLogin(String login) throws SQLException;
    public void registerPerson(String login, String pass, String nick) throws SQLException;
    public List<String> LoadBlackList(String name);
    public boolean addBlackList(String nickThis, String nickBlack);
    public void historyMsg(Long time, String msg);
    public String loadHistoryMsg();
    public void disconnect();
}
