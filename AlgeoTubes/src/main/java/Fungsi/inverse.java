package Fungsi;

public class Inverse {

    // Start of Inverse Matrix using Matrix Identity and Gauss-Jordan Elimination
    private static void exception(float[][] matrix){ // Check whether the Matrix is a Square Matrix 
        if(matrix[0].length != matrix.length){ 
            System.err.println("The Length of the Column and Row must Same");
            System.exit(0);
        }
        for(int i = 0; i < matrix.length; i++){ // Check the length Equality of each Row
            for(int j = i; j < matrix.length;j++){
                if(matrix[i].length != matrix[j].length){
                    System.err.println("The length of each Row must Same");
                    System.exit(0);
                }
            }
        }     
        if(Determinan.determinan_kofaktor(matrix) == 0){ //Check the Determinant of current Matrix
            System.err.println("The Matrix doesn't have an Inverse");
            System.exit(0);
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

    private static float[][] Add_MatrixIdentity(float[][] matrix){ // make a matrix that combine the current matrix with its matrix identity
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

    private static int zeroCounter(float[][] matrix, int row){ // Count the Amount of Zero in the left of matrix[row], this will be used in the Swapping Operation
        int sum = 0;
        for(int i = 0; i < matrix[row].length; i++){
            if(matrix[row][i] == 0){
                sum++;
            }
            else{
                return sum;
            }
        }
        return sum;
    }

    private static void swapping_Operation(float[][] matrix){ // Calculate the Logic Behind The Swapping of 2 Rows
        for(int i = 0; i < matrix.length; i++){
            for(int j = matrix.length; j >= i; j--){
                if(zeroCounter(matrix, i) > zeroCounter(matrix, j)){
                    matrix_Swapping(matrix, i, j);
                }
            }
            
        }
    }

    private static void matrix_Swapping(float[][] matrix, int row_will_be_changed, int row_who_changed){ // swap between the Matrix[row_will_be_changed] and Matrix[row_who_changed]
        float[][] temp = new float[1][matrix[0].length];
        for(int i = 0; i < matrix[0].length;i++){
             temp[0][i] = matrix[row_who_changed][i];
             matrix[row_who_changed][i] = matrix[row_will_be_changed][i];
             matrix[row_will_be_changed][i] = temp[0][i];
        }
    }
    private static void Multipy_Operation(float[][] matrix, int row_will_be_changed, int column_will_be_changed){ // multiply the desired row_will_be_changed and column_will_be_changed so the result is 1
        float x = 1/matrix[row_will_be_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length;i++){
            matrix[row_will_be_changed][i] *= x;
        } 
    }

    private static void Reduce_Operation(float[][] matrix, int row_will_be_changed,int column_will_be_changed, int row_who_changed){ // Reduce the selected Matrix[row_will_be_changed][column_will_be_changed] into 0 and apply it to the rest of the Column
        float x =  matrix[row_will_be_changed][column_will_be_changed]/matrix[row_who_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length; i++){
            matrix[row_will_be_changed][i] -= (x*matrix[row_who_changed][i]);
        }
    }

    private static float[][] Gauss_Operation(float[][] matrix){ // Do Gauss - Jordan Operation to change the current matrix into matrix identity
        float[][] matrix_inverted = Add_MatrixIdentity(matrix);

        for(int i = 0; i < matrix_inverted.length; i++){
            swapping_Operation(matrix_inverted);
            Multipy_Operation(matrix_inverted, i, i);
            for(int j = 0; j < matrix_inverted.length;j++){
                if(j != i){
                    Reduce_Operation(matrix_inverted, j, i, i);
                    if(matrix_inverted[j][i] == 0){
                        matrix[j][i] = Math.abs(matrix[i][j]);
                    }
                }
            }
        }  


        return matrix_inverted;
    }

    
    public static void Inverse_Matriks(float[][] matrix){ // The Main Function of Turning a Matrix into inverted Matrix
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

    // End of Inverse Matrix using Identity and Gauss-Jordan Elimination
}
