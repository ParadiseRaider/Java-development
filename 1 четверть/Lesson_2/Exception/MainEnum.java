package Java_core.Lesson_2.Exception;

public class MainEnum {
    public static void main(String[] args) {
        String[][] mass = new String[4][4];
        for (int i = 0; i < mass.length ; i++) {
            for (int j = 0; j < mass[i].length ; j++) {
                mass[i][j] = Integer.toString(i) + Integer.toString(j);
            }
        }
        mass[1][1] = "1F";
        try {
            test(mass);
        } catch (MyArraySizeException e) {
            System.out.println(e.getMsg());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMsg());
        }
    }

    public static void test(String[][] str) throws MyArraySizeException, MyArrayDataException {
        int res=0;
        if (str.length>4 || str[0].length>4) throw new MyArraySizeException("Размерность массива не может быть больше 4");
        for (int i = 0; i < str.length ; i++) {
            for (int j = 0; j < str[i].length ; j++) {
                try {
                    res+=Integer.valueOf(str[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("В массиве могут быть только числа",i,j);
                }
            }
        }
        System.out.println("Сумма всех элеменов массива = "+res);
    }
}
