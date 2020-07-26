package Java_professional.Lesson_4;

public class MFUPrinter {
    int paper=3;

    public synchronized void printer(int id, int pages) {
        while (paper-pages<=0) {
            System.out.println("Закончилась бумага");
            putPaper();
        }
        getPaper(pages,id);
        System.out.println("Пользователь " + id + " напечатал " + pages + " страниц");
    }

    public synchronized void getPaper(int pages,int id) {
        int time = pages*500;
        while (paper-pages<0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Пользователь "+id+" начал печать страниц");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        paper-=pages;
    }

    public synchronized void putPaper() {
        System.out.println("Васек пошел за бумагой");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Васек положил бумагу в лоток");
        paper+=20;
        notifyAll();
    }
}
