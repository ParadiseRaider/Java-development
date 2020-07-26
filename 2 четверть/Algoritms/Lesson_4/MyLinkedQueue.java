package Lesson_4;

import java.util.Iterator;

public class MyLinkedQueue<T> {
    private MyLinkedList<T> queue = new MyLinkedList<>();

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void insert(T value) {
        queue.addLast(value);
    }

    public T remove() {
        return queue.removeFirst();
    }

    public T peek() {
        return queue.getFirst();
    }
}
