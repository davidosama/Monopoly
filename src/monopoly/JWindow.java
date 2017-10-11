package monopoly;

import java.util.Random;
import javax.swing.JLabel;

public class JWindow extends javax.swing.JFrame {

    /**
     * Creates new form BoardWindow
     */
    public JWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoardPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        d2_label = new javax.swing.JLabel();
        d1_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monopoly");
        setPreferredSize(new java.awt.Dimension(1280, 760));
        setSize(new java.awt.Dimension(1280, 760));

        BoardPanel.setMaximumSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setMinimumSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setName(""); // NOI18N
        BoardPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/board.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1280, 720));
        jLabel1.setMinimumSize(new java.awt.Dimension(720, 720));
        jLabel1.setPreferredSize(new java.awt.Dimension(720, 720));
        BoardPanel.add(jLabel1);
        jLabel1.setBounds(0, 0, 720, 720);

        jButton1.setText("Roll Dices");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        BoardPanel.add(jButton1);
        jButton1.setBounds(790, 310, 160, 60);

        jButton2.setText("moveONE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        BoardPanel.add(jButton2);
        jButton2.setBounds(800, 450, 140, 60);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        BoardPanel.add(jScrollPane1);
        jScrollPane1.setBounds(770, 90, 223, 83);

        jLabel2.setText("DEBUGGING LOG");
        BoardPanel.add(jLabel2);
        jLabel2.setBounds(820, 70, 140, 16);
        BoardPanel.add(d2_label);
        d2_label.setBounds(900, 180, 110, 110);
        d2_label.getAccessibleContext().setAccessibleName("d2_label");
        d2_label.getAccessibleContext().setAccessibleDescription("");

        BoardPanel.add(d1_label);
        d1_label.setBounds(770, 180, 110, 110);
        d1_label.getAccessibleContext().setAccessibleName("d1_label");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
         Constants.carSys.GenerateDiceAndMove();
        
    }//GEN-LAST:event_jButton1ActionPerformed
    /* 
       the idea of move function is that
       CurPos Counter starts with zero end with 40 (from go to go)
       every time the car move a city the counter++
       first ten steps move left >=0 & <10
       second ten steps move up >= 10 & <20
       third move steps move left 
       fourth move step move down
        
    move is a for loop starts from 1 until the sum of dice
    each iteration moves 1 step using MoveCarLeft,Right,UP,Down functions
       MoveCarLeft loads the CarLeft.png and moves one step (58 pixel which is the city width) to the left
       the same goes for right, up and down functons
       
                after moving one step we check again if we reached the number of dice, if not, move again.
    
     */

    //get monopoly label, to add cars on it programmatically
    public JLabel getJlabel1() {
        return jLabel1;
    }
    
     public javax.swing.JTextArea getjTextArea1() {
        return jTextArea1;
    }
     
     public JLabel get_d2_label(){
         return d2_label;
     }
     
       public JLabel get_d1_label(){
         return d1_label;
     }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (Constants.testing) {
            //for testing, move player1 one extra city
            Constants.carSys.MoveCarNCities(Monopoly.p1,  1);
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
            java.util.logging.Logger.getLogger(JWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoardPanel;
    private javax.swing.JLabel d1_label;
    private javax.swing.JLabel d2_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
