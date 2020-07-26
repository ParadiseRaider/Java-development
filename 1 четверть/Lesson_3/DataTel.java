package Java_core.Lesson_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DataTel {
    HashMap<String, ArrayList<String>> hm = new HashMap<>();

    public void add(String name, String... tel) {
        ArrayList<String> al;
        al = hm.containsKey(name) ? hm.get(name) : new ArrayList<>();
        al.addAll(Arrays.asList(tel));
        hm.put(name,al);
    }

    public void get(String name) {
        System.out.println(hm.get(name));
    }
}
