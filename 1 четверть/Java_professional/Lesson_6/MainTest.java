package Lesson_6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class MainTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        int[][] mass1 = {{1,1,1,4},{1,4,1,1},{1,1,4,1}};
        int[][] mass2 = {{},{1,1},{1}};
        return Arrays.asList(new Object[][] {
                {mass1[0],mass2[0]},
                {mass1[1],mass2[1]},
                {mass1[2],mass2[2]},
        });
    }

    private int[] mass;
    private int[] res;

    public MainTest(int[] mass1,int[] mass2) {
        mass = new int[mass1.length];
        res = new int[mass2.length];
        System.arraycopy(mass1,0,mass,0,mass1.length);
        System.arraycopy(mass2,0,res,0,mass2.length);
    }

    MassTest mt;

    @Before
    public void Init() {
        mt = new MassTest();
    }

    @Test
    public void test1() {
        Assert.assertTrue(mt.massTest2(mass));
    }

    @Test
    public void test2() {
        Assert.assertArrayEquals(res,mt.massTest1(mass));
    }
}
