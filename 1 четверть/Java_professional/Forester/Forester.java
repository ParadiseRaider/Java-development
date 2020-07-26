package Java_professional.Forester;

import java.util.HashMap;
import java.util.Map;

public class Forester {
    public static void main(String[] args) {
        int[] forest = {1,2,3,1,2,3,2,2,1,3,2,4,3,1,2,3,2,4,1,2,1,2,3,1,1,2,3,4,5,5};
        Map<Integer, Integer> forester = new HashMap<>();
        for (int i = 0; i < forest.length; i++) {
            Integer count = forester.getOrDefault(forest[i],0);
            forester.put(forest[i], count + 1);
        }
        System.out.println(forester);
    }
}
