package Lesson_2;

import java.util.Random;

public class MainArray {
    static Random rand = new Random();
    public static void main(String[] args) {
        long timeSort;
        int max = 100000;
        MyArrayList<Integer> mal = new MyArrayList<>(max);

        for (int i = 0; i < max; i++) {
            mal.add(rand.nextInt(max));
        }

        timeSort = System.currentTimeMillis();
        mal.selectionSort();
        System.out.println("Selection sort time = "+(System.currentTimeMillis()-timeSort)+" ms");

        setRnd(mal,max);
        timeSort = System.currentTimeMillis();
        mal.insertionSort();
        System.out.println("Insertion sort time = "+(System.currentTimeMillis()-timeSort)+" ms");

        setRnd(mal,max);
        timeSort = System.currentTimeMillis();
        mal.bubbleSort();
        System.out.println("Bubble sort time = "+(System.currentTimeMillis()-timeSort)+" ms");
    }

    public static void setRnd(MyArrayList al, int max) {
        for (int i = 0; i < max; i++) {
            al.set(i,rand.nextInt(max));
        }
    }
}
