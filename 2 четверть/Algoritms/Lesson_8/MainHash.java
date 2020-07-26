package Lesson_8;

public class MainHash {
    public static void main(String[] args) {
        ChainingHashMap<Integer, String> chm = new ChainingHashMap<>(10);
        LinearProbingHashMap<Integer, String> lphm = new LinearProbingHashMap<>(100);

        chm.put(1,"One");
        chm.put(2,"Two-two");
        chm.put(3,"Bread");
        chm.put(4,"Blood");
        chm.put(5,"Five");
        chm.put(11,"Zim");

        lphm.put(1,"1");
        lphm.put(2,"2");
        lphm.put(3,"3");
        lphm.put(4,"4");
        lphm.put(5,"5");
        lphm.put(6,"6");
        lphm.put(7,"7");

        chm.remove(1);
        System.out.println("ChainingHashMap");
        System.out.print(chm);
        System.out.println("LinearProbingHashMap");
        System.out.println(lphm);
        lphm.remove(3);
        lphm.put(3,"3-3");
        System.out.println(lphm);
    }
}
