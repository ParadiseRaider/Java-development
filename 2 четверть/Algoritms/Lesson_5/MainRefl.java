package Lesson_5;

public class MainRefl {
    static int[] price = {894, 260, 392, 281, 27};
    static int[] weights = {8, 5, 4, 1, 21};
    static int W = 20;

    public static int knapsack(int i, int W) {
        if (i < 0) {
            return 0;
        }
        if (weights[i] > W) {
            return knapsack(i-1, W);
        } else {
            return Math.max(knapsack(i-1, W), knapsack(i-1, W - weights[i]) + price[i]);
        }
    }

    public static void main(String[] args) {
        Power p = new Power();
        int num = 2;
        int n = 10;
        System.out.println("Возведение в степень");
        System.out.println(num+" в стемени "+n+" = "+p.powerOn(num,n));
        System.out.println("Наибольшая стоимость которую можно положить в рюкзак размером "+W+" кг");
        System.out.println(knapsack(price.length - 1, W));
    }
}
