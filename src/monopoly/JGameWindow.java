package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;

public class JGameWindow extends javax.swing.JFrame {

    /**
     * Creates new form BoardWindow
     */
    static ArrayList<JLabel> playersLabels = new ArrayList();
    public static JLabel curLabel;

    public JGameWindow() {
        initComponents();

        Constants.BoardHeight = BoardLabel.getHeight();
        Constants.BoardWidth = BoardLabel.getWidth();
        RollDiceButton.setBorder(new LineBorder(Constants.colors[0], 3));

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
        BoardLabel = new javax.swing.JLabel();
        currentCardPanel = new javax.swing.JPanel();
        currentCardLabel = new javax.swing.JLabel();
        RollDiceButton = new javax.swing.JButton();
        BackGround = new javax.swing.JLabel();
        DicePanel = new javax.swing.JPanel();
        d1_label = new javax.swing.JLabel();
        d2_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monopoly");
        setSize(new java.awt.Dimension(1280, 760));

        BoardPanel.setMaximumSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setMinimumSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setName(""); // NOI18N
        BoardPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setLayout(null);

        BoardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/board.jpg"))); // NOI18N
        BoardLabel.setMaximumSize(new java.awt.Dimension(1280, 720));
        BoardLabel.setMinimumSize(new java.awt.Dimension(720, 720));
        BoardLabel.setPreferredSize(new java.awt.Dimension(720, 720));
        BoardPanel.add(BoardLabel);
        BoardLabel.setBounds(20, 10, 720, 720);

        currentCardPanel.setBackground(new java.awt.Color(212, 232, 212));
        currentCardPanel.setToolTipText("");
        currentCardPanel.setMinimumSize(new java.awt.Dimension(252, 284));
        currentCardPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        currentCardLabel.setBackground(new java.awt.Color(255, 255, 255));
        currentCardPanel.add(currentCardLabel);

        BoardPanel.add(currentCardPanel);
        currentCardPanel.setBounds(245, 210, 240, 284);

        RollDiceButton.setText("Roll Dice");
        RollDiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RollDiceButtonActionPerformed(evt);
            }
        });
        BoardPanel.add(RollDiceButton);
        RollDiceButton.setBounds(790, 310, 160, 60);

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/BackGround.jpg"))); // NOI18N
        BoardPanel.add(BackGround);
        BackGround.setBounds(0, 0, 1280, 740);

        DicePanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout DicePanelLayout = new javax.swing.GroupLayout(DicePanel);
        DicePanel.setLayout(DicePanelLayout);
        DicePanelLayout.setHorizontalGroup(
            DicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DicePanelLayout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addComponent(d1_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d2_label))
        );
        DicePanelLayout.setVerticalGroup(
            DicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DicePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(DicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(d2_label, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(d1_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70))
        );

        d1_label.getAccessibleContext().setAccessibleName("d1_label");
        d2_label.getAccessibleContext().setAccessibleName("d2_label");
        d2_label.getAccessibleContext().setAccessibleDescription("");

        BoardPanel.add(DicePanel);
        DicePanel.setBounds(728, 110, 253, 160);

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

    private void RollDiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RollDiceButtonActionPerformed

        RollDiceButton.setBorder(null);
        if (!Constants.carSys.t.isRunning()) {
            Constants.carSys.GenerateDiceAndMove();
        }


    }//GEN-LAST:event_RollDiceButtonActionPerformed
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
        return BoardLabel;
    }
    
    public JLabel get_d2_label() {
        return d2_label;
    }

    public JLabel get_d1_label() {
        return d1_label;
    }

    public void disableRollDiceBtn() {

        RollDiceButton.setEnabled(false);

    }

    public void enableRollDiceBtn() {

        RollDiceButton.setEnabled(true);

    }

    public void drawCity(int curPosition) {
        try {
            currentCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/Cities/" + curPosition + ".png")));
        } catch (Exception e) {
            currentCardLabel.setIcon(null);
        }
    }

    public void drawCurrentCard(int curPosition) {
        // to-do, adding more images and loading the images to an array of icons at the beginning

        try {
            currentCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/Cards/" + curPosition + ".png")));
        } catch (Exception e) {
        }

    }

    public void setRollBtnClr(int playerNum) {
        RollDiceButton.setBorder(new LineBorder(Constants.colors[playerNum - 1], 3));
    }

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
            java.util.logging.Logger.getLogger(JGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JGameWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackGround;
    private javax.swing.JLabel BoardLabel;
    private javax.swing.JPanel BoardPanel;
    private javax.swing.JPanel DicePanel;
    private javax.swing.JButton RollDiceButton;
    private javax.swing.JLabel currentCardLabel;
    private javax.swing.JPanel currentCardPanel;
    private javax.swing.JLabel d1_label;
    private javax.swing.JLabel d2_label;
    // End of variables declaration//GEN-END:variables
}
