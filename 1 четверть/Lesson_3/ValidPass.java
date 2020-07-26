package Java_core.Lesson_3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPass {
    public static void main(String[] args) {
        System.out.print("Введите пароль: ");
        Scanner scanner = new Scanner(System.in);
        String pass;
        pass = scanner.next();
        Pattern p = Pattern.compile("^(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[0-9])\\S{8,}$");
        Matcher m = p.matcher(pass);
        System.out.println(m.matches());
    }
}
