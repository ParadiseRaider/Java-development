package Java_core.Lesson_3;

public class Telephone {
    public static void main(String[] args) {
        DataTel dt = new DataTel();
        dt.add("Ivanov","8-919-333-12-13","8-906-512-12-21");
        dt.add("Petrov","8-929-333-12-13");
        dt.add("Sidorov","8-919-343-12-13","8-916-512-12-21","8-888-121-33-44");
        dt.add("Petrov","8-929-333-12-66");
        dt.get("Petrov");
    }
}
