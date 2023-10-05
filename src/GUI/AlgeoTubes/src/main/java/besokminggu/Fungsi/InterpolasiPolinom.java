/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besokminggu.Fungsi;

/**
 *
 * @author DELL
 */
public class InterpolasiPolinom {
    
    public static String getPolinomOutput(double[][] matrix, double x){
        String output = "F(x) = ";
        double[][] matriks_polinom = pointToFungsiInterpolasi(matrix);
        SPL.Gauss_Elimination(matriks_polinom);
        double[][] matriks_penyelesaian = SPLSolutionMatrix(matriks_polinom);
        double hasil = 0;
        for (int i=0;i<matriks_penyelesaian.length;i++){
            hasil += matriks_penyelesaian[i][0] * Math.pow(x,i);
            if (i != matriks_penyelesaian.length-1){
                output += String.format("%fx^%d+ ", matriks_penyelesaian[i][0],i);
            } else {
                output += String.format("%f", matriks_penyelesaian[i][0]);
            }
        }
        output += String.format("\nf(%.3f) = %.4f", x, hasil);
        return output;
    }
    
    public static double[][] SPLSolutionMatrix(double[][] m){ 
        double[][] hasil = new double[m[0].length-1][1];
        int k = 0;
        for(int i = m.length-1; i > -1; i--){
                    double temp = 0;
                    while (m[i][i] == 0 && (i + k) < m.length){
                        k++;
                    }
                    if(i + k < m.length){
                        for(int j = m.length; j > i ; j--){
                            temp += m[i][j-1] * hasil[j-1][0];
                        }
                        hasil[i][0] = (1/m[i][i+k]) * (m[i][m[i].length-1] - temp);
                    }
                }
        return hasil;
    }
    
    public static double[][] pointToFungsiInterpolasi(double[][] matrix_point){
        double[][] matrix_fungsi = new double[matrix_point.length][matrix_point.length+1];
        for (int i = 0; i < matrix_fungsi.length; i++) {
            for (int j = 0; j < matrix_fungsi[0].length; j++) {
                if (j == matrix_fungsi[0].length-1) {
                    matrix_fungsi[i][j] = matrix_point[i][1];
                } else {
                    matrix_fungsi[i][j] = (double) Math.pow(matrix_point[i][0], j);
                }
            }
        }
        return matrix_fungsi;
    }
}
