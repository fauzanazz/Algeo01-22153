/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besokminggu.fungsialgeo;

import java.util.Arrays;

/**
 *
 * @author DELL
 */

public class SPL {

    
    /* ------------------------SPL From Cramer------------------------ */
    public static String SPLCramer(double[][] matrix) {
        double[][] matriks_temp = new double[matrix.length][matrix[0].length-1];
        String output = "";
        for (int i=1; i<matrix[0].length; i++){
            
            for (int row = 0 ; row < matrix.length ; row++) {
                for (int col = 0 ; col < matrix[0].length-1 ; col++) {
                    matriks_temp[row][col] = matrix[row][col];
                }
            }
            
            
            // Cari Determinan dari matrix ini.
            double det = Determinan.determinangauss(matriks_temp);
            
            
            // Jika determinan tidak ada, maka SPL tidak memiliki solusi.
            if (det == 0) {
                output += String.format("Tidak bisa ditemukan solusi untuk x%d menggunakan cramer karena det = 0\n",i);
                continue;
            }
            
            
            for (int row = 0 ; row < matrix.length ; row++) {
                for (int col = 0 ; col < matrix[0].length-1 ; col++) {
                    matriks_temp[row][col] = matrix[row][col];
                }
            }
            
            
            // Ubah matrix menjadi matrix cramer
            for (int j = 0; j < matrix.length;j++) {
                matriks_temp[j][i-1] = matrix[j][matrix[0].length-1];
            }
            
            
            // Cari Determinan matrix cramer
            double det_cramer = Determinan.determinangauss(matriks_temp);
            System.out.printf("Det %f ", det_cramer);
            System.out.print("\n");
            System.out.print("\n");
            
            
            // Cari solusi dari SPL
            double solusi = det_cramer/det;
            output += String.format("x%d = %.3f\n",i, solusi);
        }
        return output;
    }
    /* ------------------------SPL From Cramer------------------------ */
    



    /* ------------------------SPL From Inverse------------------------ */
    public static String SPL_From_Inverse(double[][] matrix_input){ // The Main function of getting SPL result from AX = B, X = A^-1 * B 
        // exception(matrix_input);
        double[][] matriks_A = new double[matrix_input.length][matrix_input[0].length -1]; //Make the non-Augmented Matriks
        double[][] matriks_A2 = new double[matrix_input.length][matrix_input[0].length -1]; //Make the non-Augmented Matriks
        double[][] matriks_B = new double[matrix_input.length][1]; //The Last Row

        int row_length = matrix_input.length;
        int col_length = matrix_input[0].length-1;

        // Matriks for A
        for(int i = 0; i < row_length; i++){
            for(int j = 0; j < col_length;j++){
                matriks_A[i][j] = matrix_input[i][j];
                matriks_A2[i][j] = matrix_input[i][j];
            }
        }

        // Matriks for B
        for(int i = 0; i < col_length; i++){
            matriks_B[i][0] = matrix_input[i][col_length];
        }
            
        //Cek exception
        if(Determinan.determinangauss(matriks_A2) == 0.0){
            return "Solusi tidak dapat dicari dengan menggunakan metode ini karena Determinannya 0";
        }

        // Inverse Matriks A
        matriks_A = Inverse.getInverseFromAdjoin(matriks_A);
        
        // A * X = B, X  = Inverse(A) * B
        matriks_B = perkalian_Matriks(matriks_A, matriks_B);
        
        return String_Converter(matriks_B);
    }
    
    public   static double[][] perkalian_Matriks(double[][] matriks1, double[][] matriks2){ // Do matriks1 * matriks2 for SPL Purposes
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

    public static String String_Converter(double[][] matrix){ // print the matrix\
        String sKeluar = "";
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                sKeluar += "x";
                sKeluar += i+1 + ": ";
                sKeluar += String.format("%.3f ",matrix[i][j]);
            }
            if(i != matrix.length-1){
                sKeluar += "\n";
            }
        }

