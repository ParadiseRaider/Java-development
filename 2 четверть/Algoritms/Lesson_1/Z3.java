package Lesson_1;

import java.io.PrintWriter;
import java.util.Scanner;

//Неглухой телефон
public class Z3 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int a = in.nextInt();
        out.println(a);

        out.flush();
    }
}
