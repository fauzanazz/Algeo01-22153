public class Determinan {
    public static double DeterminanNxN(double[][] matriks){
        int m = matriks.length;
        double det = 0;
        if (m != matriks[0].length){
            System.err.println("Matriks yang diinput bukan merupakan matriks persegi!");
            return 0;
        }
        if (m != 2 && m != 3){
            System.err.println("Matriks yang diinput bukan merupakan matriks 2x2 atau 3x3!");
            return 0;
        }
        if (m==2){
            det = matriks[0][0]*matriks[1][1] - matriks[0][1]*matriks[1][0];
        } else {

            for (int j = 0; j<m; j++){
                // Miring kanan
                int col = j;
                double temp = 1;
                for(int i = 0; i<m; i++){
                    temp = temp * matriks[i][col];
                    col++;
                    if (col>=m){
                        col = col - m;
                    }
                }
                det = det + temp;

                // Miring kiri
                col = j;
                temp = 1;
                for(int i = 0; i<m; i++){
                    temp = temp * matriks[i][col];
                    col--;
                    if (col<0){
                        col = col + m;
                    }
                }
                det = det - temp;
            }
        }
        return det;
    }
}