        return  sKeluar;
    }
    /* ------------------------SPL From Inverse------------------------ */






     /* ------------------------SPL From Gauss Elimination------------------------ */
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
            if(Check_Exception(matriks) == 3 || Check_Exception(matriks) == 2){// Check any row_exception
                break;
            }
        }
    }
     public static class ParametricVariable{ // Creating a datatype for easier parametric calculation
        private double NonParamValue; //Used for any non-parametric variable
        private double[] ParamValue; //Used only for Parameters variable
        private String[] valueRepresentation; //Used as parametric representation (Ex: a,b,c)

    
        public ParametricVariable(double NonParamValue, double[] ParamValue, String[] valueRepresentation, int zero_solution){ //Datatype Constructor
            this.NonParamValue = NonParamValue;
            this.ParamValue = new double[zero_solution];
            this.valueRepresentation = new String[zero_solution];
        }

        private String printVariable(int x_counter, int Params_Counter){ // Print all the Parametric Result, x_counter used for what x is currently printed, Params_counter used for the amount of ParamsValue that can be printed.
            int zero_counter = 0; // 0 counter for Parametervalue, it is used to print 0, nonparametervalue and parametervalue has all 0 of its value
            String sKeluar = "";
            sKeluar += "x"+ x_counter +": "; 

            if(this.NonParamValue != 0){
                sKeluar += String.format("%.03f",this.NonParamValue);
            }
            for (int i = 0; i < Params_Counter; i++) { //Logic for Params_value Printing
                if(this.ParamValue[i] > 0 && this.ParamValue[i] != 1){
                    if(this.NonParamValue != 0){
                        sKeluar += String.format(" + %.03f",this.ParamValue[i]);
                    }
                    else{
                        sKeluar += String.format("%.03f",this.ParamValue[i]);
                    }
                }
                else if(this.ParamValue[i] < 0){
                    if(this.ParamValue[i] == -1){
                        sKeluar += "-";
                    }
                    else{
                        sKeluar += String.format("%.03f",this.ParamValue[i]);
                    }
                }
                if(this.ParamValue[i] == 1 && this.NonParamValue != 0){
                    sKeluar += "+";
                }
                if(this.ParamValue[i] != 0){
                    sKeluar += this.valueRepresentation[i];
                }
                else if(this.ParamValue[i] == 0){
                    zero_counter++;
                }
            }
            if(zero_counter==Params_Counter && this.NonParamValue == 0){
                sKeluar += String.valueOf(0);
            }
            return sKeluar;
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
    private static String parametric_Solution(double[][] matrix, int zero_rows, int zero_cols){ //Calculation for Parametric Exception
        int length_of_parameter = zero_cols+zero_rows; // Amount
        int row = matrix.length; // Length of row
        int col = matrix[0].length; // Length of Column
        String result = "";
        
        // Create List of Alphabet as a variable Representative
        String[] alphabet = new String[26]; 
        for(int i = 97; i < (123) ; i++){
            alphabet[i % 97] = ""; 
            alphabet[i % 97] += (char) i; 
        }

        // Creating the base of the SPL_Solution using Parametric Datatype
        ParametricVariable[] variables = new ParametricVariable[col-1]; 
        int w = 0; // Only Used as a counter if there are any Column with All of its row is 0
        for(int i = 0; i < col-1;i++){
            int sum_zero = 0; //used as row_index for column with full 0 on its row
            double[] paramsValue = new double[zero_rows + zero_cols]; // Create a new array for each instance
            String[] alphabetCopy = Arrays.copyOf(alphabet, alphabet.length); // Create a new array for each variables[i]
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
                    variables[i].valueRepresentation[zero_rows+w] = "x"+(i+1);
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
            }

        }
        for(int z = 0; z < variables.length; z++){ //Print the Solution
            result += variables[z].printVariable(z+1, length_of_parameter);
            if(z != variables.length-1){
                result+="\n";
            }
        }
        return result;
    }
    
    
    private static int Check_Exception(double[][] matriks){ //Checking Exception
        int accept;
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
        return accept;
    }

    public static String SPL_From_Gauss(double[][] matriks){ //Spl Solutions
        Gauss_Elimination(matriks); //Gauss Elimination on current Matriks
        double[][] m; 
        

        //Make a m that is a square matriks without augmented
        if (matriks.length > matriks[0].length -1){
           m = new double[matriks.length][matriks[0].length];
        } else {
            m = new double[matriks[0].length-1][matriks[0].length]; 
        }

        for (int i=0; i<matriks.length; i++){
            System.arraycopy(matriks[i], 0, m[i], 0, matriks[i].length);
        }
        
        //Do a Check_Exception for matrix m
        switch (Check_Exception(m)) {
            case 2 -> {
                // Many Solutions
                return parametric_Solution(m,zero_rows_counter(m),zero_col_counter(m));
            }
            case 3 -> {
                //No solutions
                return "System has no Solutions";
            }
            default -> {
                //If there are only single solutions
                int k = 0;
                double[][] hasil = new double[m[0].length-1][1];
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
                return String_Converter(hasil);
            }
        }

    }
    /* ------------------------SPL From Gauss Elimination------------------------ */





    /* ------------------------SPL From Gauss-Jordan-Elimination------------------------ */
    public static String sOut = "";

    public static String SPLGaussJordanFromMatrix(double[][] matrix){
        sOut = "";
        double[][] m = new double[matrix.length][matrix[0].length];
        m = SwapLess0(matrix);
        m = OBE(m);
        SPLSolution(m);
        return sOut;
    }

    // Util Method
    private static int indexOf(int val, int[] arr){
        for (int i=0; i < arr.length; i++){
            if (arr[i] == val) return i;
        }
        return -1;
    }
    
    // Util Method untuk mendapat total 0 sebelum 1 utama di satu baris 
    private static int[] getTotal0(double[][] matrix){
        int[] sum_0 = new int[matrix.length];
        
        // Count total 0 in the front of each row
        for (int i=0; i<matrix.length; i++){
            int count = 0;
            for (int j=0; j<matrix[i].length-1; j++){
                if (matrix[i][j] == 0){
                    count++;
                } else {
                    break;
                }
            }
            sum_0[i] = count;
        }
    
        return sum_0;
    }

    // Util Method
    private static int[] getJumping0(int[] sum_0){
        int[] temp = new int[sum_0.length];
        for (int i=0; i<sum_0.length; i++){
            boolean isInList = false;
            for (int j=0; j<sum_0.length; j++){
                if (i == sum_0[j]){
                    isInList = true;
                    break;
                }
            }
            if (isInList) temp[i] = 1;
        }
        return temp;
    }
    
    // Util Method
    private static int getExtreme(boolean isMax, int[] arr){
        int ext = arr[0];
        if (isMax){
            for (int i=1; i < arr.length; i++){
                if (arr[i] > ext){
                    ext = arr[i];
                }
            }
        } else {
            for (int i=1; i < arr.length; i++){
                if (arr[i] < ext){
                    ext = arr[i];
                }
            }
        }
        return ext;
    }


    // Swap rows method
    private static double[][] SwapLess0(double[][] matrix){
        double[][] m1 = new double[matrix.length][matrix[0].length];
        int[] sum_0 = getTotal0(matrix);
        
        for (int i=0; i < matrix.length; i++){
            int min = getExtreme(false, sum_0);
            int rowVMin = indexOf(min, sum_0);
            for (int j=0; j<matrix[i].length; j++){
                m1[i][j] = matrix[rowVMin][j];
            }
            sum_0[rowVMin] = getExtreme(true, sum_0) + 1;
        }
        return m1;
    }
    
    // OBE method
    private static double[][] OBE(double[][] matrix){
        double[][] m = matrix;
        int[] sum_0 = getTotal0(m);
        int row = 0;
        double temp;
        
        // make first "1 utama"
        for (row = 0; row < m.length; row++) {
            // swap baris dengan jumlah 0 lebih sedikit keatas
            m = SwapLess0(m);
            sum_0 = getTotal0(m);
            
            if (sum_0[row] < m[row].length-1) {
                
                // Ubah bilangan bukan 0 pertama menjadi 1 utama
                temp = m[row][sum_0[row]];
                for (int j=sum_0[row]; j<m[row].length; j++){
                    m[row][j] /= temp;
                }
                
                if (row < m.length){
                    // OBE baris lain dengan cara mengurangkan baris dengan posisi 1 utama yang sama dengan baris ke-{row+1}
                    for (int i=0; i<m.length; i++){
                        if (i != row){
                            if (sum_0[i] <= sum_0[row]){
                                temp = m[i][sum_0[row]];
                                for (int j=sum_0[i]; j<m[row].length; j++){
                                    m[i][j] -= (temp*m[row][j]); 
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return m;
    }

    // Print Solution method
    private static void SPLSolution(double[][] matrix){
        sOut = "";
        int[] sum_0 = getTotal0(matrix);
        double[][] m;
        if (matrix.length > matrix[0].length-1){
            m = new double[matrix.length][matrix[0].length];

            // fill matrix
            for (int i=0; i<matrix.length; i++){
                for (int j=0; j<matrix[i].length; j++){
                    m[i][j] = matrix[i][j];
                }
            }

            // Simplify Matriks
            int idxLast = -1;
            for (int i=0; i<sum_0.length; i++){
                if (sum_0[i] == m[i].length-1 && m[i][m[i].length-1] == 0){
                    break;
                } else {
                    idxLast = idxLast + 1;
                }
            }
            if (idxLast < m.length-1){
                m = simplifyMatriks(m, idxLast);
            }

        } else {
            m = new double[matrix[0].length-1][matrix[0].length]; 

            // fill matrix
            for (int i=0; i<matrix.length; i++){
                for (int j=0; j<matrix[i].length; j++){
                    m[i][j] = matrix[i][j];
                    
                }
            }
        }



        // Cek Jenis Solusi
        if (m[m.length-1-sum_0[0]][m[0].length-2] == 1){
            // Solusi tunggal
            for (int i=0; i<m.length; i++){
                getSingleSolution(m,i);
            }
        } else if (m[m.length-1][m[0].length-2] == 0){
            if (m[m.length-1][m[0].length-1] == 0){
                // Solusi Parametrik
                getParametricSolution(m);
            } else {
                // Solusi Tidak Ada
                sOut = "Solusi Tidak Ada!";
            }
        }
    }

    // mengurangi baris yang kosong
    private static double[][] simplifyMatriks(double[][] matrix, int row){
        double[][] m = new double[row+1][matrix[0].length];
        for (int i=0; i<=row; i++){
            for (int j=0; j<matrix[i].length; j++){
                m[i][j] = matrix[i][j];
            }
        }
        return m;
    }

    // menambahkan keluaran Xi ke variable sOut
    private static void getSingleSolution(double[][] matrix,int row){
        for (int j=0; j<matrix.length; j++){
            if (matrix[row][j] == 1) {
                String s = String.format("X%d = %.3f", j+1, matrix[row][matrix.length]);
                sOut += s + "\n";
                break;
            }
        }
    }
    private static void getParametricSolution(double[][] matrix){
        int[] sum_0 = getTotal0(matrix); // Jumlah 0 perbaris sebelum 1 utama
        int[] jumping_0 = getJumping0(sum_0); // Array yang berisi 0 jika Xi adalah bilangan real
        int idxLast = -1; // index terakhir dimana nilai matriks tanpa augmented 1 baris berisi 0 semua
        int temp = 112; // temp ascii mulai dari p
        int temp1 = 112; // temp ascii mulai dari p

        // Loop untuk mencari index terakhir tempat nilai matriks tanpa augmented berisi 0 semua di 1 baris
        for (int i=0; i<sum_0.length; i++){
            if (sum_0[i] < matrix[i].length-1){
                idxLast++;
            } else {
                break;
            }
        }

        // Keluaran Misal
        sOut += "Misal: \n";
        // Jika ada Xi yang berupa bilangan real
        for (int i = 0; i < sum_0[idxLast]; i++){
            if (jumping_0[i] == 0){
                sOut += String.format("X%d = X%d\n", i+1, i+1);
            }
        }
        // Jika ada Xi yang perlu dimisalkan
        for (int j = sum_0[idxLast] + 1; j < matrix.length; j++){
            String s = String.format("X%d = ", j+1);
            sOut += s + (char) temp1 + "\n";
            temp1++;
        }

        // Keluaran Maka
        sOut += "Maka: \n";
        // Nilai masing-masing Xi yang tidak dimisalkan
        for (int i=0; i<=idxLast; i++){
            String s;
            boolean isKosong;

            if (matrix[i][matrix[i].length-1] == 0){
                // Ketika nilai b (Ax = b) pada baris i adalah 0
                s = String.format("X%d = ", sum_0[i]+1);
                isKosong = true;
            } else {
                // Ketika nilai b (Ax = b) pada baris i tidak 0
                s = String.format("X%d = %.3f", sum_0[i]+1, matrix[i][matrix.length]);
                isKosong = false;
            }
            sOut += s;

            // Keluaran untuk semua parametrik
            for (int j=sum_0[idxLast] + 1; j<matrix.length; j++){
                s="";
                if (matrix[i][j] < 0){
                    // Ketika nilai parametrik negatif
                    if (isKosong){
                        // Ketika nilai b (Ax = b) pada baris i adalah 0
                        if (-1*matrix[i][j] == 1){
                            // Ketika nilai parametrik adalah 1
                            s = String.format(" ");
                        } else {
                            // Ketika nilai parametrik bukan 1
                            s = String.format("%.3f ", (-1*matrix[i][j]));
                        }
                        isKosong = false;
                    } else {
                        // Ketika nilai b (Ax = b) pada baris i bukan 0
                        if (-1*matrix[i][j] == 1){
                            // Ketika nilai parametrik adalah 1
                            s = String.format(" + ", (-1*matrix[i][j]));
                        } else {
                            // Ketika nilai parametrik bukan 1
                            s = String.format(" + %.3f ", (-1*matrix[i][j]));
                        }
                    }
                    s += String.valueOf((char)(temp-1+j-sum_0[idxLast]));
                } else if (matrix[i][j] > 0){
                    // Ketika nilai parametrik positif
                    if (isKosong){
                        // Ketika nilai b (Ax = b) pada baris i adalah 0
                        if (matrix[i][j] == 1){
                            // Ketika nilai parametrik adalah 1
                            s = String.format("- ");
                        } else {
                            // Ketika nilai parametrik bukan 1
                            s = String.format("-%.3f ", matrix[i][j]);
                        }
                        isKosong = false;
                    } else {
                        // Ketika nilai b (Ax = b) pada baris i bukan 0
                        if (matrix[i][j] == 1){
                            // Ketika nilai parametrik adalah 1
                            s = String.format(" - ");
                        } else {
                            // Ketika nilai parametrik bukan 1
                            s = String.format(" - %.3f ", matrix[i][j]);
                        }
                    }
                    s += String.valueOf((char)(temp-1+j-sum_0[idxLast]));
                }
                sOut += s;
            }
            sOut += "\n";
        }
    }
    /* ------------------------SPL From Gauss-Jordan-Elimination------------------------ */
}