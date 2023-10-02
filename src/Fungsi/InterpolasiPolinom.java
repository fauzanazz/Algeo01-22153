/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besokminggu.fungsialgeo;

/**
 *
 * @author DELL
 */
public class InterpolasiPolinom {
    public static float[][] pointToFungsiInterpolasi(float[][] matrix_point, int banyak_point){
        float[][] matrix_fungsi = new float[banyak_point][banyak_point+1];
        for (int i = 0; i < banyak_point; i++) {
            for (int j = 0; j < banyak_point+1; j++) {
                if (j == banyak_point) {
                    matrix_fungsi[i][j] = matrix_point[i][1];
                    continue;
                } else {
                    matrix_fungsi[i][j] = (float) Math.pow(matrix_point[i][0], j);
                }
            }
        }
        return matrix_fungsi;
    }
}
