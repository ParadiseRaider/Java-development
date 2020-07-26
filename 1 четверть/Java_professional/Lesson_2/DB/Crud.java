package Java_core.Lesson_7.DB;

public class Crud {
    public static void main(String[] args) {
        SqlDb.connect();
        SqlDb.crudDb();
        SqlDb.close();
    }
}
