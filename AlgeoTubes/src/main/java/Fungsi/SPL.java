/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fungsi;

/**
 *
 * @author DELL
 */

public class SPL {
    public float SPLCramer(float[][] matrix, int matrix_size, int penyelesaian) {
        // Cari Determinan dari matrix ini.
        float det = Fungsi.Determinan.determinangauss(matrix, matrix_size);

        // Jika determinan tidak ada, maka SPL tidak memiliki solusi.
        if (det == 0) {
            return 0;
        }

        // Ubah matrix menjadi matrix cramer
        for (int i = 0 ; i < matrix_size ; i++) {
            matrix[i][penyelesaian-1] = matrix[i][matrix_size-1];
        }

        // Cari Determinan matrix cramer
        float det_cramer = Determinan.determinangauss(matrix, matrix_size);

        // Cari solusi dari SPL
        float solusi = det_cramer/det;

        return solusi;
    }
}
