package Java_professional.Lesson_3;

import java.io.Serializable;

public class Terminator implements Serializable {
    String name;
    String model;
    int weight;

    public Terminator(String name, String model, int weight) {
        this.name = name;
        this.model = model;
        this.weight = weight;
    }

    public void Info() {
        System.out.println("Terminator "+model+" "+name+" весит = "+weight+" кг");
    }
}
