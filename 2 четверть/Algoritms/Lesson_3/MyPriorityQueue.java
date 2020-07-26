package Lesson_3;

import java.util.Comparator;
import java.util.EmptyStackException;

public class MyPriorityQueue<T extends Comparable<T>> {
    private T[] list;
    private int size = 0;
    private int capacity = 10;
    private Comparator comparator = Comparator.naturalOrder();

    public MyPriorityQueue(int capacity, Comparator comparator) {
        this.capacity = capacity;
        if (capacity<=0) {
            throw  new IllegalArgumentException("capacity "+capacity);
        }
        list = (T[]) new Comparable[capacity];
        this.comparator = comparator;
    }

    public MyPriorityQueue(int capacity) {
        this.capacity = capacity;
        if (capacity<=0) {
            throw  new IllegalArgumentException("capacity "+capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyPriorityQueue() {
        list = (T[]) new Comparable[capacity];
    }

    public void checkCapacity() {
        if (size>=capacity) {
            capacity+=10;
            T[] copylist = (T[]) new Comparable[capacity];
            System.arraycopy(list,0,copylist,0,size);
            list = copylist;
        }
    }

    public void insert(T item) {
        if (isFull()) {
            checkCapacity();
        }
        list[size]=item;
        size++;
        int i = size-1;
        T key = list[i];
        while (i>0 && comparator.compare(list[i],list[i-1])>0) {
            list[i] = list[i-1];
            i--;
        }
        list[i] = key;
    }


    public T remove() {
        T temp = peek();
        size--;
        list[size] = null;
        return temp;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[size-1];
    }

    public boolean isFull() {
        return size==list.length;
    }

    public boolean isEmpty() {
        return size==0;
    }
}
