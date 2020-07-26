package Java_professional.Lesson_3;

public class MainFile {
    public static void main(String[] args) {
        ReadFile rf = new ReadFile();
        System.out.println("---------------");
        System.out.println("Задание номер 1");
        System.out.println("---------------");
        rf.readFile();
        System.out.println("---------------");
        System.out.println("Задание номер 2");
        System.out.println("---------------");
        rf.readMultiFile();
        System.out.println("---------------");
        System.out.println("Задание номер 3");
        System.out.println("---------------");
        rf.readPageFile();
        System.out.println("---------------");
        System.out.println("Задание номер 5");
        System.out.println("---------------");
        rf.endReadText();
    }
}
