/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besokminggu.fungsialgeo;

/**
 *
 * @author Dave
 */
import java.awt.image.BufferedImage; //Used for make a bigger image
import java.io.File; // used for input a file
import java.io.IOException; // Handle Image Exception
import javax.imageio.ImageIO; //also for reading and writing images in JPEG or PNG
import java.awt.*;
import javax.swing.*;

public class Implementasi_BicubicSpline {
    
    public static void displayImage(Image image) throws IOException {
        BufferedImage displayedImage = (BufferedImage) image;
        JFrame frame = new JFrame("Scaled Image");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Handle User Exit

        // Load the scaled image
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon);
        label.setPreferredSize(new Dimension(displayedImage.getWidth(), displayedImage.getHeight()));
        JScrollPane scrollPane = new JScrollPane(label); // Wrap the JLabel inside a JScrollPane
        scrollPane.setPreferredSize(new Dimension(displayedImage.getWidth(), displayedImage.getHeight()));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Increase scroll speed
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16); // Increase scroll speed
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static BufferedImage input_image(String path) throws IOException{// Input image from a path, throw error when exception occured
        BufferedImage input_Image = ImageIO.read(new File(path));
        return input_Image;
    }
    static BufferedImage after_Image;
    
    public static void saveImage (String outputPath) throws IOException{
        File outputFile = new File(outputPath);
        String format = "JPG";
        ImageIO.write(after_Image, format, outputFile);
    }
    
    public static void ImageScaler(String input_Path, double scale) throws IOException{ // Main function for Scaling image
        BufferedImage originalImage = input_image(input_Path);
        double after_width = originalImage.getWidth() * scale;    
        double after_height = originalImage.getHeight() * scale;
        after_Image = new BufferedImage((int) after_width, (int) after_height, BufferedImage.TYPE_INT_RGB); //Initiate the new Image.
        //Bicubic Spline Interpolation Calculation
        for(int x = 0; x < after_width;x++){
            for(int y = 0; y < after_height;y++){
                double previous_x = x / scale;
                double previous_y = y / scale; 
                int InterpolatedResult = BicubicInterpolation(originalImage,previous_x, previous_y);
                after_Image.setRGB(x, y, InterpolatedResult);
            }
        }
        displayImage(after_Image); //To show image.
    }   
    public static double[][] matriksI(BufferedImage image, int current_x, int current_y) { // a Method to get the I matriks, y = D I, I is the value of current pixel position
        double[][] I_matrix = new double[16][1];
        int counter = 0;
    
        for (int j = -1; j < 3; j++) {
            for (int i = -1; i < 3; i++) {
                int px = current_x + i;
                int py = current_y + j;
    
                if (px >= 0 && px < image.getWidth() && py >= 0 && py < image.getHeight()) {
                    // Pixel coordinates are within bounds
                    I_matrix[counter][0] = (double) (image.getRGB(px, py) & 0xFF) / 255.0; //get the normalized value
                } else {
                    // Pixel coordinates are out of bounds, set to 0
                    I_matrix[counter][0] = 0.0;
                }
                counter++;
            }
        }
        return I_matrix;
    }   

    public static void state(int n, int[] x, int[] y){ // a methode to get the index for f(x,y), fx(x,y), fy(x,y), fxy(x,y)
        switch(n){
            case 0 -> {
                x[0] = 0;
                y[0] = 0;
            }
            case 1 -> {
                x[0] = 1;
                y[0] = 0;
            }
            case 2 -> {
                x[0] = 0;
                y[0] = 1;
            }
            default -> {
                x[0] = 1;
                y[0] = 1;
            }
        }
    }
    public static double[][] matriksD(){ //a method to get the D matriks y = D I
        double[][] matriks_D = new double[16][16];
        int idx = 0; // D matrix column index counter
        int[] x_counter = new int[1]; // Note: List are used as a passable object
        int[] y_counter= new int[1];

        //Calculation
        for(int D_row = 0; D_row < 16;D_row++){
            state(D_row % 4, x_counter, y_counter);
            for(int Iy= -1; Iy < 3; Iy++){
                for(int Ix = -1; Ix < 3; Ix++){
                    if(D_row < 4){
                        if(x_counter[0] == Ix && y_counter[0] == Iy){
                            matriks_D[D_row][idx] = 1;
                        }
                        else{
                            matriks_D[D_row][idx] = 0;
                        }
    
                    }
                    else if(D_row < 8){
                        if(x_counter[0] + 1 == Ix && y_counter[0] == Iy){
                            matriks_D[D_row][idx] = 0.5;
                        }
                        else if(x_counter[0] - 1 == Ix && y_counter[0] == Iy){
                            matriks_D[D_row][idx] = -0.5;
                        }
                        else{
                            matriks_D[D_row][idx] = 0;
                        }
                        
                    }
                    else if(D_row < 12){
                        if(x_counter[0] == Ix && y_counter[0] + 1 == Iy){
                            matriks_D[D_row][idx] = 0.5;
                        }
                        else if(x_counter[0] == Ix && y_counter[0] - 1 == Iy){
                            matriks_D[D_row][idx] = -0.5;
                        }
                        else{
                            matriks_D[D_row][idx] = 0;
                        }
    
                    }
                    else{
                        if(x_counter[0] + 1 == Ix && y_counter[0] + 1 == Iy){
                            matriks_D[D_row][idx] = 0.25;
                        }
                        else if(x_counter[0] - 1 == Ix && y_counter[0]== Iy){
                            matriks_D[D_row][idx] = -0.25;
                        }
                        else if(x_counter[0] == Ix && y_counter[0] - 1 == Iy){
                            matriks_D[D_row][idx] = -0.25;
                        }
                        else if(x_counter[0] == Ix && y_counter[0] == Iy){
                            matriks_D[D_row][idx] = -0.25;
                        }
                        else{
                            matriks_D[D_row][idx] = 0;
                        }
                    }
                    idx++;
                }
            }
            idx = 0;
        }
        return matriks_D;
    }
    public static double GetAValue(double[][] matriksA, int i, int j){ // get current Aij value, y = A X
        return matriksA[i+j*4][0];
    }
    
    public static double getBicubicOutput(double[][] matriksA,double x, double y){ // sum all of the Aij matriks
        double sum = 0;
        for(int k=0; k<4; k++){
            for(int l=0; l<4; l++){
                sum += GetAValue(matriksA,l,k)*Math.pow(x,l)*Math.pow(y,k);
            }
        }
        return sum;
    }

    private static int BicubicInterpolation(BufferedImage image, double x, double y) { // A method to calculate the Bicubic function
        // Make the coordinate x,y into integer 
        int startX = (int) x;
        int startY = (int) y;

        //Use the existing Bicubic interpolation
        double[][] matriks_temp;
        matriks_temp = SPL.perkalian_Matriks(matriksD(),matriksI(image, startX, startY));
        matriks_temp = SPL.perkalian_Matriks(InterpolasiBicubicSpline.constant_inverted(),matriks_temp);
        double interpolatedValue = getBicubicOutput(matriks_temp, x - (int)x, y - (int)y); //then get the value of a. x - (int) x used to get the diffrent of x and (int) x so that it will not overflow;

        //Clamp the pixels to 0-1 to prevent overflow
        if(interpolatedValue >= 1){
            interpolatedValue = 1;
        }
        if(interpolatedValue < 0){
            interpolatedValue = 0;
        }

        // Scale the interpolated value to the range [0, 255] and convert to RGB
        // Assumption : The only image used are a grey image
        int color = (int) (interpolatedValue * 255.0);
        color = (color << 8) | (color << 16) | color;

        return color;

        }
}
