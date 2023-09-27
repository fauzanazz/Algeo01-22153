import java.util.Arrays;
public class Eliminasi_Gauss {
    private static void PrintMatrix(double[][] matrix){ // print the matrix
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]+ " ");
            }
           System.out.println(); 
        }

    }

    private static void Multipy_Operation(double[][] matrix, int row_will_be_changed, int column_will_be_changed){ // multiply the desired row_will_be_changed and column_will_be_changed so the result is 1
        while(column_will_be_changed < matrix[row_will_be_changed].length && matrix[row_will_be_changed][column_will_be_changed] == 0.0){
            column_will_be_changed++;
        }

        if (column_will_be_changed < matrix[row_will_be_changed].length) {

            double x = 1/matrix[row_will_be_changed][column_will_be_changed];
            for(int i = 0; i < matrix[0].length;i++){
                matrix[row_will_be_changed][i] *= x;
            } 
        }
    }
    
    private static void Reduce_Operation(double[][] matrix, int row_will_be_changed,int column_will_be_changed, int row_who_changed){
        if(matrix[row_who_changed][column_will_be_changed] != 0){
            double x =  matrix[row_will_be_changed][column_will_be_changed]/matrix[row_who_changed][column_will_be_changed];
            for(int i = 0; i < matrix[0].length; i++){
                matrix[row_will_be_changed][i] -= (x*matrix[row_who_changed][i]);
            }
        }

    }

    private static void negative_0_handler(double[][] matriks){
        for(int i = 0; i < matriks.length; i++){
            for(int j = 0; j < matriks[i].length; j++){
                if(matriks[i][j] == (double) 0.0){
                    matriks[i][j] = Math.abs(matriks[i][j]);
                }
            }   
        }
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

    public static void Gauss_Elimination(double[][] matriks){

        int k = 0;
        for(int i = 0; i < matriks.length; i++){
            swapping_Operation(matriks);
            while((i + k) < matriks[i].length && matriks[i][i+k] == 0.0){
                k++;
            }
            if(i + k < matriks[i].length){            
                Multipy_Operation(matriks, i, i+k);
                for(int j = i+1; j < matriks.length;j++){
                    Reduce_Operation(matriks, j, i+k, i);
                }
            }
        
            negative_0_handler(matriks);
            }
        PrintMatrix(matriks);
        Check_Exception(matriks);
        }

    public static class ParametricVariable{
        private double NonParamValue;
        private double[] ParamValue;
        private char[] valueRepresentation;

    
        public ParametricVariable(double NonParamValue, double[] ParamValue, char[] valueRepresentation, int zero_solution){
            this.NonParamValue = NonParamValue;
            this.ParamValue = new double[zero_solution];
            this.valueRepresentation = new char[zero_solution];
        }

        private void printVariable(int x_counter, int Params_Counter){
            System.out.print("x-"+ x_counter +" : ");
            // All Filled (Without 0 number)
            if(this.NonParamValue != 0){
                System.out.print(this.NonParamValue);
            }
            for (int i = 0; i < Params_Counter; i++) {
                if(this.ParamValue[i] > 0 && this.ParamValue[i] != 1){
                    System.out.print(" +"+this.ParamValue[i]);
                }
                else if(this.ParamValue[i] < 0){
                    System.out.print(this.ParamValue[i]);
                }
                if(this.ParamValue[i] == 1 && this.NonParamValue != 0){
                    System.out.print(" +");
                }
                if(this.ParamValue[i] != 0){
                    System.out.print(this.valueRepresentation[i]);
                }
            }
            System.out.println();
        }
    }


    private static int zero_col_counter(double[][] matriks){
       int sum = 0;
        for(int i = 0; i < matriks[0].length; i++){ //Checking for Rows of 0
            boolean check = false;
            for(int j = 0; j < matriks.length;j++){
                if(matriks[j][i] != 0){
                    check = true;
                }
            }
            
            if(!check){
                sum++;
            }
        }
        return sum; 
    }

    private static int zero_rows_counter(double[][] matriks){
        int sum = 0;
        for(int i = 0; i < matriks.length; i++){ //Checking for Rows of 0
            boolean check = false;
            for(int j = 0; j < matriks[0].length;j++){
                if(matriks[i][j] != 0){
                    check = true;
                }
            }
            System.out.println(i + ":" +check);
            if(!check){
                sum++;
            }
        }
        return sum;
    }
    private static void parametric_Solution(double[][] matrix, int zero_rows, int zero_cols){
        int length_of_parameter = zero_cols+zero_rows;
        char[] alphabet = new char[length_of_parameter];
        for(int i = 97; i < (97 + length_of_parameter) ; i++){
            alphabet[i % 97] = (char) i; 
        }
        ParametricVariable[] variables = new ParametricVariable[matrix[0].length-1];
        int row = matrix.length;
        int col = matrix[0].length;
        int w = 0;
        for(int i = 0; i < col-1;i++){
            int sum_zero = 0;
            double[] paramsValue = new double[zero_rows + zero_cols]; // Create a new array for each instance
            char[] alphabetCopy = Arrays.copyOf(alphabet, alphabet.length); // Create a new array for each variables[i]
            variables[i] = new ParametricVariable(0, paramsValue, alphabetCopy, zero_rows + zero_cols);
            for (int j = 0; j < length_of_parameter; j++) { 
                variables[i].ParamValue[j] = 0;
                variables[i].valueRepresentation[j] = alphabetCopy[j];
            }
            while(matrix[sum_zero][i] == 0){
                if(sum_zero < matrix.length-1){
                    sum_zero++;
                }
                else{
                    variables[i].ParamValue[zero_rows+w] = 1;
                    variables[i].valueRepresentation[zero_rows+w-1] = alphabet[zero_rows+w];
                    w++;
                    break;
                }
  
            }
        }
        int l = 0;
        for(int i = row-1; i > -1; i--){
            boolean Rowleading = false;
            for(int j = 0; j < col-1;j++){

                if(matrix[i][j] == 1){
                    int n = col-1-zero_rows;
                    int m = zero_rows-1;
                    Rowleading = true;
                    variables[j].NonParamValue = matrix[i][col-1];
                    for(int k = 0; k < col-1; k++){
                        if(k != j){
                            variables[j].NonParamValue -= (variables[k].NonParamValue * matrix[i][k]);
                            if(k == n){
                                variables[j].ParamValue[m] -= variables[k].ParamValue[m] * matrix[i][k];
                                n++;
                                m--;
                            }
                        }
                    }  
                    break;
                }
            }
            
            if(!Rowleading){
                l++;
                variables[variables.length - l].ParamValue[l-1] = 1;        
                variables[variables.length - l].valueRepresentation[l-1] = alphabet[l-1];
                continue;
            }

        }
        for(int z = 0; z < variables.length; z++){
            variables[z].printVariable(z+1, length_of_parameter);
        }

    }
    
    
    private static void Check_Exception(double[][] matriks){
        int accept = 0;
        if(matriks[matriks.length-1][matriks[0].length-1] == 0){
            accept = 2;
            for(int i = 0; i < matriks[0].length-1;i++){
                if(matriks[matriks.length-1][i] != 0){
                    accept =1;
                }
            }
        }
        else{
            accept = 3;
            for(int i = 0; i < matriks[0].length-1;i++){
                if(matriks[matriks.length-1][i] != 0){
                    accept =1;
                }
            }
        }
        if(accept == 3){
            System.err.println("System has no Solutions");
            System.exit(0);
        }
        else if(accept == 2 ){
            parametric_Solution(matriks,zero_rows_counter(matriks),zero_col_counter(matriks));
            System.exit(0);
        }
    }  

    public static void SPL_From_Gauss(double[][] matriks){
        Gauss_Elimination(matriks);
        double[][] m;
        if (matriks.length > matriks[0].length){
           m = new double[matriks.length][matriks[0].length];
        } else {
            m = new double[matriks[0].length-1][matriks[0].length]; 
        }

        for (int i=0; i<matriks.length; i++){
            for (int j=0; j<matriks[i].length; j++){
                m[i][j] = matriks[i][j];
            }
        }
        PrintMatrix(m);
        System.out.println("\n\n");
        Check_Exception(m);
        int k = 0;
        double[] hasil = new double[m[0].length-1];
        for(int i = m.length-1; i > -1; i--){
           double temp = 0;
           while (m[i][i] == 0 && (i + k) < m.length){
               k++;
           }
           if(i + k < m.length){
               for(int j = m.length; j > i ; j--){
                   temp += m[i][j-1] * hasil[j-1];
               }
               hasil[i] = (1/m[i][i+k]) * (m[i][m[i].length-1] - temp);
           }
        }
        for(int i = 0; i < hasil.length;i++){
            System.out.println(hasil[i]);
        }
    }

    public static void main(String[] args){
        TextToMatriks T1 = new TextToMatriks();
        double[][] matriks = T1.readMatrixFromFile("filename.txt");
        SPL_From_Gauss(matriks);
    }
}
