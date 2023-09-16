import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class TextToMatriks {
    public static int[][] getMatrixFromText(String text){
        // Get row & column
        int m = 0, n = 1;
        for (char x: text.toCharArray()) {
            if (x == ' ' && m == 0) n++;
            if (x == '\n') m++;
        }
        
        // Create Empty Matrix & initiate variable
        int[][] matrix = new int[m][n];
        int i = 0, j = 0;
        String temp = "";

        // Loop: Fill The Matrix
        for (char x: text.toCharArray()){
            if (x != ' ' && x != '\n') {
                temp = temp + String.valueOf(x);
            } else {
                matrix[i][j] = Integer.parseInt(temp);
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

    public static void printMatrix(int[][] matrix, int m, int n){
        int i,j;
        for (i=0; i<m; i++) {
            for (j=0; j<n; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // public static void main(String[] args) {

    //     String text = "1 2 3 4\n5 6 7 8\n9 10 11 12\n13 14 15 16";
	// 	// int[][] matrix = { { 3, 2, 1, 7 },
	// 	// 			 { 9, 11, 5, 4 },
	// 	// 			 { 6, 0, 13, 17 },
	// 	// 			 { 7, 21, 14, 15 } };
	// 	// printMatrix(matrix, 4, 4);
    //     int[][] matriks = getMatrixFromText(text);
	// 	printMatrix(matriks, 4, 4);
	// }



    public static void main(String[] args) {
        String text = "";
        try {
            File f = new File("filename.txt");
            Scanner line = new Scanner(f);
            while (line.hasNextLine()) {
                text = text + line.nextLine() + "\n";
            }
            line.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }

        int[][] matriks = getMatrixFromText(text);
        printMatrix(matriks, 4, 4);
    }
}
