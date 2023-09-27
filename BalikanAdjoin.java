import java.lang.Math;

public class BalikanAdjoin{

    public static String getInverseFromAdjoinOutput(double[][] matriks){
        double[][] s = getInverseFromAdjoin(matriks);
        if (s[0][0] == 0 && s.length == 1){
            return sOut;
        }

        sOut = "";
        for (int i=0; i<s.length; i++){
            for (int j=0; j<s[i].length; j++){
                sOut += s[i][j];
                if (j != s[0].length -1) sOut += " ";
            }
            if (i != s.length -1) sOut += "\n";
        }
        return sOut;
    }

    private static String sOut = "";

    public static double[][] getInverseFromAdjoin(double[][] matriks){
        double det = Determinan_Kofaktor.determinan_kofaktor(matriks);
        if (det == 0) {
            sOut += ("Matriks memiliki balikan jika dan hanya jika nilai determinan matriks tidak sama dengan 0!");
            return new double[1][1]; 
        }
        if (matriks.length != matriks[0].length) {
            sOut += ("Matriks yang diinput bukan merupakan matriks persegi!");
            return new double[1][1]; 
        }
        matriks = getKofaktor(matriks);
        matriks = getTranspose(matriks);
        for(int i = 0; i<matriks.length; i++){
            for(int j=0; j<matriks[i].length; j++){
                matriks[i][j] *= (1/det);
            }
        }
        return matriks;
    }

    private static double[][] getKofaktor(double[][]matriks){
        double[][] m = new double[matriks.length][matriks[0].length];
        for (int i = 0; i<matriks.length; i++){
            for (int j = 0; j<matriks[i].length; j++){
                m[i][j] = getKofaktorValue(matriks, i, j) * (double)Math.pow(-1,(i+j));
            }
        }
        return m;
    }

    private static double getKofaktorValue(double[][] matriks, int x, int y){
        double[][] temp_matriks = new double[matriks.length-1][matriks[0].length-1];
        int tempi = 0, tempj = 0;
        for (int i=0; i<matriks.length; i++){
            for (int j=0; j<matriks[i].length; j++){
                if (i != x && j != y){
                    temp_matriks[tempi][tempj] = matriks[i][j];
                    tempj++;
                    if (tempj>=temp_matriks.length){
                        tempj = 0;
                        tempi++;
                    }
                        
                }
            }
        }
        return Determinan_Kofaktor.determinan_kofaktor(temp_matriks);
    }

    private static double[][] getTranspose(double[][] matriks){
        double[][] matriks_transpose= new double[matriks.length][matriks[0].length];
        for (int i = 0; i<matriks.length; i++){
            for (int j = 0; j<matriks[i].length; j++){
                matriks_transpose[i][j] = matriks[j][i];
            }
        }
        return matriks_transpose;
    }
}