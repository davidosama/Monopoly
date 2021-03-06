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
    private int HighestAuctionPrice;
    private Integer curPlayer;
    private ArrayList<Integer> players;

    public AuctionDialog() {

        //Setting Background
        try {
            Image BackgroundImage = ImageIO.read(AuctionDialog.class.getResource("/drawables/BackGround.jpg"));
            JPanel p = new JPanel() {
                public void paintComponent(Graphics g) {
                    g.drawImage(BackgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };
            this.setContentPane(p);
        } catch (IOException ex) {
            Logger.getLogger(AuctionDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(AuctionDialog.class.getName()).log(Level.SEVERE, null, e);
        }
        initComponents();

    }

    public int[] start() {
        this.HighestAuctionPrice = 0;
        this.curPlayer = Player.getTurn();
        loadArray();
        PlayerNameLabel.setText(Player.getName(curPlayer) + "'s " + "turn");
        AuctionPricelbl.setText("" + this.HighestAuctionPrice);
        this.setLocationRelativeTo(Constants.gameWindow.getBoardLabel());
        this.setVisible(true);
        return new int[]{curPlayer, HighestAuctionPrice};
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
        warningLbl = new javax.swing.JLabel();
        FoldBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Auction");
        setBounds(new java.awt.Rectangle(500, 370, 0, 0));
        setModal(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(520, 198));
        setResizable(false);

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
        IncrementAucBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IncrementAucBtnActionPerformed(evt);
            }
        });

        PlayerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        FoldBtn.setText("Fold");
        FoldBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FoldBtnActionPerformed(evt);
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
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SubmitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(FoldBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DecrementAucBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AuctionPricelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IncrementAucBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(82, 82, 82))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(390, Short.MAX_VALUE)
                .addComponent(warningLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubmitBtn)
                    .addComponent(FoldBtn))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed

        int price = Integer.parseInt(AuctionPricelbl.getText());

        if (price > Player.playersList.get(curPlayer).getMoney()) {
            warningLbl.setForeground(Color.red);
            warningLbl.setText("You don't Have Enough Money");
            SubmitBtn.setEnabled(false);

        } else if (price <= HighestAuctionPrice) {
            warningLbl.setForeground(Color.red);
            warningLbl.setText("You can't ! Another Player has already participated in the Auction with more money");
        } else if (price > HighestAuctionPrice) {
            HighestAuctionPrice = price;
            switchPlayer();
        }

    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void loadArray() {
        int playersCount = Player.playersList.size();
        players = new ArrayList();
        for (int i = 0; i < playersCount; i++) {
            players.add(i);
        }
    }

    private void switchPlayer() {

        players.remove(curPlayer);
        players.add(curPlayer);
        curPlayer = players.get(0);
        PlayerNameLabel.setText(Player.getName(curPlayer) + "'s " + "turn");
    }
    private void IncrementAucBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IncrementAucBtnActionPerformed
        //If there is a warning , remove it untill the player submits again
        warningLbl.setText("");
        int price = Integer.parseInt(AuctionPricelbl.getText());
        price += 10;
        AuctionPricelbl.setText("" + price);
    }//GEN-LAST:event_IncrementAucBtnActionPerformed

    private void DecrementAucBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DecrementAucBtnActionPerformed
        int price = Integer.parseInt(AuctionPricelbl.getText());
        int temp = price - 10;
        if (temp < HighestAuctionPrice) {

        } else {
            price -= 10;
            AuctionPricelbl.setText("" + price);
        }
    }//GEN-LAST:event_DecrementAucBtnActionPerformed

    private void FoldBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FoldBtnActionPerformed
        players.remove(curPlayer);
        curPlayer = players.get(0);
        if (players.size() == 1) {
            this.setVisible(false);
        }
        PlayerNameLabel.setText(Player.getName(curPlayer) + "'s " + "turn");

    }//GEN-LAST:event_FoldBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AuctionPricelbl;
    private javax.swing.JButton DecrementAucBtn;
    private javax.swing.JButton FoldBtn;
    private javax.swing.JButton IncrementAucBtn;
    private javax.swing.JLabel PlayerNameLabel;
    private javax.swing.JButton SubmitBtn;
    private javax.swing.JLabel warningLbl;
    // End of variables declaration//GEN-END:variables
}
