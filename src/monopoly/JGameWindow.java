package monopoly;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class JGameWindow extends javax.swing.JFrame {

    /**
     * Creates new form BoardWindow
     */
    private int curTurn = 0;

    private ArrayList<JPlayerInfo> mv_refs;

    private ImageIcon[] locationIcons = new ImageIcon[40];
    private ImageIcon[] detailedIcons = new ImageIcon[40];
    private ImageIcon[] diceIcons = new ImageIcon[6];
    private ImageIcon[] chanceIcons = new ImageIcon[40];

    public void addLabel(int playerNum) {

        PieceLabel pieceLabel = new PieceLabel(playerNum);

        ///////////////Code for mouse hover over player
        /* pieceLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.

                Constants.playerInfoWin.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.

                JGameWindow gw = Constants.gameWindow;
                Constants.playerInfoWin = new JPlayerInfo();
                Constants.playerInfoWin.setLocation(gw.getX() + 126, gw.getY() + 139);
                Constants.playerInfoWin.setVisible(true);

            }
        });*/
        BoardLabel.add(pieceLabel);
    }

    public void changeTurn(int turn) {
        curTurn = turn;
    }

    public void moveCarLabel() {
        ((PieceLabel) BoardLabel.getComponent(curTurn)).MoveOneCity();
    }

    public void addPlayers(int number) {

        //Creates and add players into the array
        for (int i = 0; i < number; i++) {
            Player.playersList.add(new Player());
            addLabel(i);
        }

    }

    public JGameWindow() {
        initComponents();
        Constants.carSys = new CarAndDiceSystem();
        Constants.BoardHeight = BoardLabel.getHeight();
        Constants.BoardWidth = BoardLabel.getWidth();

        PlayerInfoArea.setText("Money: 1000\nCities Owned: No cities");
        initMVH();
        initIcons();
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
        BIGCITYLBL = new javax.swing.JLabel();
        mvhPanel = new javax.swing.JPanel();
        currentCardPanel = new javax.swing.JPanel();
        currentCardLabel = new javax.swing.JLabel();
        RollDiceButton = new javax.swing.JButton();
        BoardLabel = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();
        DicePanel = new javax.swing.JPanel();
        d1_label = new javax.swing.JLabel();
        d2_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PlayerInfoArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monopoly");
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 760));

        BoardPanel.setMaximumSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setMinimumSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setName(""); // NOI18N
        BoardPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setLayout(null);
        BoardPanel.add(BIGCITYLBL);
        BIGCITYLBL.setBounds(270, 220, 220, 290);

        mvhPanel.setBackground(new java.awt.Color(0, 0, 0));
        mvhPanel.setOpaque(false);
        mvhPanel.setLayout(null);
        BoardPanel.add(mvhPanel);
        mvhPanel.setBounds(20, 10, 720, 720);

        currentCardPanel.setBackground(new java.awt.Color(210, 234, 220));
        currentCardPanel.setToolTipText("");
        currentCardPanel.setMinimumSize(new java.awt.Dimension(252, 284));
        currentCardPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        currentCardLabel.setBackground(new java.awt.Color(255, 255, 255));
        currentCardPanel.add(currentCardLabel);

        BoardPanel.add(currentCardPanel);
        currentCardPanel.setBounds(260, 230, 236, 280);

        RollDiceButton.setText("Roll Dice");
        RollDiceButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        RollDiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RollDiceButtonActionPerformed(evt);
            }
        });
        BoardPanel.add(RollDiceButton);
        RollDiceButton.setBounds(300, 550, 160, 60);

        BoardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/board.jpg"))); // NOI18N
        BoardLabel.setMaximumSize(new java.awt.Dimension(1280, 720));
        BoardLabel.setMinimumSize(new java.awt.Dimension(720, 720));
        BoardLabel.setPreferredSize(new java.awt.Dimension(720, 720));
        BoardPanel.add(BoardLabel);
        BoardLabel.setBounds(20, 10, 720, 720);

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/BackGround.jpg"))); // NOI18N
        BoardPanel.add(BackGround);
        BackGround.setBounds(0, 0, 1030, 740);

        DicePanel.setBackground(new java.awt.Color(210, 234, 220));
        DicePanel.setPreferredSize(new java.awt.Dimension(240, 140));
        DicePanel.add(d1_label);
        d1_label.getAccessibleContext().setAccessibleName("d1_label");

        DicePanel.add(d2_label);
        d2_label.getAccessibleContext().setAccessibleName("d2_label");
        d2_label.getAccessibleContext().setAccessibleDescription("");

        BoardPanel.add(DicePanel);
        DicePanel.setBounds(235, 470, 240, 140);

        PlayerInfoArea.setColumns(20);
        PlayerInfoArea.setRows(5);
        jScrollPane2.setViewportView(PlayerInfoArea);

        BoardPanel.add(jScrollPane2);
        jScrollPane2.setBounds(1030, 10, 240, 730);

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
        currentCardLabel.setIcon(null);
        disableRollDiceBtn();
        Constants.carSys.GenerateDiceAndMove();

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


    public void disableRollDiceBtn() {
        RollDiceButton.setEnabled(false);
        RollDiceButton.setVisible(false);
        DicePanel.setVisible(true);
    }

    public void enableRollDiceBtn() {
        RollDiceButton.setEnabled(true);
        RollDiceButton.setVisible(true);
        DicePanel.setVisible(false);
    }

    public void drawDetailedLocation(int curPosition) {
        try {
            currentCardLabel.setIcon(detailedIcons[curPosition]);
        } catch (Exception e) {
            currentCardLabel.setIcon(null);
        }
    }

    //drawing chance/community cards
    public void drawChanceCard(int id) {
        try {
            currentCardLabel.setIcon(chanceIcons[id]);
        } catch (Exception e) {
            currentCardLabel.setIcon(null);
        }
    }

    public void drawCurrentLocation(int curPosition) {
        // to-do, adding more images and loading the images to an array of icons at the beginning

        try {
            currentCardLabel.setIcon(locationIcons[curPosition]);
        } catch (Exception e) {
            currentCardLabel.setIcon(null);
        }

    }

    public void setRollBtnClr(int playerNum) {
        RollDiceButton.setBorder(new LineBorder(Constants.colors[playerNum], 3));
    }

    private void initMVH() {

        //it's better to go with loops like that, i'll modify the rest later or do it if you can
        for (int i = 0; i < 40; i++) {
            final int j = i;
            JLabel mvh = new JLabel();
            mvhPanel.add(mvh);
            if (i > 0 && i <= 9) {
                mvh.setBounds(Constants.BoardWidth - Constants.CornerWidth - i * Constants.CityWidth, Constants.BoardHeight - Constants.LocationHeight, Constants.CityWidth, Constants.LocationHeight);
            } else if (i > 10 && i <= 19) {
                mvh.setBounds(0, Constants.BoardHeight - Constants.CornerWidth - (i % 10) * Constants.CityWidth, Constants.LocationHeight, Constants.CityWidth);
            } else if (i > 20 && i <= 29) {
                mvh.setBounds(Constants.CornerWidth + ((i - 1) % 20) * Constants.CityWidth, 0, Constants.CityWidth, Constants.LocationHeight);
            } else if (i > 30 && i <= 39) {
                mvh.setBounds(Constants.BoardWidth - Constants.LocationHeight, Constants.CornerWidth + ((i - 1) % 30) * Constants.CityWidth, Constants.LocationHeight, Constants.CityWidth);
            }

            mvh.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    JGameWindow.this.drawCurrentLocation(j);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JGameWindow.this.drawCurrentLocation(-1);
                }
            });

        }

    }

    public void drawDice(int d1, int d2) {
        d1_label.setIcon(diceIcons[d1 - 1]);
        d2_label.setIcon(diceIcons[d2 - 1]);
    }

    public void initIcons() {
        for (int i = 0; i < locationIcons.length; i++) {
            try {
                locationIcons[i] = new ImageIcon(getClass().getResource("/drawables/Cards/" + i + ".png"));
            } catch (Exception e) {
                locationIcons[i] = null;
            }
        }
        for (int i = 0; i < detailedIcons.length; i++) {
            try {
                detailedIcons[i] = new ImageIcon(getClass().getResource("/drawables/Cities/" + i + ".png"));
            } catch (Exception e) {
                detailedIcons[i] = null;
            }
        }
        for (int i = 0; i < diceIcons.length; i++) {
            diceIcons[i] = new ImageIcon(getClass().getResource("/drawables/d" + (i + 1) + ".png"));
        }
        for (int i = 0; i < chanceIcons.length; i++) {
            try {
                chanceIcons[i] = new javax.swing.ImageIcon(getClass().getResource("/drawables/Chance-Community-Cards/" + i + ".png"));
            } catch (Exception e) {
                chanceIcons[i] = null;
            }
        }
    }

    /////////auction
    public int startAuction(int curPlayerNum) {

        //Begin the Auction with the Player who initialized it
        AuctionDialog a;
        AuctionDialog.PlayerNumTurn = curPlayerNum;
        a = new AuctionDialog(this, true);
        a.setVisible(true);
        boolean firstTourFinished = true;

        for (int i = 0; i < Player.playersList.size(); i++) {
            // if this is the number of the player who initialized the Auction,skip it in the first Tour only ( he already specified the amount of money )
            if (Player.playersList.get(i).num == curPlayerNum && firstTourFinished) {
                firstTourFinished = false;
                continue;
            }
            AuctionDialog.PlayerNumTurn = Player.playersList.get(i).num;
            System.out.println(AuctionDialog.PlayerNumTurn + "skdjalskdjalskdajklPLAYERNUMTURN");
            AuctionDialog ad;
            ad = new AuctionDialog(this, true);
            ad.setVisible(true);

            while (ad.isFocusOwner()) {
                //Wait until this Dialog is closed and the player clicks submit in order to open a new one for another player to submit the amount of money
            }
            //Use this to make the loop run for ever 
//            if(i==(Player.playersList.size()-1)){
//                i=0;
//            }
        }
        System.out.println("THE WINNER IS " + AuctionDialog.HighestAuctionPlayer);
        return AuctionDialog.HighestAuctionPlayer;
    }

    /////////
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
            java.util.logging.Logger.getLogger(JGameWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JGameWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JGameWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JGameWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel BIGCITYLBL;
    private javax.swing.JLabel BackGround;
    private javax.swing.JLabel BoardLabel;
    private javax.swing.JPanel BoardPanel;
    private javax.swing.JPanel DicePanel;
    public javax.swing.JTextArea PlayerInfoArea;
    private javax.swing.JButton RollDiceButton;
    private javax.swing.JLabel currentCardLabel;
    private javax.swing.JPanel currentCardPanel;
    private javax.swing.JLabel d1_label;
    private javax.swing.JLabel d2_label;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mvhPanel;
    // End of variables declaration//GEN-END:variables
}
