package Java_professional.Lesson_4;

public class MainPotok {

    public static void main(String[] args) {
        PrintPotok p1 = new PrintPotok("A",true);
        PrintPotok p2 = new PrintPotok("B",false);
        PrintPotok p3 = new PrintPotok("C",false);
        p1.nextPotok(p2);
        p2.nextPotok(p3);
        p3.nextPotok(p1);
        p1.start();
        p2.start();
        p3.start();
    }
}
