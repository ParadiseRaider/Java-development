package Java_professional.Lesson_7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class startClass {
    public static void start(Class t) {
        TestRefl tr = new TestRefl();
        Method[] methods = t.getDeclaredMethods();
        List<Method> al1 = new ArrayList<>();
        List<Method> al2 = new ArrayList<>();
        boolean fl=true;

        for (Method m:methods) {
            if (m.isAnnotationPresent(Test.class))
                al1.add(m);
            if (m.isAnnotationPresent(BeforeSuite.class))
                al2.add(m);
            if (m.isAnnotationPresent(AfterSuite.class))
                al2.add(m);
        }

        if (al2.size()>0) {
            if (!al2.get(0).isAnnotationPresent(BeforeSuite.class)) {
                Method c = al2.get(0);
                al2.set(0, al2.get(1));
                al2.set(1, c);
            }
        }

        if (al2.size()>2) throw new TestException("Не может быть больше одного, BeforeSuite и AfterSuite");

        if (al2.size()>0) {
            try {
                al2.get(0).invoke(tr);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        while(fl) {
            fl=false;
            for (int i = 0; i < al1.size()-1; i++) {
                if (al1.get(i).getAnnotation(Test.class).priority()>al1.get(i+1).getAnnotation(Test.class).priority()) {
                    Method c = al1.get(i);
                    al1.set(i,al1.get(i+1));
                    al1.set(i+1,c);
                    fl=true;
                }
            }
        }

        for (int i=0;i<al1.size();i++) {
            try {
                al1.get(i).invoke(tr);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (al2.size()>=2) {
            try {
                al2.get(1).invoke(tr);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
