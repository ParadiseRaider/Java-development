package Java_professional.Lesson_5;


import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    public static CyclicBarrier cb = new CyclicBarrier(MainClass.CARS_COUNT);
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            MainClass.cdl.countDown();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        MainClass.cdl2.countDown();
        MainClass.vector.add(name);
        if (MainClass.ab.get()) {
            MainClass.ab.getAndSet(false);
            System.out.println("Победитель: "+name);
        }
    }
}
