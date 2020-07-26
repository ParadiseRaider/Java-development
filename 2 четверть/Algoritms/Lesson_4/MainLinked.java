package Lesson_4;


import java.util.LinkedList;
import java.util.ListIterator;

public class MainLinked {
    public static void main(String[] args) {
        MyLinkedList<String> mll = new MyLinkedList<>();
        MyLinkedQueue<String> mlq = new MyLinkedQueue<>();
        MyLinkedStack<String> mls = new MyLinkedStack<>();

        //Линкедлист с итератором
        mll.addFirst("Ira");
        mll.addFirst("Artem");
        mll.addFirst("Igor");
        mll.removeLast();

        ListIterator<String> iter = mll.iterator();
        while (iter.hasNext()) {
            if (iter.next()=="Artem") {
                iter.remove();
                iter.add("Nina");
                iter.add("Zina");
                iter.add("Vlad");
                iter.add("Kolya");
            }
        }
        while (iter.hasPrevious()) {
            if (iter.previous()=="Igor") {
                iter.remove();
                iter.add("Kano");
                iter.add("Halo");
                iter.set("Hero");
            }
        }

        for (String s: mll) {
            System.out.print(s+" ");
        }
        System.out.println();

        //Очередь на линкедлист с итератором
        mlq.insert("1");
        mlq.insert("2");
        mlq.insert("3");
        mlq.insert("4");
        mlq.insert("5");
        for (int i=mlq.size();i>0;i--) {
            System.out.print(mlq.remove()+" ");
        }
        System.out.println();

        //Стек на линкедлист с итератором
        mls.push("1");
        mls.push("2");
        mls.push("3");
        mls.push("4");
        mls.push("5");
        for (int i=mls.size();i>0;i--) {
            System.out.print(mls.pop()+" ");
        }
    }
}
