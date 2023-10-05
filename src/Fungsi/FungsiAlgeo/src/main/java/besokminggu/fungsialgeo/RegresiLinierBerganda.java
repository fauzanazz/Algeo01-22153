package besokminggu.fungsialgeo;

public class RegresiLinierBerganda {

    public static String RLB(double[][] matrix){
        double[] x = new double[matrix[0].length-1];
        for (int i=0; i<matrix[0].length-1; i++){
            x[i] = matrix[matrix.length-1][i];
        }
        double[][] m = new double[matrix.length-1][matrix[0].length];
        for (int i=0; i<matrix.length-1; i++){
            for (int j=0; j<matrix[0].length; j++){
                m[i][j] = matrix[i][j];
            }
        }
        m = NEE(matrix);
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
        sOut += "\n\nUntuk menaksir nilai";
        for (int i=0; i<matrix[0].length-1; i++){
            sOut += String.format(" X%d = %.3f", i+1, x[i]);
            if (i != matrix[0].length-2){
                sOut += ",";
            }
        }
        sOut += " akan didapat nilai Y = ";
        double result = Double.parseDouble(temp_value[0].strip());
        for (int i=1; i<temp_value.length; i++){
            result += Double.parseDouble(temp_value[i].strip())*x[i-1];
        }
        sOut += String.format("%.3f", result);
        
        return sOut;
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
                    temp += (matrix[k][matrix[i].length-1]);
                }
            } else {
                for (int k=0; k<n; k++){
                    temp += (matrix[k][i-1]*matrix[k][matrix[i].length-1]);
                }
            }
            m[i][matrix[0].length] = temp;
        }
        return m;
    }
}
