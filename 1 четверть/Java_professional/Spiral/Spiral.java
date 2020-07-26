package Java_professional.Spiral;

public class Spiral {
    void goToSpiral(int[][] mass, int y, int x) {
        int x2=x;
        int y2=y;
        int sum = y*x;
        int corX=0;
        int corY=0;
        int count=0;

        while(y>0) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < ((x<y) ? y:x); j++) {
                    if (i==0 && j<x-corX && count<=sum)
                        mass[i + corY][j + corX] = count++;
                    if (i==1 && j<y-corY && j!=0 && count<=sum)
                        mass[j+corY][x-1] = count++;
                    if (i==2 && j<x-corX && j!=0 && count<=sum)
                        mass[y-1][x-(j+1)] = count++;
                    if (i==3 && j<y-(corY+1) && j!=0 && count<=sum)
                        mass[y-(j+1)][corY] = count++;
                }
            }
            x--;
            y--;
            corX++;
            corY++;
        }
        for (int i = 0; i < y2; i++) {
            for (int j = 0; j < x2; j++) {
                System.out.print(mass[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Spiral spiral = new Spiral();
        int[][] mass = new int[5][5];
        spiral.goToSpiral(mass,mass.length,mass[0].length);
    }
}
