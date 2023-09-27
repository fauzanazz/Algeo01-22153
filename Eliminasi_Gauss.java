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
        double x = 1/matrix[row_will_be_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length;i++){
            matrix[row_will_be_changed][i] *= x;
            if(matrix[row_will_be_changed][i] == 0){ // Encounter any -0.0
                matrix[row_will_be_changed][i] = Math.abs(matrix[row_will_be_changed][i]); 
            }
        } 
    }
    
    private static void Reduce_Operation(double[][] matrix, int row_will_be_changed,int column_will_be_changed, int row_who_changed){ // Reducing a certain m[row][column] into 0 then apply it to the rest of the column
        double x =  matrix[row_will_be_changed][column_will_be_changed]/matrix[row_who_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length; i++){
            matrix[row_will_be_changed][i] -= (x*matrix[row_who_changed][i]);
            if(matrix[row_will_be_changed][i] == 0){ // Encounter any -0.0
                matrix[row_will_be_changed][i] = Math.abs(matrix[row_will_be_changed][i]); 
            }
        }
    }
    
    private static int zeroCounter(double[][] matrix, int row){ // Count the Amount of Zero in the left of matrix[row], this will be used in the SwappingOp eration
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

    private static void swapping_Operation(double[][] matrix){ // Swap 2 rows if one row have more 0 before the leading 1 than the other row
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

    public static void Gauss_Elimination(double[][] matriks){ // Do the normal Gauss Elimination

        int k = 0; //index that count if a matriks[row][column] == 0
        for(int i = 0; i < matriks.length; i++){
            swapping_Operation(matriks);
            while((i + k) < matriks[i].length && matriks[i][i+k] == 0.0){ //Encountering Infinity Result
                k++;
            }
            if(i + k < matriks[i].length){            
                Multipy_Operation(matriks, i, i+k); //Multiply matriks[i][column] into 1, and applied the calculation to the rest of its column
                for(int j = i+1; j < matriks.length;j++){
                    Reduce_Operation(matriks, j, i+k, i); //Reducing matriks[j][i+k] into 0, and applied to the rest of its column
                }
            }
            Check_Exception(matriks); // Check any exception
            }
        }

    public static class ParametricVariable{ // Creating a datatype for easier parametric calculation
        private double NonParamValue; //Used for any non-parametric variable
        private double[] ParamValue; //Used only for Parameters variable
        private char[] valueRepresentation; //Used as parametric representation (Ex: a,b,c)

    
        public ParametricVariable(double NonParamValue, double[] ParamValue, char[] valueRepresentation, int zero_solution){ //Datatype Constructor
            this.NonParamValue = NonParamValue;
            this.ParamValue = new double[zero_solution];
            this.valueRepresentation = new char[zero_solution];
        }

        private void printVariable(int x_counter, int Params_Counter){ // Print all the Parametric Result, x_counter used for what x is currently printed, Params_counter used for the amount of ParamsValue that can be printed.
            int zero_counter = 0; // 0 counter for Parametervalue, it is used to print 0, nonparametervalue and parametervalue has all 0 of its value
            System.out.print("x-"+ x_counter +" : "); 

            if(this.NonParamValue != 0){
                System.out.print(this.NonParamValue);
            }
            for (int i = 0; i < Params_Counter; i++) { //Logic for Params_value Printing
                if(this.ParamValue[i] > 0 && this.ParamValue[i] != 1){
                    if(this.NonParamValue != 0){
                        System.out.print(" +"+this.ParamValue[i]);
                    }
                    else{
                        System.out.print(this.ParamValue[i]);
                    }
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
                else if(this.ParamValue[i] == 0){
                    zero_counter++;
                }
            }
            if(zero_counter==Params_Counter && this.NonParamValue == 0){
                System.out.print(0);
            }
            
            System.out.println();
        }
    }


    private static int zero_col_counter(double[][] matriks){ // Check the amount of rows that all its column is 0, for Parametric Calculation
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

    private static int zero_rows_counter(double[][] matriks){ // Check the amount of column that all its row is 0, for Parametric Calculation
        int sum = 0;
        for(int i = 0; i < matriks.length; i++){ //Checking for Rows of 0
            boolean check = false;
            for(int j = 0; j < matriks[0].length;j++){
                if(matriks[i][j] != 0){
                    check = true;
                }
            }
            if(!check){
                sum++;
            }
        }
        return sum;
    }
    private static void parametric_Solution(double[][] matrix, int zero_rows, int zero_cols){ //Calculation for Parametric Exception
        int length_of_parameter = zero_cols+zero_rows; // Amount
        int row = matrix.length; // Length of row
        int col = matrix[0].length; // Length of Column
        
        // Create List of Alphabet as a variable Representative
        char[] alphabet = new char[26]; 
        for(int i = 97; i < (123) ; i++){
            alphabet[i % 97] = (char) i; 
        }

        // Creating the base of the SPL_Solution using Parametric Datatype
        ParametricVariable[] variables = new ParametricVariable[col-1]; 
        int w = 0; // Only Used as a counter if there are any Column with All of its row is 0
        for(int i = 0; i < col-1;i++){
            int sum_zero = 0; //used as row_index for column with full 0 on its row
            double[] paramsValue = new double[zero_rows + zero_cols]; // Create a new array for each instance
            char[] alphabetCopy = Arrays.copyOf(alphabet, alphabet.length); // Create a new array for each variables[i]
            variables[i] = new ParametricVariable(0, paramsValue, alphabetCopy, zero_rows + zero_cols);
            for (int j = 0; j < length_of_parameter; j++) { // Default value of ParamValue and valueRepresentation
                variables[i].ParamValue[j] = 0;  
                variables[i].valueRepresentation[j] = alphabetCopy[j]; 
            }
            while(matrix[sum_zero][i] == 0){ //Check if all the existing rows on a particular column has value of 0, asumsi: Jika semua Column, memiliki row bernilai 0, maka hasilnya suatu variable representation
                if(sum_zero < matrix.length-1){
                    sum_zero++;
                }
                else{
                    variables[i].ParamValue[zero_rows+w] = 1;
                    variables[i].valueRepresentation[zero_rows+w] = alphabet[zero_rows+w];
                    w++;
                    break;
                }
  
            }
        }

        int[] index_banned = new int[zero_rows]; // List of Index that will if it become a leading 0, the paramValue will be 0
        int rows_tracker = 0; //index for tracking the variables list that has row full of 0
        int index_tracker = 0; //index for tracking the ParamValue and valueRepresentation index
        
        //Calculation
        for(int i = row-1; i > -1; i--){ 
            boolean Rowleading = false; //Checking whether a leading 1 is exist

            for(int j = 0; j < col-1;j++){
                if(matrix[i][j] == 1){ // Leading 1 exist
                    for(int k : index_banned){ // Leading 1 shouldn't have its own ParamValue, check if leading 1 has its own ParamValue
                        if(k == j){
                            variables[j].ParamValue[col-k-2] = 0;
                        }
                    }
                    Rowleading = true; 
                    variables[j].NonParamValue = matrix[i][col-1];
                    for(int k = 0; k < col-1; k++){ //Calculation with other Variable
                        if(k != j){
                            variables[j].NonParamValue -= (variables[k].NonParamValue * matrix[i][k]); //Changing the NonparamValue of current Leading 1
                            for(int q = 0; q < zero_rows;q++){
                                variables[j].ParamValue[q] -= variables[k].ParamValue[q] * matrix[i][k]; //Changing the ParamValue of current Leading 1
                            }
                        }
                    }  
                    break;
                }
            }
            
            if(!Rowleading){ //if a row has full of 0
                if(index_banned.length > 0){ //Exception if there are no rows that full of 0
                    index_banned[rows_tracker] = variables.length-rows_tracker-1;
                }
                rows_tracker++;
                variables[variables.length - rows_tracker].ParamValue[index_tracker] = 1;        
                variables[variables.length - rows_tracker].valueRepresentation[index_tracker] = alphabet[index_tracker];
                index_tracker++;
                continue;                
            }

        }
        for(int z = 0; z < variables.length; z++){ //Print the Solution
            variables[z].printVariable(z+1, length_of_parameter);
        }
    }
    
    
    private static void Check_Exception(double[][] matriks){ //Checking Exception
        int accept = 0;
        if(matriks[matriks.length-1][matriks[0].length-1] == 0){ //Check for Parametric Exception
            accept = 2;
            for(int i = 0; i < matriks[0].length-1;i++){
                if(matriks[matriks.length-1][i] != 0){
                    accept =1;
                }
            }
        }
        else{ //Check for No-Solution Exception
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

    public static void SPL_From_Gauss(double[][] matriks){ //Spl Solutions
        Gauss_Elimination(matriks); //Gauss Elimination on current Matriks
        double[][] m; 

        //Make a m that is a square matriks without augmented
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
        
        //Do a Check_Exception for matrix m
        Check_Exception(m); 

        //If there are only single solutions
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
            System.out.print(hasil[i] + " ");
        }

    }

    public static void main(String[] args){
        TextToMatriks T1 = new TextToMatriks();
        double[][] matriks = T1.readMatrixFromFile("filename.txt");
        SPL_From_Gauss(matriks);
    }
}
