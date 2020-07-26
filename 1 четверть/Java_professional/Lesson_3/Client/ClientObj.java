package Java_professional.Lesson_3.Client;

import Java_professional.Lesson_3.Terminator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientObj {
    Socket client;
    ObjectOutputStream out;

    public ClientObj() {
        try {
            client = new Socket("localhost",8189);
            Terminator t1 = new Terminator("Arni","T-800",600);
            out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(t1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
