package Java_core.Lesson_7.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MainServ serv;
    private String nick;
    private String login;

    public ClientHandler(MainServ serv, Socket socket) {
        try {
            this.serv = serv;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/err")) {
                                sendMsg("Ошибка ввода данных.\nВсе данные должны быть без пробелов\nПароль должен начинаться с латинской буквы");
                            }
                            if (str.startsWith("/regin")) {
                                String[] tokens = str.split(" ");
                                String currentNick = AuthService.getNickByLogin(tokens[1]);
                                if (currentNick==null) {
                                    AuthService.registerPerson(tokens[1],tokens[2],tokens[3]);
                                    nick = tokens[3];
                                    login = tokens[1];
                                    serv.clientsOn(ClientHandler.this);
                                    sendMsg("/authok");
                                    break;
                                } else {
                                    sendMsg("Такой пользователь уже есть");
                                }
                            }
                            if (str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String currentNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (currentNick != null) {
                                    if (!serv.isConnect(tokens[1])) {
                                        nick = currentNick;
                                        login = tokens[1];
                                        serv.clientsOn(ClientHandler.this);
                                        sendMsg("/authok");
                                        break;
                                    } else {
                                        sendMsg("данный пользователь уже есть в сети");
                                    }
                                } else {
                                    sendMsg("неверный логин/пароль");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/w")) {
                                String[] tokens = str.split(" ");
                                serv.personalMsg(nick,tokens[1],str);
                            } else {
                                serv.broadcastMsg(nick+": "+str);
                            }
                            if (str.equalsIgnoreCase("/end")) {
                                sendMsg("/clientClose");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(socket+" отключился");
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        serv.clientDisconnet(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogin() {
        return login;
    }

    public String getNick() {
        return nick;
    }
}
