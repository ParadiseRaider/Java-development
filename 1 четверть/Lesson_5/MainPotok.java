package Java_core.Lesson_5;

public class MainPotok {
    static final int SIZE = 10000000;

    public static void main(String[] args) {
        int potok = 4; //Количество потоков
        if (potok>128) potok=128; //Максимальное число потоков выбрал, чтоб не было больше 128
        Thread[] threads = new Thread[potok];
        float arr[] = new float[SIZE];

        //Первая задача без потоков
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время без потоков: "+(System.currentTimeMillis() - a));

        //Вторая задача с потоками
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        a = System.currentTimeMillis();
        for (int i = 0; i < potok; i++) {
            int p = i;
            int pot = potok;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int s1,s2;
                    s1 = p * SIZE / pot;
                    s2 = (p+1)*SIZE / pot-s1;
                    float[] arr2 = new float[s2];
                    System.arraycopy(arr, s1, arr2, 0, s2);
                    for (int j = 0; j < s2; j++) {
                        arr2[j] = (float)(arr2[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                    }
                    System.arraycopy(arr2, 0, arr, s1, s2);
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < potok; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Время с "+potok+" потоками: "+(System.currentTimeMillis() - a));
    }
}
