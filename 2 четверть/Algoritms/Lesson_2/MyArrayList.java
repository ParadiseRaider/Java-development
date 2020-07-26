package Lesson_2;

import java.util.Comparator;

public class MyArrayList<T extends Comparable> {
    private T[] list;
    private T[] copylist;
    private int size = 0;
    private int capacity = 10;

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        if (capacity<=0) {
            throw  new IllegalArgumentException("capacity "+capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyArrayList() {
        list = (T[]) new Comparable[capacity];
    }

    public void checkCapacity() {
        if (size>=capacity) {
            copylist = (T[]) new Comparable[capacity];
            System.arraycopy(list,0,copylist,0,size);
            capacity+=10;
            list = (T[]) new Comparable[capacity];
            System.arraycopy(copylist,0,list,0,size);
        }
    }

    public void add(T item) {
        list[size] = item;
        size++;
        checkCapacity();
    }

    public void add(int index, T item) {
        for (int i = size; i > index ; i--) {
            list[i] = list[i-1];
        }
        list[index] = item;
        size++;
        checkCapacity();
    }

    public void remove(int index) {
        for (int i = index; i < size; i++) {
            list[i] = list[i+1];
        }
        size--;
        list[size] = null;
    }

    public boolean remove(T item) {
        int index = indexOf(item);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    public final int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) return i;
        }
        return -1;
    }

    public T get(int index) {
        return list[index];
    }

    public void set(int index, T item) {
        if (index<0) throw new IllegalArgumentException("Error type index");
        list[index] = item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < size - 1; i++) {
            sb.append(list[i] + ", ");
        }
        sb.append(list[size - 1] + " ]");
        return sb.toString();
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public void selectionSort() {
        for (int i = 0; i < size - 1; i++) {
            int imin = i;
            for (int j = i + 1; j < size; j++) {
                if( list[j].compareTo(list[imin])<0){
                    imin = j;
                }
            }
            swap(i, imin);
        }
    }

    public void insertionSort() {
        T key;
        for (int i = 1; i < size; i++) {
            int j = i;
            key  = list[i];
            while(j>0 && key.compareTo(list[j-1])<0){
                list[j] = list[j-1];
                j--;
            }
            list[j] = key;
        }
    }

    public void bubbleSort(Comparator<T> comparator){
        boolean isSwap;
        for (int i = size-1; i > 0 ; i--) {
            isSwap = false;
            for (int j = 0; j < i ; j++) {
                if( comparator.compare(list[j+1],list[j])<0 ){
                    swap(j+1, j);
                    isSwap = true;
                }
            }
            if(!isSwap){
                break;
            }
        }
    }

    public void bubbleSort(){
        bubbleSort(Comparator.naturalOrder());
    }

    public boolean binaryFind(T item) {
        int lo = 0;
        int hi = size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (item.compareTo(get(mid)) < 0) {
                hi = mid - 1;
            } else if (item.compareTo(get(mid)) > 0) {
                lo = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
