package Java_core.Lesson_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainCol {
    public static void main(String[] args) {
        ArrayList<String> al1 = new ArrayList<>();
        al1.add("Hello");
        al1.add("World");
        al1.add("Place");
        al1.add("Table");
        al1.add("Hello");
        al1.add("Winter");
        al1.add("Table");
        al1.add("Peace");
        al1.add("Beep");
        al1.add("World");

        ListInit(al1);
    }

    static void ListInit(ArrayList<String> al) {
        HashMap<String,Integer> hm = new HashMap<>();
        Iterator<String> iter = al.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            Integer count = hm.get(str);
            hm.put(str, count==null ? 1 : count+1);
        }
        System.out.println(hm);
    }
}
