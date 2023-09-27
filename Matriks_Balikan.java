public class Matriks_Balikan {
    /* Rule
     * 1. Matrix that has inverse is a square Matrix or the size of Row and column should match
     * 2. Each Row of Matrix Must have the same amount of Column and 
     * 3. A Matrix Has Inverse if the Determinant of its Matrix isn't 0
     */
    // static int UNIDENTIFIED = -1; // Used for unidentified value, example: not-found index
    static int UNIDENTIFIED = -1;
    private static void exception(double[][] matrix){ // Check whether the Matrix is a Square Matrix 
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

        if(Determinan_Kofakto.determinan_kofaktor(matrix) == 0){ //Check the Determinant of current Matrix
            System.err.println("The Matrix doesn't have an Inverse");
            System.exit(0);
        }
    }

    private static double[][] MatriksIdentity_Maker(double[][] matrix){ // make a matrixIdentity
        int n = matrix.length;
        double[][] matrix_identity = new double[n][n];
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

    private static double[][] Add_MatrixIdentity(double[][] matrix){ // make a matrix that combine the current matrix with its matrix identity
        int n = matrix.length;
        double[][] matriks_identity = MatriksIdentity_Maker(matrix);
        double[][] temp = new double[n][n*2];
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

    private static int zeroCounter(double[][] matrix, int row){ // Count the Amount of Zero in the left of matrix[row], this will be used in the Swapping Operation
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

    private static void swapping_Operation(double[][] matrix){ // Calculate the Logic Behind The Swapping of 2 Rows
        for(int i = 0; i < matrix.length; i++){
            for(int j = matrix.length; j >= i; j--){
                if(zeroCounter(matrix, i) > zeroCounter(matrix, j)){
                    matrix_Swapping(matrix, i, j);
                }
            }
            
        }
    }

    private static void matrix_Swapping(double[][] matrix, int row_will_be_changed, int row_who_changed){ // swap between the Matrix[row_will_be_changed] and Matrix[row_who_changed]
        double[][] temp = new double[1][matrix[0].length];
        for(int i = 0; i < matrix[0].length;i++){
             temp[0][i] = matrix[row_who_changed][i];
             matrix[row_who_changed][i] = matrix[row_will_be_changed][i];
             matrix[row_will_be_changed][i] = temp[0][i];
        }
    }

    private static void Multipy_Operation(double[][] matrix, int row_will_be_changed, int column_will_be_changed){ // multiply the desired row_will_be_changed and column_will_be_changed so the result is 1
        double x = 1/matrix[row_will_be_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length;i++){
            matrix[row_will_be_changed][i] *= x;
        } 
    }

    private static void Reduce_Operation(double[][] matrix, int row_will_be_changed,int column_will_be_changed, int row_who_changed){ // Reduce the selected Matrix[row_will_be_changed][column_will_be_changed] into 0 and apply it to the rest of the Column
        double x =  matrix[row_will_be_changed][column_will_be_changed]/matrix[row_who_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length; i++){
            matrix[row_will_be_changed][i] -= (x*matrix[row_who_changed][i]);
        }
    }

    private static void negative_0_handler(double[][] matriks){ // Handling the -0.0 if the value is printed
        for(int i = 0; i < matriks.length; i++){
            for(int j = 0; j < matriks[i].length; j++){
                if(matriks[i][j] == (double) 0.0){
                    matriks[i][j] = Math.abs(matriks[i][j]);
                }
            }   
        }
    }

    private static double[][] Gauss_Operation(double[][] matrix){ // Do Gauss - Jordan Operation to change the current matrix into matrix identity
        double[][] matrix_inverted = Add_MatrixIdentity(matrix);

        for(int i = 0; i < matrix_inverted.length; i++){
            swapping_Operation(matrix_inverted);
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

    private static double[][] perkalian_Matriks(double[][] matriks1, double[][] matriks2){ // Do matriks1 * matriks2 for SPL Purposes
        double[][] matriks_copy = new double[matriks1.length][matriks2[0].length]; 
        for(int i = 0 ; i < matriks1.length; i++){
            for(int j = 0; j < matriks2.length;j++){
                for(int k = 0; k < matriks2[0].length;k++){
                    matriks_copy[i][k] += matriks1[i][j] * matriks2[j][k];
                }
            }
        }
        return matriks_copy;
    }
    public static void Inverse_Matriks(double[][] matrix){ // The Main Function of Turning a Matrix into inverted Matrix
        exception(matrix);
        double[][] matriks_inverted = Gauss_Operation(matrix);
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

    
    public static void SPL_From_Inverse(double[][] matrix_input){ // The Main function of getting SPL result from AX = B, X = A^-1 * B 
        double[][] matriks_A = new double[matrix_input.length][matrix_input[0].length -1];
        double[][] matriks_B = new double[matrix_input.length][1];

        int row_length = matrix_input.length-1;
        int col_length = matrix_input[0].length;

        // Matriks for A
        for(int i = 0; i < col_length; i++){
            for(int j = 0; j < col_length;j++){
                matriks_A[i][j] = matrix_input[i][j];
            }
        }

        // Matriks for B
        for(int i = 0; i < col_length; i++){
            matriks_B[i][0] = matrix_input[i][row_length+1];
        }

        // Inverse Matriks A
        Inverse_Matriks(matriks_A);

        // A * X = B, X  = Inverse(A) * B
        matriks_B = perkalian_Matriks(matriks_A, matriks_B);


    }
}