
public class Determinan_Kofaktor{
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
    }
    static double determinan_kofaktor(double[][] matriks){
        exception(matriks); // Check Any Exception
        if(matriks.length == 0){
            return 0;
        }
        if(matriks.length == 1){
            return matriks[0][0];
        }
        if(matriks[0].length == 2){
            return (matriks[0][0] * matriks[1][1]) - (matriks[0][1] *matriks[1][0]); //Basic Determinan 2x2 calculation
        }

        double result = 0;
        int l;
        for(int i = 0; i < matriks.length; i++){
            double[][] temp = new double[matriks.length-1][matriks[i].length-1]; //Make the shorter version of current Matriks
            for(int j = 0; j < temp.length ; j++){
                l = 0;
                for(int k = 0; k < matriks[i].length;k++){ //Copy the value of matriks into the shorter version
                    if(k != i){
                        temp[j][l] = matriks[j+1][k];
                        l++;
                    }
                }

            }
            result += matriks[0][i] * Math.pow(-1, 2+i)* determinan_kofaktor(temp); // Do Recursive until become 2x2 matriks
        }

        return result;
    }

    
}