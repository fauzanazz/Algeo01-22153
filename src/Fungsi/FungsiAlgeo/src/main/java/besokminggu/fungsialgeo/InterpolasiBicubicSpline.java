/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besokminggu.fungsialgeo;

/**
 *
 * @author DELL
 */
public class InterpolasiBicubicSpline {
    
    public static String matrixOutput (double[][] matriks){
        String output = "";
        for(int i=0; i<matriks.length;i++){
            for (int j=0; j<matriks[0].length; j++){
                output += String.format("%d ",(int)matriks[i][j]);
            }
            output += String.format("\n");
        }
        return output;
    }
    
    static double[][] matriksInput = new double[16][1];
    static double[][] matriksX = new double[16][16];
    static double[][] matriksXInversed = new double[16][16];
    static double[][] matriksCoefficient = new double[16][1];
    
    public static void setMatrix (double[][] input){
        int index = 0;
        //Set input as matriksinput
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                matriksInput[index][0] = input[i][j];
                index++;
            }
        }
        for (int i = 0; i < 16; i++) {
            konstanta(i);
        }
        matriksXInversed = Inverse.get_Inverse_Matriks_fromIdentity(matriksX);
        matriksCoefficient = SPL.perkalian_Matriks(matriksXInversed, matriksInput);
    }
    
    public static double[][] constant_inverted(){ //used for bicubic implementation on bonus.
		for(int i = 0; i < 16; i++){
			konstanta(i);
		}
		matriksXInversed = Inverse.get_Inverse_Matriks_fromIdentity(matriksX);
		return matriksX;
	}
    
    public static void konstanta(int i){
        int x,y;
        int loop = i%4;
        switch (loop) {
            case 0:
                x = 0;
                y = 0;
                valueTurunanBerarah(x,y,i);
                break;
            case 1:
                x = 1;
                y = 0;
                valueTurunanBerarah(x,y,i);
                break;
            case 2:
                x = 0;
                y = 1;
                valueTurunanBerarah(x,y,i);
                break;
            default:
                x = 1;
                y = 1;
                valueTurunanBerarah(x,y,i);
                break;
        }
    }

    public static void valueTurunanBerarah(int x, int y, int i){
        double value;
        int idx = 0;
        for(int k=0; k<4; k++){
            for(int l=0; l<4; l++){
                if (i < 4) {
                    value = (Math.pow(x,l) * Math.pow(y,k));
                } else if (i < 8) {
                    value = l * (Math.pow(x,l-1) * Math.pow(y,k));
                } else if (i < 12) {
                    value = k * (Math.pow(x,l) * Math.pow(y,k-1));
                } else {
                    value = k*l*(Math.pow(x,l-1) * Math.pow(y,k-1));
                }
                matriksX[i][idx] = (int) value;
                idx++;
            }
        }
    }
    
    public static double GetAValue(int i, int j){
        return matriksCoefficient[i+j*4][0];
    }
    
    public static String getBicubicOutput(double x, double y){
        String sout = "";
        double sum = 0;
        for(int k=0; k<4; k++){
            for(int l=0; l<4; l++){
                sum += GetAValue(l,k)*Math.pow(x,l)*Math.pow(y,k);
            }
        }
        sout += String.format("f(%f,%f) = %f", x,y, sum);
        return sout;
    }

    public static String OutputMX(){
        String output;
        output = matrixOutput(matriksX);
        return output;
    }
    
    public static String OutputNilai(){
        String output;
        matriksXInversed = Inverse.get_Inverse_Matriks_fromIdentity(matriksX);
        output = matrixOutput(matriksXInversed);
        return output;
    }
}