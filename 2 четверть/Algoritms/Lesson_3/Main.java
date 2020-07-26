package Lesson_3;

public class Main {
    public static void main(String[] args) {
        MyStack<Integer> ms = new MyStack<>();
        MyQueue<Integer> mq = new MyQueue<>();
        MyDeque<Integer> md = new MyDeque<>();
        MyPriorityQueue<Integer> mpq = new MyPriorityQueue<>();

        System.out.println("Stack test");
        ms.push(1);
        ms.push(2);
        ms.push(3);
        ms.push(4);
        ms.push(5);
        for (int i = 0; i < 5; i++) {
            System.out.println(ms.pop());
        }

        System.out.println("Queue test");
        mq.insert(1);
        mq.insert(2);
        mq.insert(3);
        mq.insert(4);
        mq.insert(5);
        for (int i = 0; i < 5; i++) {
            System.out.println(mq.remove());
        }

        System.out.println("Deque test");
        md.insertLeft(1);
        md.insertLeft(2);
        md.insertRight(3);
        md.insertRight(4);
        System.out.println(md.removeLeft());
        System.out.println(md.removeRight());
        System.out.println(md.removeRight());
        System.out.println(md.removeRight());

        System.out.println("Priority Queue test");
        mpq.insert(7);
        mpq.insert(2);
        mpq.insert(8);
        mpq.insert(9);
        mpq.insert(3);
        for (int i = 0; i < 5; i++) {
            System.out.println(mpq.remove());
        }
    }
}
