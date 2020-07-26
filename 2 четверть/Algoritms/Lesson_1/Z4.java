package Lesson_1;

import java.io.PrintWriter;
import java.util.Scanner;

//Больше-меньше
public class Z4 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int a = in.nextInt();
        int b = in.nextInt();
        if (a>b) out.println(">");
        if (a<b) out.println("<");
        if (a==b) out.println("=");

        out.flush();
    }
}
