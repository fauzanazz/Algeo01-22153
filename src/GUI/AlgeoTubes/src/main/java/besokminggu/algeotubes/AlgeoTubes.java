/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package besokminggu.algeotubes;
import javax.swing.*;
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
    static int x;
    static String pathfile;
    
    static double[][] matriksinput;
    static String output;
    
    public static void main(String[] args) {
        new MainMenu().setVisible(true);
    }
}
