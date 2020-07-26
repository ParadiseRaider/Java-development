package Java_core.Lesson_2;

public class MainDayOfWeek {
    public static void main(String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.Monday));
    }

    public static String getWorkingHours(DayOfWeek dw) {
        int tmp = 5-dw.ordinal();
        String res;
        if (tmp>0) {
            res = "До конца недели осталось "+tmp*8+" часов";
        }
        else {
            res = "Да сегодня же выходной, можно спокойно отдыхать и готовится к понедельнику";
        }
        return res;
    }
}