public class SPLGaussJordan{
    public static void main(String[] args){
        SPLGaussJordanFromMatrix(TextToMatriks.readMatrixFromFile("Algeo/filename.txt"));
    }

    public static void SPLGaussJordanFromMatrix(float[][] matrix){
        double[][] m = new double[matrix.length][matrix[0].length];
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                m[i][j] = matrix[i][j];
            }
        }
        m = SwapLess0(m);
        m = OBE(m);
        SPLSolution(m);
    }

    // Util Method
    private static int indexOf(int val, int[] arr){
        for (int i=0; i < arr.length; i++){
            if (arr[i] == val) return i;
        }
        return -1;
    }
    
    // Util Method
    private static int[] getTotal0(double[][] matrix){
        int[] sum_0 = new int[matrix.length];
        
        // Count total 0 in the front of each row
        for (int i=0; i<matrix.length; i++){
            int count = 0;
            for (int j=0; j<matrix[i].length; j++){
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
            // for (int i = 0; i < sum_0.length; i++){
            //     System.out.println(sum_0[i]);
            // }
            // System.out.println();
            // System.out.println();
            
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
        // System.out.println();
        // TextToMatriks.printMatrix(m);
        // System.out.println();

        return m;
    }

    // Print Solution method
    private static void SPLSolution(double[][] matrix){
        int[] sum_0 = getTotal0(matrix);
        double[][] m;
        if (matrix.length > matrix[0].length){
           m = new double[matrix.length][matrix[0].length];
        } else {
            m = new double[matrix[0].length-1][matrix[0].length]; 
        }

        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                m[i][j] = matrix[i][j];
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
                System.out.println("Solusi Tidak Ada!");
            }
        }
    }

    private static void getSingleSolution(double[][] matrix,int row){
        for (int j=0; j<matrix.length; j++){
            if (matrix[row][j] == 1) {
                String s = String.format("X%d = %f", j+1,matrix[row][matrix.length]);
                System.out.println(s);
                break;
            }
        }
    }
    private static void getParametricSolution(double[][] matrix){
        int[] sum_0 = getTotal0(matrix);
        int idxLast = -1;
        int temp = 112;
        for (int i=0; i<sum_0.length; i++){
            if (sum_0[i] < matrix.length){
                idxLast = idxLast + 1;
            } else {
                break;
            }
        }

        System.out.println("Misal:");
        for (int j = sum_0[idxLast] + 1; j < matrix.length; j++){
            String s = String.format("X%d = ", j+1);
            System.out.print(s);    
            System.out.println((char) temp);
            temp++;
        }
        System.out.println("Maka:");
        for (int i=0; i<=idxLast; i++){
            String s = String.format("X%d = %f", sum_0[i]+1, matrix[i][matrix.length]);
            boolean isKosong;
            if (matrix[i][matrix.length] == 0){
                s = String.format("X%d = ", sum_0[i]+1);
                isKosong = true;
            } else {
                s = String.format("X%d = %f", sum_0[i]+1, matrix[i][matrix.length]);
                isKosong = false;
            }
            System.out.print(s);
            for (int j=sum_0[idxLast] + 1; j<matrix.length; j++){
                if (matrix[i][j] < 0){
                    if (isKosong){
                        if (-1*matrix[i][j] == 1){
                            s = String.format(" ");
                        } else {
                            s = String.format("%f ", (-1*matrix[i][j]));
                        }
                        isKosong = false;
                    } else {
                        if (-1*matrix[i][j] == 1){
                            s = String.format(" + ", (-1*matrix[i][j]));
                        } else {
                            s = String.format(" + %f ", (-1*matrix[i][j]));
                        }
                    }
                } else {
                    if (isKosong){
                        if (matrix[i][j] == 1){
                            s = String.format("- ");
                        } else {
                            s = String.format("-%f ", matrix[i][j]);
                        }
                        isKosong = false;
                    } else {
                        if (matrix[i][j] == 1){
                            s = String.format(" - ");
                        } else {
                            s = String.format(" - %f ", matrix[i][j]);
                        }
                    }
                }
                System.out.print(s);
                System.out.println((char)(temp-2+j-sum_0[idxLast]));
            }
        }
    }
}