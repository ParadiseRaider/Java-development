package Java_professional.Lesson_3.Server;

import Java_professional.Lesson_3.Terminator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServObj {
    ServerSocket serv;
    Socket client;
    ObjectInputStream in;

    public ServObj() {
        try {
            serv = new ServerSocket(8189);
            System.out.println("Сервер стартанул");
            client = serv.accept();
            System.out.println("Клиент успешно подключен");
            in = new ObjectInputStream(client.getInputStream());
            try {
                Terminator t2 = (Terminator) in.readObject();
                t2.Info();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
