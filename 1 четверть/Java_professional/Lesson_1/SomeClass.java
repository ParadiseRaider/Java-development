package Java_professional.Lesson_1;

public class SomeClass {
    static int count=1;
    Integer id=count;
    String name;

    public SomeClass(String name) {
        this.name = name;
        count++;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
