package AlgeoTubes.src.main.java.Fungsi;

public class Inverse {
    // public static void main (String[] args){
    //     System.out.println(getInverseOutput(TextToMatriks.readMatrixFromFile("Algeo/filename.txt"), "Adjoin"));
    //     System.out.println(getInverseOutput(TextToMatriks.readMatrixFromFile("Algeo/filename.txt"), "Identity"));
    // }

    // Start of returning string
    private static String sOut = "";

    public static String getInverseOutput(double[][] matriks, String Function){
        double[][] matrix = new double[matriks.length][matriks[0].length];
        if (Function == "Adjoin"){
            matrix = getInverseFromAdjoin(matriks);
    
        } else if (Function == "Identity"){
            matrix = get_Inverse_Matriks_fromIdentity(matriks); 
        } 
        sOut = "";
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                sOut += String.format("%.3f",matrix[i][j]);
                if (j != matrix[0].length -1) sOut += " ";
            }
            if (i != matrix.length -1) sOut += "\n";
        }
        return sOut;
    }
    // End of returning string
    

    // Start of Inverse Matrix using Matrix Identity and Gauss-Jordan Elimination
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
            for(int j = matrix.length-1; j >= i; j--){
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

    private static double[][] Gauss_Operation(double[][] matrix){ // Do Gauss - Jordan Operation to change the current matrix into matrix identity
        double[][] matrix_inverted = Add_MatrixIdentity(matrix);

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

    public static double[][] get_Inverse_Matriks_fromIdentity(double[][] matrix){ // The Main Function of Turning a Matrix into inverted Matrix
        // exception(matrix);
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
        return matrix;
    }
    // End of Inverse Matrix using Matrix Identity and Gauss-Jordan Elimination




    // Start of Inverse Matrix using Matrix Adjoin and Kofaktor
    public static double[][] getInverseFromAdjoin(double[][] matriks){
        double det = Determinan.determinan_kofaktor(matriks);
        // if (det == 0) {
        //     sOut += ("Matriks memiliki balikan jika dan hanya jika nilai determinan matriks tidak sama dengan 0!");
        //     return new double[1][1]; 
        // }
        // if (matriks.length != matriks[0].length) {
        //     sOut += ("Matriks yang diinput bukan merupakan matriks persegi!");
        //     return new double[1][1]; 
        // }
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
        return Determinan.determinan_kofaktor(temp_matriks);
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
    // End of Inverse Matrix using Matrix Adjoin and Kofaktor
}
