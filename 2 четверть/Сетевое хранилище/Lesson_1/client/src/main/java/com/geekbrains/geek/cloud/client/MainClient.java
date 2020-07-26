package com.geekbrains.geek.cloud.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost",8189)) {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bOut);
            out.write(15);
            String filename = "winter.jpg";
            int filenameLength = filename.length();
            out.writeInt(filenameLength);
            out.write(filename.getBytes());
            byte[] bytesFromFile = Files.readAllBytes(Paths.get("client_repository/"+filename));
            out.writeInt(bytesFromFile.length);
            out.write(bytesFromFile);
            socket.getOutputStream().write(bOut.toByteArray());
            System.out.println("Данные отправлены");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
