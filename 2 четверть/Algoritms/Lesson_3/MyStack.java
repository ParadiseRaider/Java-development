package Lesson_3;

import java.util.EmptyStackException;

public class MyStack<T> {
    private T[] list;
    private int size = 0;
    private int capacity = 10;

    public MyStack(int capacity) {
        this.capacity = capacity;
        if (capacity<=0) {
            throw  new IllegalArgumentException("capacity "+capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyStack() {
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

    public void push(T item) {
        if (isFull()) {
            checkCapacity();
        }
        list[size]=item;
        size++;
    }


    public T pop() {
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
