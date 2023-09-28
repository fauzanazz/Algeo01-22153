package AlgeoTubes.src.main.java.Fungsi;

public class RegresiLinierBerganda {
    public static void main (String[] args){
        RLB(TextToMatriks.readMatrixFromFile("Algeo/filename.txt"));
    }

    public static void RLB(double[][] matrix){
        double[][] m = NEE(matrix);
        String sOut = "Didapat nilai masing-masing b adalah sebagai berikut:\n"; 
        String temp = "";
        String[] temp_value = new String[m.length];
        boolean is_X = false, is_EQ = false;
        int temp_idx = 0;

        // TextToMatriks.printMatrix(m);
        String s = SPL.SPLGaussJordanFromMatrix(m);

        for (char cc: s.toCharArray()){
            if (is_X){
                cc = (char)(cc-1);
                is_X = false;
            }
            if (cc == 'X'){
                cc = 'b';
                is_X = true;
            }
            if (cc == '\n'){
                temp_value[temp_idx] = temp;
                temp = "";
                temp_idx++;
                is_EQ = false;
            }
            if (is_EQ) temp += cc;
            if (cc == '=') is_EQ = true;
            sOut += cc;
        }
        sOut += "\nMaka persamaan regresi linier berganda-nya adalah\nY =";
        sOut += temp_value[0] + " +";
        for (int i=1; i<temp_value.length; i++){
            sOut += temp_value[i] + " X" + String.valueOf(i);
            if (i < temp_value.length-1) sOut += " +";
        }

        System.out.println(sOut);
    }
    
    private static double[][] NEE(double[][] matrix){
        double[][] m = new double[matrix[0].length][matrix[0].length+1];
        int n = matrix.length;
        for (int i=0; i<matrix[0].length; i++){
            for (int j=0; j<matrix[0].length; j++){
                if (i==0){
                    if (j==0){
                        m[i][j] = n;
                    } else {
                        double temp = 0;
                        for (int k=0; k<n; k++){
                            temp += matrix[k][j-1];
                        }
                        m[i][j] = temp;
                    }
                } else {
                    if (j==0){
                        double temp = 0;
                        for (int k=0; k<n; k++){
                            temp += matrix[k][i-1];
                        }
                        m[i][j] = temp;
                    } else {
                        double temp = 0;
                        for (int k=0; k<n; k++){
                            temp += (matrix[k][j-1]*matrix[k][i-1]);
                        }
                        m[i][j] = temp;
                    }
                    
                }
            }
        }
        for (int i=0; i<m.length; i++){
            double temp = 0;
            if (i==0){
                for (int k=0; k<n; k++){
                    temp += (matrix[k][3]);
                }
            } else {
                for (int k=0; k<n; k++){
                    temp += (matrix[k][i-1]*matrix[k][3]);
                }
            }
            m[i][4] = temp;
        }
        return m;
    }
}
