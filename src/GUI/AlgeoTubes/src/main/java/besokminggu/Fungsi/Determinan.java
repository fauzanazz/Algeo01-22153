/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besokminggu.Fungsi;


/**
 * 
 * @author DELL
 */
public class Determinan {

    // public static void main (String[] args){
    //     System.out.println(
    //         getDeterminanOutput(TextToMatriks.readMatrixFromFile("Algeo/filename.txt"), "NxN")
    //     );
    //     System.out.println(
    //         getDeterminanOutput(TextToMatriks.readMatrixFromFile("Algeo/filename.txt"), "Gauss")
    //     );
    //     System.out.println(
    //         getDeterminanOutput(TextToMatriks.readMatrixFromFile("Algeo/filename.txt"), "Kofaktor")
    //     );
    // }
    // Get String Output
    public static String getDeterminanOutput (double[][] matriks, String Function){
        double s = 0;
        if  (Function == "NxN"){
            s = DeterminanNxN(matriks);
        } else if (Function == "Gauss"){
            s = determinangauss(matriks);
        } else if (Function == "Kofaktor"){
            s = determinan_kofaktor(matriks);
        }
        
        return String.format("Hasil Determinan: %.3f", s);
    }
    
    // Determinan Gauss
    public static double determinangauss(double[][] matrix) {
        double det = 1; int rows; int i;
        int n = matrix.length;
        double[][] localMatrix = matrix;
        for(rows=0; rows<n; rows++){
            // Find the row where matrixf[rows][rows] != 0
            if (localMatrix[rows][rows] == 0){
                i = rows;
                while(localMatrix[i][rows] == 0 && i<(n-1)){
                    i++;
                }
                
                // if not found and the last localMatrix also have 0, then it has 0 as determinant
                if(i == (n-1) && localMatrix[i][rows] == 0){
                    return 0;
                }else {
                    // Swap row-rows with the row that has a non-zero value
                    for (int j=0; j<n; j++){
                        double temp = (-1) * localMatrix[rows][j];
                        localMatrix[rows][j] = localMatrix[i][j];
                        localMatrix[i][j] = temp;
                    }
                }
            }
            
            
            // The state right now should be (x x x .... xn) for the first row
            // Subtract row-1 times the first row from all rows below, dividing by the rows' elements.
            for (i = rows+1; i<n; i++){
                if (localMatrix[i][rows] != 0){
                    double ratio = localMatrix[i][rows]/localMatrix[rows][rows];
                    for (int j=0; j<n; j++){
                        localMatrix[i][j] = localMatrix[i][j] - ratio*localMatrix[rows][j];
                    }
                }
            }
        }

        
        // Find the double det by multiplying the diagonal elements
        for (i = 0; i < n; i++) {
            det *= localMatrix[i][i];
        }

        return det;
    }
    
    // Determinan Kofaktor
    public static double determinan_kofaktor(double[][] m){
        if(m.length == 0){
            return 0;
        }
        if(m.length == 1){
            return m[0][0];
        }
        if(m[0].length == 2){
            return (m[0][0] * m[1][1]) - (m[0][1] *m[1][0]);
        }

        double result = 0;
        int l;
        for(int i = 0; i < m.length; i++){
            double[][] temp = new double[m.length-1][m[i].length-1];
            for(int j = 0; j < temp.length ; j++){
                l = 0;
                for(int k = 0; k < m[i].length;k++){
                    if(k != i){
                        temp[j][l] = m[j+1][k];
                        l++;
                    }
                }

            }
            result += m[0][i] * Math.pow(-1, 2+i)* determinan_kofaktor(temp);
        }

        return result;
    }


    // Determinan NxN
    public static double DeterminanNxN(double[][] matriks){
        int m = matriks.length;
        double det = 0;
        // if (m != matriks[0].length){
        //     sOut +=("Matriks yang diinput bukan merupakan matriks persegi!");
        //     return 0;
        // }
        // if (m != 2 && m != 3){
        //     sOut +=("Matriks yang diinput bukan merupakan matriks 2x2 atau 3x3!");
        //     return 0;
        // }
        if (m == 2){
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
