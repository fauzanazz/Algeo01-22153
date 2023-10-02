/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package besokminggu.algeotubes;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
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
        
        jButton3.addActionListener((ActionEvent e) -> {
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
            
        });
        
        jButton2.addActionListener((ActionEvent e) -> {
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
        });
        
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
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 76, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Masukkan Matriks");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 500, -1, -1));

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 170, -1));

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 440, 159, -1));

        jLabel2.setText("M");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, -1, -1));

        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, -1, -1));

        jLabel3.setText("X");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, -1, -1));

        jLabel4.setText("N");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AlgeoTubes.matriksinput = getTextfieldTodoubleMatrix(newTextFields);
        afterInput();
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
        nMatrix++;
        mMatrix++;
        String mMat = String.valueOf(mMatrix);
        String nMat = String.valueOf(nMatrix);
        jLabel2.setText(nMat);
        jLabel4.setText(mMat);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        closeAllWindows();
        new MainMenu().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        nMatrix--;
        mMatrix--;
        String mMat = String.valueOf(mMatrix);
        String nMat = String.valueOf(nMatrix);
        jLabel2.setText(nMat);
        jLabel4.setText(mMat);
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
    
    private void afterInput(){
        if (AlgeoTubes.SPLEliminasiGauss == 1) {
            new SPLMetodeEliminasiGauss().setVisible(true);

        } else if (AlgeoTubes.SPLELiminasiGaussJordan == 1){
            new SPLMetodeEliminasiGaussJordan().setVisible(true);

        } else if (AlgeoTubes.SPLMatriksBalikan == 1){
            new SPLMetodeMatriksBalikan().setVisible(true);

        } else if (AlgeoTubes.SPLCramer == 1){
            new SPLKaidahCramer().setVisible(true);

        } else if (AlgeoTubes.DETDeterminanNxN == 1){
            new DETDeterminanNxN().setVisible(true);

        } else if (AlgeoTubes.DETDeterminandenganKofaktor == 1){
            new DETDeterminandenganKofaktor().setVisible(true);

        } else if (AlgeoTubes.DETDeterminanMatriksSegitiga == 1){
            new DETDeterminanMatriksSegitiga().setVisible(true);

        } else if (AlgeoTubes.INVAdjoin == 1){
            new INVMatriksinversedenganAdjoin().setVisible(true);

        } else if (AlgeoTubes.INVBalikan == 1){
            new INVMetodematriksBalikan().setVisible(true);

        } else if (AlgeoTubes.InterpolasiPolinom == 1){
            new InterpolasiPolinom().setVisible(true);

        } else if (AlgeoTubes.InterpolasiBicubicSpline == 1){
            new InterpolasiBicubicSpline().setVisible(true);

        } else if (AlgeoTubes.RegresiLinearBerganda == 1){
            new RegresiLinierBerganda().setVisible(true);

        } else if (AlgeoTubes.ImplementasiInterpolasiBicubicSpline == 1){
            new SPLMetodeEliminasiGaussJordan().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "An error occurred. Contact Ojan for help.", "Error", JOptionPane.ERROR_MESSAGE);
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