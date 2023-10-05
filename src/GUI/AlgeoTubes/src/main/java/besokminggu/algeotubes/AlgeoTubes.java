/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package besokminggu.algeotubes;
import java.io.File;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author DELL
 */


public class AlgeoTubes extends JFrame {

    
    static int SPLEliminasiGauss = 0;
    static int SPLELiminasiGaussJordan = 0;
    static int SPLMatriksBalikan = 0;
    static int SPLCramer = 0;
    static int DETDeterminanNxN = 0;
    static int DETDeterminandenganKofaktor = 0;
    static int DETDeterminanMatriksSegitiga = 0;
    static int INVAdjoin = 0;
    static int INVBalikan = 0;
    static int InterpolasiPolinom = 0;
    static int InterpolasiBicubicSpline = 0;
    static int RegresiLinearBerganda = 0;
    static int ImplementasiInterpolasiBicubicSpline = 0;
    static int n;
    static int m;
    static double x;
    static double y;
    static String pathfile;
    static String hasil;
    
    static double[][] matriksinput;
    static String output;
    
    public static void main(String[] args) {
        new MainMenu().setVisible(true);
    }
    
    public static void saveFile(String output){
        // Create a new JFileChooser object.
        JFileChooser fileChooser = new JFileChooser();

        // Set the fileSelectionMode property of the JFileChooser object to FILES_ONLY.
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Call the showOpenDialog() method on the JFileChooser object.
        int result = fileChooser.showOpenDialog(null);

        // If the user selects a file, get the selected file path using the getSelectedFile() method.
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            // Create a new FileWriter object with the selected file path as the constructor parameter.
            try (FileWriter fileWriter = new FileWriter(filePath)) {

                // Write the String variable to the FileWriter object using the write() method.
                fileWriter.write(output);

            } catch (IOException e) {
            }
        }
    }
}
