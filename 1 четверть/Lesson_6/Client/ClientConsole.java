package Java_core.Lesson_6.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientConsole {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private BufferedReader console;

    public ClientConsole() {
        try {
            socket = new Socket("localhost",8189);
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
                                    System.out.println("Сервер отключился");
                                    break;
                                }
                                System.out.println("Server: " + str);
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
                        }
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            while (true) {
                                System.out.println("Введите сообщение");
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
                        }
                }
            });
            t2.setDaemon(true);
            t1.start();
            t2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
