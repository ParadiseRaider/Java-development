package Java_professional.Lesson_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mass {
    <T> void changePlace(T[] mas, int index1, int index2) {
        T c = mas[index1];
        mas[index1] = mas[index2];
        mas[index2] = c;
    }

    <T> List<T> createArrayList(T[] mas) {
        List<T> arr = Arrays.asList(mas);
        return arr;
    }
}
