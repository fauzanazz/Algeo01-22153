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
    
    
    private static double a00, a01, a02, a03;
    private static double a10, a11, a12, a13;
    private static double a20, a21, a22, a23;
    private static double a30, a31, a32, a33;

    public static void updateCoefficients (double[][] p) {
            a00 = p[1][1];
            a01 = -.5*p[1][0] + .5*p[1][2];
            a02 = p[1][0] - 2.5*p[1][1] + 2*p[1][2] - .5*p[1][3];
            a03 = -.5*p[1][0] + 1.5*p[1][1] - 1.5*p[1][2] + .5*p[1][3];
            a10 = -.5*p[0][1] + .5*p[2][1];
            a11 = .25*p[0][0] - .25*p[0][2] - .25*p[2][0] + .25*p[2][2];
            a12 = -.5*p[0][0] + 1.25*p[0][1] - p[0][2] + .25*p[0][3] + .5*p[2][0] - 1.25*p[2][1] + p[2][2] - .25*p[2][3];
            a13 = .25*p[0][0] - .75*p[0][1] + .75*p[0][2] - .25*p[0][3] - .25*p[2][0] + .75*p[2][1] - .75*p[2][2] + .25*p[2][3];
            a20 = p[0][1] - 2.5*p[1][1] + 2*p[2][1] - .5*p[3][1];
            a21 = -.5*p[0][0] + .5*p[0][2] + 1.25*p[1][0] - 1.25*p[1][2] - p[2][0] + p[2][2] + .25*p[3][0] - .25*p[3][2];
            a22 = p[0][0] - 2.5*p[0][1] + 2*p[0][2] - .5*p[0][3] - 2.5*p[1][0] + 6.25*p[1][1] - 5*p[1][2] + 1.25*p[1][3] + 2*p[2][0] - 5*p[2][1] + 4*p[2][2] - p[2][3] - .5*p[3][0] + 1.25*p[3][1] - p[3][2] + .25*p[3][3];
            a23 = -.5*p[0][0] + 1.5*p[0][1] - 1.5*p[0][2] + .5*p[0][3] + 1.25*p[1][0] - 3.75*p[1][1] + 3.75*p[1][2] - 1.25*p[1][3] - p[2][0] + 3*p[2][1] - 3*p[2][2] + p[2][3] + .25*p[3][0] - .75*p[3][1] + .75*p[3][2] - .25*p[3][3];
            a30 = -.5*p[0][1] + 1.5*p[1][1] - 1.5*p[2][1] + .5*p[3][1];
            a31 = .25*p[0][0] - .25*p[0][2] - .75*p[1][0] + .75*p[1][2] + .75*p[2][0] - .75*p[2][2] - .25*p[3][0] + .25*p[3][2];
            a32 = -.5*p[0][0] + 1.25*p[0][1] - p[0][2] + .25*p[0][3] + 1.5*p[1][0] - 3.75*p[1][1] + 3*p[1][2] - .75*p[1][3] - 1.5*p[2][0] + 3.75*p[2][1] - 3*p[2][2] + .75*p[2][3] + .5*p[3][0] - 1.25*p[3][1] + p[3][2] - .25*p[3][3];
            a33 = .25*p[0][0] - .75*p[0][1] + .75*p[0][2] - .25*p[0][3] - .75*p[1][0] + 2.25*p[1][1] - 2.25*p[1][2] + .75*p[1][3] + .75*p[2][0] - 2.25*p[2][1] + 2.25*p[2][2] - .75*p[2][3] - .25*p[3][0] + .75*p[3][1] - .75*p[3][2] + .25*p[3][3];
    }

    public static double getValue (double x, double y) {
            double x2 = x * x;
            double x3 = x2 * x;
            double y2 = y * y;
            double y3 = y2 * y;

            return (a00 + a01 * y + a02 * y2 + a03 * y3) +
                   (a10 + a11 * y + a12 * y2 + a13 * y3) * x +
                   (a20 + a21 * y + a22 * y2 + a23 * y3) * x2 +
                   (a30 + a31 * y + a32 * y2 + a33 * y3) * x3;
    }
}