package Lesson_5;

public class Power {
    public double powerOn(double n1, double n2) {
        if (n2==0) {
            return 1;
        }
        if (n2>0) {
            return powerOn(n1, n2 - 1) * n1;
        } else {
            return powerOn(n1, n2 + 1) * 1 / n1;
        }
     }
}
