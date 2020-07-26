package Java_professional.Lesson_4;

public class MFUScanner {
    public synchronized void scaner(int id) {
        System.out.println("Пользователь "+id+" начал сканировать страницу");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Пользователь "+id+" отсканировал страницу");
    }
}
