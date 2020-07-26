package Java_professional.Lesson_7;

public class TestRefl {

    @Test(priority = 3)
    public void test1() {
        System.out.println("Это тест 1");
    }

    @Test(priority = 1)
    public void test2() {
        System.out.println("Это тест 2");
    }

    @Test(priority = 2)
    public void test3() {
        System.out.println("Это тест 3");
    }

    @BeforeSuite
    public void pr() {
        System.out.println("Это начало тестов");
    }

    @AfterSuite
    public void end() {
        System.out.println("Это конец тестов");
    }
}
