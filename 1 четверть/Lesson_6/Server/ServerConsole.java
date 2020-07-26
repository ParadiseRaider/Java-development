package Java_core.Lesson_6.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerConsole {
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private BufferedReader console;

    public ServerConsole() {
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");
            socket = server.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            console = new BufferedReader(new InputStreamReader(System.in));

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            while (true) {
                                String str = in.readUTF();
                                if (str.equalsIgnoreCase("/end")) {
                                    System.out.println(socket + " отключился");
                                    break;
                                }
                                System.out.println("Client: " + str);
                            }
                        } catch (IOException e) {
                            //e.printStackTrace();
                        } finally {
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
                            try {
                                server.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            while (true) {
                                System.out.println("Ввеодите сообщение");
                                String str = console.readLine();
                                System.out.println("Сообщение отправлено");
                                out.writeUTF(str);
                                if (str.equalsIgnoreCase("/end")) break;
                            }
                        } catch (IOException e) {
                            //e.printStackTrace();
                        } finally {
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                server.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                }
            });
            t1.setDaemon(true);
            t1.start();
            t2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
