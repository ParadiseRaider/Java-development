package Java_core.Lesson_2.Exception;

public class MyArraySizeException extends Exception {
    String msg;
    public MyArraySizeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
