package Lesson_8;

import java.util.LinkedList;

public class ChainingHashMap<Key, Value> {
    private int capacity;
    private int size;
    private LinkedList<Node>[] st;

    public ChainingHashMap(int capacity) {
        this.capacity = capacity;
        st = new LinkedList[capacity];
        for (int i = 0; i < st.length; i++) {
            st[i] = new LinkedList<>();
        }
        size=0;
    }

    private class Node {
        Key key;
        Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public final int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private void isKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key == null");
        }
    }

    public void put(Key key, Value value) {
        isKeyNotNull(key);
        int i = hash(key);
        for (Node node: st[i]) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        st[i].addLast(new Node(key,value));
        size++;
    }

    public void remove(Key key) {
        isKeyNotNull(key);
        int i = hash(key);
        for (Node node: st[i]) {
            if (key.equals(node.key)) {
                st[i].removeIf(x -> x.key.equals(key));
                size--;
                return;
            }
        }
    }

    public Value get(Key key) {
        isKeyNotNull(key);
        int i = hash(key);
        for (Node node: st[i]) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            for (Node node : st[i]) {
                sb.append(node.key).append("[").append(node.value).append("], ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
