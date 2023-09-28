/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fungsi;

/**
 *
 * @author DELL
 */
public class Determinan {
    public static float determinangauss(float[][] matrix, int matrix_size) {
        float det = 1; int rows; int i;
        
        for(rows=0; rows<matrix_size; rows++){
            // Find the row where matrixf[rows][rows] != 0
            if (matrix[rows][rows] == 0){
                i = rows;
                while(matrix[i][rows] == 0 && i<(matrix_size-1)){
                    i++;
                }
                
                // if not found and the last matrix also have 0, then it has 0 as determinant
                if(i == (matrix_size-1) && matrix[i][rows] == 0){
                    return 0;
                }else {
                    // Swap row-rows with the row that has a non-zero value
                    for (int j=0; j<matrix_size; j++){
                        float temp = matrix[rows][j];
                        matrix[rows][j] = matrix[i][j];
                        matrix[i][j] = temp;
                    }
                }
            }
            
            // The state right now should be (x x x .... xn) for the first row
            // Subtract row-1 times the first row from all rows below, dividing by the rows' elements.
            for (i = rows+1; i<matrix_size; i++){
                if (matrix[i][rows] != 0){
                    float ratio = matrix[i][rows]/matrix[rows][rows];
                    for (int j=0; j<matrix_size; j++){
                        matrix[i][j] = matrix[i][j] - ratio*matrix[rows][j];
                    }
                }
            }
        }

        // Find the float det by multiplying the diagonal elements
        for (i = 0; i < matrix_size; i++) {
        det *= matrix[i][i];
        }

        return det;
    }

}
