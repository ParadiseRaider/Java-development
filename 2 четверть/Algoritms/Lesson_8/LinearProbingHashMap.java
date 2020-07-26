package Lesson_8;

public class LinearProbingHashMap<Key, Value> {
    private int capacity;
    private int size;
    private Key[] keys;
    private Value[] values;
    private final Key deleted = (Key) "!DeL!";

    public LinearProbingHashMap(int capacity) {
        this.capacity = capacity;
        keys = (Key[])new Object[capacity];
        values = (Value[]) new Object[capacity];
        size=0;
    }

    public int size() {
        return size;
    }

    public final int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public final int hash2(Key key) {
        return 7 - (key.hashCode() & 0x7fffffff) % 7;
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
        if (size == capacity-1) {
            throw new RuntimeException("Места нет");
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(deleted)) {
                keys[i] = key;
                values[i] = value;
                size++;
                return;
            }
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

    public void remove(Key key) {
        isKeyNotNull(key);
        int i;
        for (i = hash(key); keys[i] != null ; i = (i + 1) % capacity) {
            if (key.equals(keys[i])) {
                keys[i] = (Key) deleted;
                values[i] = (Value) deleted;
                size--;
                return;
            }
        }
    }

    public Value get(Key key) {
        isKeyNotNull(key);
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (key.equals(keys[i])) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i <capacity ; i++) {
            sb.append(keys[i]).append("[").append(values[i]).append("], ");
        }
        return sb.toString();
    }
}
