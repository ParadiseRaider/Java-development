package Lesson_6;



public class MainTree {
    public static void main(String[] args) {
//        map.put(5,"-");
//        map.put(3,"-");
//        map.put(2,"-");
//        map.put(10,"-");
//        map.put(8,"-");
//        map.put(6,"-");
//        map.put(20,"-");
//        map.put(30,"-");
//        map.put(40,"-");
//        map.put(11,"-");
//        map.put(12,"-");
//        map.put(13,"-");

        for (int i = 0; i < 20; i++) {
            MyTreeMap<Integer, String> map = new MyTreeMap<>();
            int count = 0;
            while (map.getHeight() < 6) {
                int f = (int) (Math.random() * (100 + 100) - 100);
                map.put(f, Integer.toString(count));
                count++;
            }
            System.out.println(map.isBalanced());
            System.out.println(map);
        }

    }
}
