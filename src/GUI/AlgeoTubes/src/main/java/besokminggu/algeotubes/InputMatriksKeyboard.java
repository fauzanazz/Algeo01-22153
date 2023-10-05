/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package besokminggu.algeotubes;


import besokminggu.Fungsi.TextToMatriks;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class InputMatriksKeyboard extends javax.swing.JFrame {

    /**
     * Creates new form InputMatriksKeyboard
     */
    int nMatrix = AlgeoTubes.n;
    int mMatrix = AlgeoTubes.m;
    private JTextField[][] newTextFields;
    
    private void addgrid(){
        // Add a new row to the matrix input.
        newTextFields = new JTextField[nMatrix+1][mMatrix+1];
        for (int i = 0; i < nMatrix+1; i++) {
            for (int j = 0; j < mMatrix+1; j++) {
                newTextFields[i][j] = new JTextField();

            }
        }
        jPanel1.removeAll();
        for (int i = 0; i < nMatrix+1; i++) {
            for (int j = 0; j < mMatrix+1; j++) {
                jPanel1.add(newTextFields[i][j]);
            }
        }
        // Update the GridLayout to include the new row.
        jPanel1.setLayout(new GridLayout(nMatrix+1, nMatrix+1));
        jPanel1.repaint();
        jPanel1.updateUI();
    }
    
    public void minusgrid(){
        // Create a new 2D array with the selected row and column removed.
            newTextFields = new JTextField[nMatrix - 1][mMatrix - 1];
            for (int i = 0; i < nMatrix-1; i++) {
                for (int j = 0; j < mMatrix-1; j++) {
                    newTextFields[i][j] = new JTextField();
                }
            }
            
            jPanel1.removeAll();
            for (int i = 0; i < nMatrix-1; i++) {
                for (int j = 0; j < mMatrix-1; j++) {
                    jPanel1.add(newTextFields[i][j]);
                }
            }

            // Update the GridLayout to include the new row.
            jPanel1.setLayout(new GridLayout(nMatrix-1, mMatrix-1));
            jPanel1.repaint();
            jPanel1.updateUI();
    }
    
    public InputMatriksKeyboard() {
        initComponents();
        
        String mMat = String.valueOf(mMatrix);
        String nMat = String.valueOf(nMatrix);
        jLabel2.setText(nMat);
        jLabel4.setText(mMat);
        
        jPanel1.setPreferredSize(new Dimension(368, 320));
        jPanel1.setMinimumSize(new Dimension(200, 200));
        jPanel1.setMaximumSize(new Dimension(368,320));
        // Create a JPanel to hold the matrix input fields.
        jPanel1.setLayout(new GridLayout(nMatrix, mMatrix));

        // Create a JTextField for each element in the matrix.
        newTextFields = new JTextField[nMatrix][mMatrix];
        for (int i = 0; i < nMatrix; i++) {
            for (int j = 0; j < mMatrix; j++) {
                newTextFields[i][j] = new JTextField();
                jPanel1.add(newTextFields[i][j]);
            }
        }  
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("JFrame");
        setMinimumSize(new java.awt.Dimension(100, 100));
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Masukkan Matriks");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("M");

        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setText("X");

        jLabel4.setText("N");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel2)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel3)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jButton1)))
                .addContainerGap(205, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(37, 37, 37)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AlgeoTubes.matriksinput = getTextfieldTodoubleMatrix(newTextFields);
        if (AlgeoTubes.InterpolasiBicubicSpline == 1) {
            AlgeoTubes.x = SpecialInput.getx();
            AlgeoTubes.y = SpecialInput.gety();
            if (nMatrix == 4 && mMatrix == 4 ){
                try {
                    afterInput(false, false, false);
                 } catch (IOException ex) {
                     Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                 }
            } else {
                JOptionPane.showMessageDialog(null, "Udah lah bang jangan aneh2, emang harus 4.", "Duar you trigger an easter egg!", JOptionPane.ERROR_MESSAGE);
            }
        } else if (AlgeoTubes.ImplementasiInterpolasiBicubicSpline == 1 || AlgeoTubes.InterpolasiPolinom == 1) {
            AlgeoTubes.x = SpecialInput.getx();
            try {
                afterInput(false, false, false);
            } catch (IOException ex) {
                Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            boolean square = AlgeoTubes.matriksinput.length == AlgeoTubes.matriksinput[0].length;
            boolean under_3 = AlgeoTubes.matriksinput.length < 3 && AlgeoTubes.matriksinput[0].length < 3;
            boolean inverse_spl = AlgeoTubes.matriksinput.length == AlgeoTubes.matriksinput[0].length-1;
            try {
                afterInput(square, under_3, inverse_spl);
            } catch (IOException ex) {
                Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void doubleToStringMatrix(double[][] doubleMatrix) {
        String[][] stringMatrix = new String[doubleMatrix.length][doubleMatrix[0].length];
        for (int i = 0; i < doubleMatrix.length; i++) {
          for (int j = 0; j < doubleMatrix[0].length; j++) {
            stringMatrix[i][j] = String.valueOf(doubleMatrix[i][j]);
          }
        }
        
        for (int i = 0; i < stringMatrix.length; i++) {
            for (int j = 0; j < stringMatrix[0].length; j++) {
              System.out.print(stringMatrix[i][j] + " ");
            }
            System.out.println();
          }
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (AlgeoTubes.DETDeterminanNxN != 1 || AlgeoTubes.DETDeterminanNxN == 1 && nMatrix < 3 ){
            addgrid();
            nMatrix++;
            mMatrix++;
            String mMat = String.valueOf(mMatrix);
            String nMat = String.valueOf(nMatrix);
            jLabel2.setText(nMat);
            jLabel4.setText(mMat); 
        } else {
            JOptionPane.showMessageDialog(null, "Mana bisa bang dia nambah lagi, Error code = 911", "Duar you trigger an easter egg!", JOptionPane.ERROR_MESSAGE);
        }
            
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        closeAllWindows();
        new MainMenu().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (nMatrix > 1 && mMatrix > 1) {
            minusgrid();
            nMatrix--;
            mMatrix--;
            String mMat = String.valueOf(mMatrix);
            String nMat = String.valueOf(nMatrix);
            jLabel2.setText(nMat);
            jLabel4.setText(mMat);
        } else {
            JOptionPane.showMessageDialog(null, "Walau sebenarnya tidak bisa mundur lagi, tetap pantang mundur kawan2!", "Duar you trigger an easter egg!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputMatriksKeyboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputMatriksKeyboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputMatriksKeyboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputMatriksKeyboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputMatriksKeyboard().setVisible(true);
            }
        });
    }
    
    private void afterInput(boolean determinan, boolean under_3, boolean inverse_spl) throws IOException{
        if (AlgeoTubes.SPLEliminasiGauss == 1) {
            new SPLMetodeEliminasiGauss().setVisible(true);

        } else if (AlgeoTubes.SPLELiminasiGaussJordan == 1){
            new SPLMetodeEliminasiGaussJordan().setVisible(true);

        } else if (AlgeoTubes.SPLMatriksBalikan == 1 && inverse_spl){
            new SPLMetodeMatriksBalikan().setVisible(true);

        } else if (AlgeoTubes.SPLCramer == 1 && inverse_spl){
            new SPLKaidahCramer().setVisible(true);

        } else if (AlgeoTubes.DETDeterminanNxN == 1 && determinan && under_3){
            new DETDeterminanNxN().setVisible(true);

        } else if (AlgeoTubes.DETDeterminanNxN == 1 && determinan && !under_3){
            JOptionPane.showMessageDialog(null, "You found easter egg WOW! Your matrix is bigger than 3 id*o*.", "Maap ga maap kasar dikit.", JOptionPane.ERROR_MESSAGE);

        } else if (AlgeoTubes.DETDeterminandenganKofaktor == 1 && determinan){
            new DETDeterminandenganKofaktor().setVisible(true);

        } else if (AlgeoTubes.DETDeterminanMatriksSegitiga == 1 && determinan){
            new DETDeterminanMatriksSegitiga().setVisible(true);

        } else if (AlgeoTubes.INVAdjoin == 1 && determinan){
            new INVMatriksinversedenganAdjoin().setVisible(true);

        } else if (AlgeoTubes.INVBalikan == 1 && determinan){
            new INVMetodematriksBalikan().setVisible(true);

        } else if (AlgeoTubes.InterpolasiPolinom == 1){
            new InterpolasiPolinom().setVisible(true);

        } else if (AlgeoTubes.InterpolasiBicubicSpline == 1){
            new InterpolasiBicubicSpline().setVisible(true);

        } else if (AlgeoTubes.RegresiLinearBerganda == 1){
            new RegresiLinierBerganda().setVisible(true);

        } else if (AlgeoTubes.ImplementasiInterpolasiBicubicSpline == 1){
            new ImplementasiBicubicSpline().setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "An error occurred. Contact Ojan for help. Code error = 1", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public static void closeAllWindows() {
        Input.ResetState();
        for (Window window : Window.getWindows()) {
            window.dispose();
        }
    }
    
    public double[][] getTextfieldTodoubleMatrix(JTextField[][] textFields) {
        double[][] doubleMatrix = new double[nMatrix][mMatrix];
        int error = 0;
        double value;
        String text;
        for (int i = 0; i < nMatrix; i++) {
            for (int j = 0; j < mMatrix; j++) {
                if (textFields[i][j] == null){
                    text = "0";
                } else {
                    text = textFields[i][j].getText();
                }
                

                try {
                    if (!text.isEmpty()) {

                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Contact Ojan for help.", "Error", JOptionPane.ERROR_MESSAGE);
                    error = 1;
                }
                
                if (text.isEmpty()) {
                    value = 0;
                } else {
                    value = Double.parseDouble(text);
                }
                
                doubleMatrix[i][j] = value;
            }
            if (error == 1){
                break;
            }
        }
        return doubleMatrix;
    }

    public double getTextfieldToFloat(JTextField textField) {
        String text = textField.getText();
    
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "An error occurred. Contact Ojan for help.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    
        try {
            Double value = Double.valueOf(text);
            return value;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "An error occurred. Contact Ojan for help.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    
    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
