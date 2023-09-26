import java.lang.Math;

public class BalikanAdjoin{
    public static void main(String[] args){
        TextToMatriks.printMatrix(TextToMatriks.readMatrixFromFile("filename.txt"));
        getInverseFromAdjoin(TextToMatriks.readMatrixFromFile("filename.txt"));
    }
    public static double[][] getInverseFromAdjoin(double[][] matriks){
        double det = determinant(matriks);
        if (det == 0) {
            System.out.println("Matriks memiliki balikan jika dan hanya jika nilai determinan matriks tidak sama dengan 0!");
            return new double[1][1]; 
        }
        matriks = getKofaktor(matriks);
        matriks = getTranspose(matriks);
        for(int i = 0; i<matriks.length; i++){
            for(int j=0; j<matriks[i].length; j++){
                matriks[i][j] *= (1/det);
            }
        }
        TextToMatriks.printMatrix(matriks);
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
        return determinant(temp_matriks);
    }
    private static double determinant(double[][] m){
        if (m.length == 0) return (0);
        if (m.length == 1) return (m[0][0]);
        if (m.length == 2) return (m[0][0]*m[1][1]) - (m[0][1]*m[1][0]);

        int i,j,k,l;
        double det=0;
        for (i=0; i<m.length; i++){
            double[][] mtemp = new double[m.length-1][m[0].length-1];
            for(j=0; j<mtemp.length; j++){
                l = 0;
                for(k=0; k<m[0].length; k++){
                    if(k != i){
                        mtemp[j][l] = m[j+1][k];
                        l++;
                    }
                }
            }

            det += (m[0][i])*(Math.pow(-1,i))*determinant(mtemp);
        }

        return det;
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