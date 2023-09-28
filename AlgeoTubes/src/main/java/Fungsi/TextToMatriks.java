package AlgeoTubes.src.main.java.Fungsi;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class TextToMatriks {

    private static double[][] getMatrixFromText(String text){
        // Get row & column
        int m = 0, n = 1;
        for (char x: text.toCharArray()) {
            if (x == ' ' && m == 0) n++;
            if (x == '\n') m++;
        }
        
        // Create Empty Matrix & initiate variable
        double[][] matrix = new double[m][n];
        int i = 0, j = 0;
        String temp = "";

        // Loop: Fill The Matrix
        for (char x: text.toCharArray()){
            if (x != ' ' && x != '\n') {
                temp = temp + String.valueOf(x);
            } else {
                if (temp.contains("/")){
                    String temp1 = "";
                    int k;
                    for(k = 0; k<temp.indexOf('/', 0); k++){
                        temp1 += temp.charAt(k);
                    }
                    // System.out.println(temp1);
                    matrix[i][j] = Double.parseDouble(temp1);
                    temp1 = "";
                    for(k= k+1; k<temp.length(); k++){
                        temp1 += temp.charAt(k);
                    }
                    // System.out.println(temp1);
                    matrix[i][j] /= Double.parseDouble(temp1);

                } else {
                    matrix[i][j] = Double.parseDouble(temp);
                }
                j++;
                temp = "";
                if (j == n) {
                    j = 0;
                    i++;
                }
            }
        }

        return matrix;
    }

    public static void printMatrix(double[][] matrix){
        int i,j;
        for (i=0; i<matrix.length; i++) {
            for (j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] readMatrixFromFile(String path) {
        String text = "";
        try {
            File f = new File(path);
            Scanner line = new Scanner(f);
            while (line.hasNextLine()) {
                text = text + line.nextLine() + "\n";
            }
            line.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }

        return getMatrixFromText(text);
    }
}
