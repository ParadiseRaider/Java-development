package Lesson_4;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private class Node<T> {
        T value;
        Node previous;
        Node next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Node previous, T value, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    private class Iter implements ListIterator<T> {
        Node current = new Node(null, first);
        int index = 0;
        boolean forward = false;

        @Override
        public boolean hasNext() {
            return current.next!=null;
        }

        @Override
        public T next() {
            current = current.next;
            forward = true;
            index++;
            return (T) current.value;
        }

        @Override
        public boolean hasPrevious() {
            return current.previous!=null;
        }

        @Override
        public T previous() {
            current = current.previous;
            forward = false;
            index--;
            return (T) current.value;
        }

        @Override
        public int nextIndex() {
            return index+1;
        }

        @Override
        public int previousIndex() {
            return index-1;
        }

        @Override
        public void remove() {
            delete(index);
            if (forward)
                current = current.previous;
            else
                current = current.next;
            index--;
        }

        @Override
        public void set(T t) {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            current.value = t;
        }

        @Override
        public void add(T t) {
            if (forward) {
                insert(index, t);
                current = current.next;
                index++;
            } else {
                index--;
                if (index<0) index=0;
                insert(index,t);
                current = current.previous;
            }
        }
    }

    @Override
    public ListIterator<T> iterator() {
        return new Iter();
    }

    private Node first;
    private Node last;
    private int size = 0;

    public void addFirst(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.previous = newNode;
            newNode.next = first;
        }
        first = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.previous = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node temp = first;
        first = first.next;
        if (first==null) {
            last = null;
        } else {
            first.previous = null;
        }
        size--;
        return (T) temp.value;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node temp = last;
        last = last.previous;
        if (last==null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return (T) temp.value;
    }

    public boolean remove(T item) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node current = first;
        if (current.value.equals(item)) {
            removeFirst();
            return true;
        }
        while (current!=null && !current.value.equals(item)) {
            current=current.next;
        }
        if (current==null) {
            return false;
        }
        if (current == last) {
            removeLast();
            return true;
        }
        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
        return true;
    }

    public void delete(int index) {
        int i=1;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node current = first;
        if (index==i) {
            removeFirst();
            return;
        }
        while (i!=index) {
            current=current.next;
            i++;
        }
        if (current==null) {
            return;
        }
        if (index == size) {
            removeLast();
            return;
        }
        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
    }

    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index==0) {
            addFirst(item);
            return;
        }
        if (index==size) {
            addLast(item);
            return;
        }
        Node current = first;
        int i=0;
        while (i<index-1) {
            current=current.next;
            i++;
        }
        Node newNode = new Node(item);
        newNode.next = current.next;
        newNode.previous = current;
        current.next = newNode;
        newNode.next.previous = newNode;
        size++;
    }

    public int index(T value) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node current = first;
        for (int i = 0; i < size; i++) {
            sb.append(current.value);
            if (current.next!=null) sb.append(", ");
            current = current.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T getLast() {
        return (T) last.value;
    }

    public T getFirst() {
        return (T) first.value;
    }
}
