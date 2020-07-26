package Java_professional.Lesson_1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Задание №1
        String[] zad1 = {"Hello","man","how","are","you"};
        Mass mass = new Mass();
        mass.changePlace(zad1,0,1);
        System.out.println(Arrays.toString(zad1));

        //Задание №2
        Integer[] num = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> nums;
        nums = mass.createArrayList(num);
        System.out.println(nums);

        //Задание №3
        Box<Apple> ba1 = new Box<>();
        Box<Apple> ba2 = new Box<>();
        Box<Orange> bo1 = new Box<>();
        Box<Orange> bo2 = new Box<>();

        //Заполняем ящики яблоками и апельсинами
        ba1.add(Apple.class,10);
        ba2.add(Apple.class,5);
        bo1.add(Orange.class,10);
        bo2.add(Orange.class,6);

        System.out.println("Вес 1 ящика яблок: "+ba1.getWeight());
        System.out.println("Вес 2 ящика яблок: "+ba2.getWeight());
        System.out.println("Вес 1 ящика апельсин: "+bo1.getWeight());
        System.out.println("Вес 2 ящика апельсин: "+bo2.getWeight());
        ba1.fillBox(ba2); //Перекладываем из 1 ящика во 2 ящик яблоки
        System.out.println("Вес 2 ящика после добавления яблок из 1 ящика: "+ba2.getWeight());
        System.out.println("Равны ли по весу 2 ящик яблок и 1 ящик апельсин: "+ba2.compare(bo1));

        //Задание №4
        List<SomeClass> ls = new ArrayList<>();
        Map<String,ArrayList<Integer>> hm = new HashMap<>();
        ls.add(new SomeClass("Test1"));
        ls.add(new SomeClass("Test1"));
        ls.add(new SomeClass("Test1"));
        ls.add(new SomeClass("Test2"));
        ls.add(new SomeClass("Test2"));
        ls.add(new SomeClass("Test3"));
        ls.add(new SomeClass("Test3"));
        ls.add(new SomeClass("Test4"));
        ls.forEach(ind->{
            ArrayList<Integer> al = hm.getOrDefault(ind.getName(), new ArrayList<>());
            al.add(ind.getId());
            hm.put(ind.getName(),al);
        });
        System.out.println(hm);
    }
}
