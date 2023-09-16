import Matriks_Balikan.*;

public class Eliminasi_Gauss {
    static int UNIDENTIFIED = -1;
    private static void PrintMatrix(float[][] matrix){ // print the matrix
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]+ " ");
            }
           System.out.println(); 
        }

    }
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

    
    private static void Swapping_Operation(float[][] matrix, int row_will_be_changed, int row_who_changed){ // swap between the row_will_be_changed and row_who_changed
        float[][] temp = new float[1][matrix[0].length];
        for(int i = 0; i < matrix[0].length;i++){
             temp[0][i] = matrix[row_who_changed][i];
             matrix[row_who_changed][i] = matrix[row_will_be_changed][i];
             matrix[row_will_be_changed][i] = temp[0][i];
        }
    }

    private static int find_index_value_1(float[][] matrix, int row_order){
        for(int i = 0; i< matrix.length; i++){
            if(matrix[i][row_order] == 1){
                return i;
            }
        }
        return UNIDENTIFIED;
    }

    public static void Gauss_Elimination(float[][] matriks){
        for(int i = 0; i < matriks.length; i++){
            if(i == 0){
                if(find_index_value_1(matriks, i) != UNIDENTIFIED){
                    Swapping_Operation(matriks, i, find_index_value_1(matriks, i));
                }
            }
            Multipy_Operation(matriks, i, i);
            for(int j = i+1; j < matriks[i].length-1;j++){
                Reduce_Operation(matriks,j,i,i);
            }
        }

        negative_0_handler(matriks);
        PrintMatrix(matriks);
    }

    private static int Check_Exception(float[][] matriks){
        int accept = 0;
        for(int i = matriks.length-1; i > -1; i--){
            if(i == matriks.length-1){
                if(matriks[matriks.length-1][matriks[i].length-1] == 0){
                    accept = 2;
                    for(int k = 0; k < matriks.length-1; k++){
                        if(matriks[i][k] != 0){
                            accept = 1;
                        }
                    }
                }
                else{
                    accept = 3;
                    for(int k = 0; k < matriks[i].length-1; k++){
                        if(matriks[i][k] != 0){
                            accept = 1;
                            break;
                        }
                    }
                }
                
            }
        
        }
        return accept;
    }

    private static void calculation(float[][] matriks_in, float[][] matriks_out){
        // for(int i = matriks_in.length-1; i > 0; i--){
        //     if(i == matriks_in.length-1){
        //         matriks_out[0][i-1] = matriks_in[i][matriks_in[i].length-1];
        //     }
        //     else{
        //         int hasil = 0;
        //         for(int j = matriks_in[i].length-1; j > -1; j--){
        //             if(matriks_in[i][j] == 1){
        //                 hasil+= matriks_in[i][j];
        //             }
        //             matriks_out[0][i-1] = matriks_in[i][matriks_in[i].length-1] -hasil;
        //         }
        //     }
        // }

        for(int i = matriks_in.length-1; i > -1; i--){
            int temp = 0;
            for(int j = matriks_in.length; j > i ; j--){
                temp += matriks_in[i][j-1] * matriks_out[0][j-1];
            }
            matriks_out[0][i] = (1/matriks_in[i][i]) * (matriks_in[i][matriks_in[i].length-1] - temp);
        }
    }

    public static void SPL_From_Gauss(float[][] matriks){
        float[][] matriks_spl = new float[1][matriks.length];
        for(int i = 0; i < matriks.length; i++){ //Iteration for next calculation
           matriks_spl[0][i] = 0;
        }
        if(Check_Exception(matriks) == 3){
            System.err.println("The SPL has no Solution");
        }
        else if(Check_Exception(matriks) == 2){
            System.err.println("The SPL has so many Soltions");
        }
        else{
            calculation(matriks, matriks_spl);
            PrintMatrix(matriks_spl);
        }
    }

    public static void main(String[] args){
        float[][] matriks = {{9,3,4,7},{4,3,4,8},{1,1,1,3}};
        Gauss_Elimination(matriks);
        SPL_From_Gauss(matriks);
    }

}
