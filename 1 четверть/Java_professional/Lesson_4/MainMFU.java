package Java_professional.Lesson_4;

import java.util.Random;

public class MainMFU {

    public static void main(String[] args) {
        MFUPrinter mfuPrinter = new MFUPrinter();
        MFUScanner mfuScanner = new MFUScanner();

        for (int i = 0; i < 10; i++) {
            int user_p=i;
            int user_s=i+10;

            new Thread(() -> {
                int id = user_s;
                Random r2 = new Random();
                try {
                    Thread.sleep(r2.nextInt(7000)+1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mfuScanner.scaner(id);
            }).start();

            new Thread(() -> {
                int id = user_p;
                Random r1 = new Random();
                Random pages = new Random();
                try {
                    Thread.sleep(r1.nextInt(4500)+500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mfuPrinter.printer(id,pages.nextInt(10)+1);
            }).start();
        }
    }
}
