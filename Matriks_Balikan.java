import java.text.DecimalFormat;

import javax.print.event.PrintEvent;

public class Matriks_Balikan {

    // static int UNIDENTIFIED = -1; // Used for unidentified value, example: not-found index

    private static void exception(float[][] matrix){ // throw an error if the matrix used has diffrent length of column and row
        if(matrix[0].length != matrix.length){
            System.err.println("The Length of the Column and Row must Same");
            System.exit(0);
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = i; j < matrix.length;j++){
                if(matrix[i].length != matrix[j].length){
                    System.err.println("The length of each Row must Same");
                    System.exit(0);
                }
            }
        }
        
        if(matrix.length != matrix[0].length){
            System.err.println("This Operation only accept (n x n) matriks");
            System.exit(0);
        }
    }
    private static void PrintMatrix(float[][] matrix){ // print the matrix
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]+ " ");
            }
           System.out.println(); 
        }

    }

    private static float[][] MatriksIdentity_Maker(float[][] matrix){ // make a matrixIdentity
        int n = matrix.length;
        float[][] matrix_identity = new float[n][n];
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    matrix_identity[i][j] = 1;
                }
                else{
                    matrix_identity[i][j] = 0;
                }  
            }
        }
        return matrix_identity;
    }

    private static float[][] Add_MatrixIdentity(float[][] matrix){ // make a matrix combination of current matrix with its matrix identity
        int n = matrix.length;
        float[][] matriks_identity = MatriksIdentity_Maker(matrix);
        float[][] temp = new float[n][n*2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n;j++){
                temp[i][j] = matrix[i][j];
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n;j++){
                temp[i][j+n] = matriks_identity[i][j];
            }
        }
        return temp;
    }

    // private static void Swapping_Operation(float[][] matrix, int row_will_be_changed, int row_who_changed){ // swap between the row_will_be_changed and row_who_changed
    //     float[][] temp = new float[1][matrix[0].length];
    //     for(int i = 0; i < matrix[0].length;i++){
    //          temp[0][i] = matrix[row_who_changed][i];
    //          matrix[row_who_changed][i] = matrix[row_will_be_changed][i];
    //          matrix[row_will_be_changed][i] = temp[0][i];
    //     }
    // }

    private static void Multipy_Operation(float[][] matrix, int row_will_be_changed, int column_will_be_changed){ // multiply the desired row_will_be_changed and column_will_be_changed so the result is 1
        float x = 1/matrix[row_will_be_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length;i++){
            matrix[row_will_be_changed][i] *= x;
        } 
    }

    private static void Reduce_Operation(float[][] matrix, int row_will_be_changed,int column_will_be_changed, int row_who_changed){
        float x =  matrix[row_will_be_changed][column_will_be_changed]/matrix[row_who_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length; i++){
            matrix[row_will_be_changed][i] -= (x*matrix[row_who_changed][i]);
        }
    }

    private static void negative_0_handler(float[][] matriks){
        for(int i = 0; i < matriks.length; i++){
            for(int j = 0; j < matriks[i].length; j++){
                if(matriks[i][j] == (float) 0.0){
                    matriks[i][j] = Math.abs(matriks[i][j]);
                }
            }   
        }
    }

    // private static int find_index_value_1(float[][] matrix, int row_order){
    //     for(int i = 0; i< matrix.length/2; i++){
    //         if(matrix[i][row_order] == 1){
    //             return i;
    //         }
    //     }
    //     return UNIDENTIFIED;
    // }

    private static float[][] Gauss_Operation(float[][] matrix){
        float[][] matrix_inverted = Add_MatrixIdentity(matrix);
        for(int i = 0; i < matrix_inverted.length; i++){
            Multipy_Operation(matrix_inverted, i, i);
            for(int j = 0; j < matrix_inverted.length;j++){
                if(j != i){
                    Reduce_Operation(matrix_inverted, j, i, i);
                }
            }
        }  

        negative_0_handler(matrix_inverted);

        return matrix_inverted;
    }

    private static float[][] perkalian_Matriks(float[][] matriks1, float[][] matriks2){
        float[][] matriks_copy = new float[matriks1.length][matriks2[0].length]; 
        for(int i = 0 ; i < matriks1.length; i++){
            for(int j = 0; j < matriks2.length;j++){
                for(int k = 0; k < matriks2[0].length;k++){
                    matriks_copy[i][k] += matriks1[i][j] * matriks2[j][k];
                }
            }
        }
        return matriks_copy;
    }
    public static void Inverse_Matriks(float[][] matrix){
        exception(matrix);
        float[][] matriks_inverted = Gauss_Operation(matrix);
        int end_row = matriks_inverted.length;
        int start_col = (matriks_inverted[0].length)/2;
        int end_col = matriks_inverted[0].length;
        int k = 0;
        for(int i = 0; i < end_row ;i++){
            for(int j = start_col; j < end_col; j++){
                matrix[i][k] = matriks_inverted[i][j];
                k++;
            }
            k= 0;
        }
    }

    
    public static void SPL_From_Inverse(float[][] matrix_input){
        float[][] matriks_A = new float[matrix_input.length][matrix_input[0].length -1];
        float[][] matriks_B = new float[matriks_input.length][1];

        int row_length = matriks_input.length-1;
        int col_length = matriks_input[0].length;
        // Matriks for A
        for(int i = 0; i < col_length; i++){
            for(int j = 0; j < col_length;j++){
                matriks_A[i][j] = matrix_input[i][j];
            }
        }

        // Matriks for B
        for(int i = 0; i < col_length; i++){
            matriks_B[i][0] = matriks_input[i][row_length+1];
        }

        // Inverse Matriks A
        Inverse_Matriks(matriks_A);

        // A * X = B, X  = Inverse(A) * B
        matriks_B = perkalian_Matriks(matriks_A, matriks_B);


    }

    
    public static void main(String[] args){
        final float[][] matrix = {
            {5,7,9},{4,3,8},{7,5,6}
         };
        final float[][] matriks2 = {
            {1},{1},{1},
        };
        PrintMatrix(perkalian_Matriks(matrix, matriks2));
       
    }

}