/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Mina
 */
public class AuctionDialog extends javax.swing.JDialog {

    /**
     * Creates new form AuctionDialog
     */
    // the player with the highest AuctionPrice
    public static int HighestAuctionPrice;
    public static int HighestAuctionPlayer;
    public static ArrayList HighestAuction=new ArrayList();
    public Boolean WontParticipateFlag=false;
    public static int PlayerNumTurn;//the player num whose turn to specify the price for the Auction
    public AuctionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        //Setting Background
        try {
           Image BackgroundImage = ImageIO.read(AuctionDialog.class.getResource("/drawables/bg.jpg"));
           JPanel p = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(BackgroundImage,0,0,getWidth(),getHeight(),this);
            }
        };
        this.setContentPane(p);
        } catch (IOException ex) {
            Logger.getLogger(AuctionDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        PlayerNameLabel.setText("Player "+PlayerNumTurn+" Auction");
        AuctionPricelbl.setText(""+this.HighestAuctionPrice);
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SubmitBtn = new javax.swing.JButton();
        AuctionPricelbl = new javax.swing.JLabel();
        DecrementAucBtn = new javax.swing.JButton();
        IncrementAucBtn = new javax.swing.JButton();
        PlayerNameLabel = new javax.swing.JLabel();
        NoAuctionCheckBox = new javax.swing.JCheckBox();
        warningLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Auction");
        setBounds(new java.awt.Rectangle(500, 370, 0, 0));

        SubmitBtn.setText("Submit");
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });

        AuctionPricelbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AuctionPricelbl.setText("0");

        DecrementAucBtn.setText("-");
        DecrementAucBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DecrementAucBtnActionPerformed(evt);
            }
        });

        IncrementAucBtn.setText("+");
        IncrementAucBtn.setSize(new java.awt.Dimension(97, 29));
        IncrementAucBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IncrementAucBtnActionPerformed(evt);
            }
        });

        PlayerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        NoAuctionCheckBox.setText("I don't want to participate in this Auction");
        NoAuctionCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoAuctionCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PlayerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(NoAuctionCheckBox)
                        .addGap(53, 53, 53))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(DecrementAucBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AuctionPricelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IncrementAucBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addGap(82, 82, 82))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(SubmitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(warningLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(PlayerNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IncrementAucBtn)
                    .addComponent(AuctionPricelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DecrementAucBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SubmitBtn)
                .addGap(24, 24, 24)
                .addComponent(NoAuctionCheckBox)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        if(NoAuctionCheckBox.isSelected()){
            WontParticipateFlag=true;
            this.dispose();
        }else{
            int price;
            if(Constants.testing==true){
                price=1500;
            }else{
                price = Integer.parseInt(AuctionPricelbl.getText());
            }
            System.out.println("alskdalskdalskdalskjdalk Player"+PlayerNumTurn);
            if(price>Player.playersList.get(PlayerNumTurn-1).getMoney()){
                warningLbl.setForeground(Color.red);
                warningLbl.setText("You don't Have Enough Money");
                SubmitBtn.setEnabled(false);
            }
            else if(price==HighestAuctionPrice){
                warningLbl.setForeground(Color.red);
                warningLbl.setText("You can't ! Another Player has already participated with the same amount of money");
            }
            else if(price<HighestAuctionPrice){
                warningLbl.setForeground(Color.red);
                warningLbl.setText("You can't ! Another Player has already participated in the Auction with more money");
            }
            else if(price>HighestAuctionPrice){
                System.out.println("Highest Auction Price is "+price+"Player"+PlayerNumTurn);
                HighestAuctionPrice=price;
                HighestAuctionPlayer=PlayerNumTurn;
                this.dispose();
            }
        }
        
    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void IncrementAucBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IncrementAucBtnActionPerformed
        //If there is a warning , remove it untill the player submits again
        warningLbl.setText("");
        int price = Integer.parseInt(AuctionPricelbl.getText());
        price+=10;
        AuctionPricelbl.setText(""+price);
    }//GEN-LAST:event_IncrementAucBtnActionPerformed

    private void DecrementAucBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DecrementAucBtnActionPerformed
        int price = Integer.parseInt(AuctionPricelbl.getText());
        int temp = price-10;
        if(temp<AuctionDialog.HighestAuctionPrice){
        
        }
        else{ 
            System.out.println("HIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHIHI");
            price-=10;
            AuctionPricelbl.setText(""+price);
        }
    }//GEN-LAST:event_DecrementAucBtnActionPerformed

    private void NoAuctionCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoAuctionCheckBoxActionPerformed
        if(NoAuctionCheckBox.isSelected())
        {
            if(PlayerNumTurn==HighestAuctionPlayer){
                NoAuctionCheckBox.setEnabled(false);
            }
            IncrementAucBtn.setEnabled(false);
            DecrementAucBtn.setEnabled(false);
        }
    }//GEN-LAST:event_NoAuctionCheckBoxActionPerformed

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
            java.util.logging.Logger.getLogger(AuctionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuctionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuctionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuctionDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AuctionDialog dialog = new AuctionDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AuctionPricelbl;
    private javax.swing.JButton DecrementAucBtn;
    private javax.swing.JButton IncrementAucBtn;
    private javax.swing.JCheckBox NoAuctionCheckBox;
    private javax.swing.JLabel PlayerNameLabel;
    private javax.swing.JButton SubmitBtn;
    private javax.swing.JLabel warningLbl;
    // End of variables declaration//GEN-END:variables
}
