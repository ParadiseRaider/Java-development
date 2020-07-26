package Java_professional.Lesson_4;

public class PrintPotok extends Thread {
    private String letter;
    private PrintPotok pp;
    private boolean enable;

    public synchronized static void printed(String sumbol, PrintPotok prpt) {
        while(!prpt.enable) {
            try {
                PrintPotok.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(sumbol);
        prpt.enable=false;
        prpt.pp.enable=true;
        PrintPotok.class.notifyAll();
    }

    public PrintPotok(String letter, boolean enable) {
        this.letter = letter;
        this.enable = enable;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            printed(letter,PrintPotok.this);
        }
    }

    public void nextPotok(PrintPotok pp) {
        this.pp = pp;
    }
}
