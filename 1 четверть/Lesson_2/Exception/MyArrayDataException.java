package Java_core.Lesson_2.Exception;

public class MyArrayDataException extends Exception {
    String msg;
    int i,j;
    public MyArrayDataException(String msg,int i, int j) {
        super(msg);
        this.msg = msg;
        this.i = i;
        this.j = j;
    }

    public String getMsg() {
        String res = msg+"\nОшибка в "+i+" строке "+j+" столбце";
        return res;
    }
}
