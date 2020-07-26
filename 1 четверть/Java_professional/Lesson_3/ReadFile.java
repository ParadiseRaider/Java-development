package Java_professional.Lesson_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ReadFile {

    public void readFile() {
        try (FileInputStream in = new FileInputStream("L3/1.txt")) {
            int x;
            byte[] arr = new byte[50];
            while ((x = in.read(arr)) != -1) {
                System.out.println(new String(arr, 0, x,"windows-1251"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMultiFile() {
        ArrayList<InputStream> ali = new ArrayList<>();
        try {
            ali.add(new FileInputStream("L3/z1.txt"));
            ali.add(new FileInputStream("L3/z2.txt"));
            ali.add(new FileInputStream("L3/z3.txt"));
            ali.add(new FileInputStream("L3/z4.txt"));
            ali.add(new FileInputStream("L3/z5.txt"));
            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
            int x;
            byte[] arr = new byte[50];
            while ((x=in.read(arr))!=-1) {
                System.out.println(new String(arr,0,x,"windows-1251"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPageFile() {
        try (RandomAccessFile raf = new RandomAccessFile(("L3/book.txt"),"r")) {
            int page=1;
            int pageSize=1800;
            if (page<1) {
                System.out.println("Страница не может быть нулевой или отрицательной, установлена 1 страница");
                page=1;
            }
            if (page*pageSize>raf.length()) {
                System.out.println("Размер страницы больше текста или слишком большая страница выбрана, установлена последняя страница");
                page = (int) ((raf.length()-pageSize)/pageSize);
                if (page==0) page=1;
            }
            raf.seek((page-1)*pageSize);
            byte[] arr = new byte[pageSize];
            int x = raf.read(arr);
            System.out.println(new String(arr,0,x));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endReadText() {
        try (RandomAccessFile raf = new RandomAccessFile(("L3/zad5.txt"), "r")) {
            StringBuilder bf = new StringBuilder();
            int x;
            for (long i = raf.length()-1; i >= 0 ; i--) {
                raf.seek(i);
                x = raf.read();
                bf.insert(0,(char)x);
                if ((char)x=='\n' || i==0) {
                    System.out.print(bf.toString());
                    bf = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
