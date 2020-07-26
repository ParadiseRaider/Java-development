package Lesson_1;

import java.io.PrintWriter;
import java.util.Scanner;

//Сумма
public class Z2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int res=0;
        if (n>0) {
            for (int i = 1; i <= n; i++) {
                res += i;
            }
        } else {
            for (int i = 1; i >=n ; i--) {
                res += i;
            }
        }
        out.println(res);

        out.flush();
    }
}
