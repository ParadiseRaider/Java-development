package Lesson_6;

public class MassTest {

    public int[] massTest1(int[] mass) {
        int[] res;
        int count=0;
        int start=0;
        if (mass.length==0) throw new MassException("Массив не может быть пустым");
        for (int i = 0; i < mass.length; i++) {
            if (mass[i]==4) {
                count=0;
                start=i;
            }
            count++;
        }
        if (start==0) throw new MassException("Массив не содержит 4");
        res = new int[count-1];
        System.arraycopy(mass,start+1,res,0,count-1);
        return res;
    }

    public boolean massTest2(int[] mass) {
        boolean res=true;
        if (mass.length==0) res=false;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i]!=1 && mass[i]!=4) res=false;
        }
        return res;
    }
}
