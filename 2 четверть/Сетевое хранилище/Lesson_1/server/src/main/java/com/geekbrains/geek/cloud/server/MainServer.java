package com.geekbrains.geek.cloud.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class MainServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен. Ожидаем подключение клиента");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            StringBuilder filename = new StringBuilder();
            int b;
            while ((b=in.read())!=-1) {
                if (b == 15) {
                    while (in.available() < 4) ;
                    byte[] bytes = new byte[4];
                    in.read(bytes);
                    int go = ByteBuffer.wrap(bytes).getInt();
                    for (int i = 0; i < go; i++) {
                        filename.append((char) in.read());
                    }
                    while (in.available() < 4) ;
                    in.read(bytes);
                    int n = ByteBuffer.wrap(bytes).getInt();
                    byte[] fileWrite = new byte[n];
                    for (int i = 0; i < n; i++) {
                        fileWrite[i] = (byte) in.read();
                    }
                    FileOutputStream writeFile = new FileOutputStream("server_repository/" + filename);
                    writeFile.write(fileWrite, 0, fileWrite.length);
                    System.out.println("Данные успешно получены");
                    filename.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
