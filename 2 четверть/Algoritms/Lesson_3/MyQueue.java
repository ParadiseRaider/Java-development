package Lesson_3;

import java.util.EmptyStackException;

public class MyQueue<T> {
    private T[] list;
    private int size = 0;
    private int capacity = 10;
    private int begin = 0;
    private int end = 0;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        if (capacity<=0) {
            throw  new IllegalArgumentException("capacity "+capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyQueue() {
        list = (T[]) new Comparable[capacity];
    }


    public void insert(T item) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        list[end]=item;
        size++;
        end = nextIndex(end);
    }


    public T remove() {
        T temp = peekFront();
        size--;
        list[begin] = null;
        begin = nextIndex(begin);
        return temp;
    }

    public T peekFront() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[begin];
    }

    public boolean isFull() {
        return size==list.length;
    }

    public boolean isEmpty() {
        return size==0;
    }

    private int nextIndex(int index) {
        return (index+1) % list.length;
    }
}
