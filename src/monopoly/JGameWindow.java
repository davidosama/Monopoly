package monopoly;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class JGameWindow extends javax.swing.JFrame {

    /**
     * Creates new form BoardWindow
     */
    private int curTurn = 0;

    //panels for choosing players names and icons
    boolean BuildHouse, Mortgage, SellHouse, Unmortgage, moving = false;
    ArrayList<JPanel> pnls = new ArrayList<>();
    ArrayList<JTextField> txt_filds = new ArrayList<>();
    ArrayList<JComboBox> cmb_bxs = new ArrayList<>();

    private ImageIcon[] locationIcons = new ImageIcon[40];
    private ImageIcon[] detailedIcons = new ImageIcon[40];
    private ImageIcon[] diceIcons = new ImageIcon[6];
    private ImageIcon[] chanceIcons = new ImageIcon[40];

    private AskToBuyOrAuction buyorAuctionWindow;
    private JPlayerInfo playerInfoWin;
    private AuctionDialog auctionDialog;

    public void addLabel(int playerNum, String iconName) {

        PieceLabel pieceLabel;

        pieceLabel = new PieceLabel(playerNum, iconName);

        ///////////////Code for mouse hover over player
        pieceLabel.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                playerInfoWin.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                playerInfoWin.setLocationRelativeTo(JGameWindow.this.getBoardLabel());
                playerInfoWin.openWindow(playerNum);
            }
        }
        );
        BoardLabel.add(pieceLabel);
    }

    public void hidePlayerInfoWindow() {
        playerInfoWin.setVisible(false);
    }

    public void showWithdrawButton(boolean show) {
        WithdrawBtn.setVisible(show);
    }

    public void changeTurn(int turn) {
        curTurn = turn;
        playerNameLabel.setText(Player.getName(turn) + "'s Turn");
    }

    public void moveCarLabel(int steps) {
        for (int i = 0; i < steps; i++) {
            ((PieceLabel) BoardLabel.getComponent(curTurn)).MoveOneCity();
        }
        this.drawCurrentLocation(-1);
    }

    public void removeLabel(int num) {
        BoardLabel.remove(num);
        BoardLabel.repaint();
    }

    public void addPlayer(String name, String iconName) {

        //Creates and add players into the array
        Player.playersList.add(new Player(name, iconName));
        addLabel(Player.playersList.size() - 1, iconName);

    }

    public JGameWindow() {
        initComponents();
        WithdrawBtn.setVisible(false);
        Constants.gameWindow = this;
        Constants.board = new Board();
        Constants.BoardHeight = BoardLabel.getHeight();
        Constants.BoardWidth = BoardLabel.getWidth();
        EndTurnButton.setVisible(false);
        //jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/BackGround.jpg"))); // NOI18N

        this.setLocationRelativeTo(null);

        initMenu();

    }

    //must be calle after specifying players number
    private void initGame() {
        playerInfoWin = new JPlayerInfo();
        ///
        Constants.carSys = new MonopolyController();
        initMVH();
        initIcons();
        MenuPanel.setVisible(false);
        BoardPanel.setVisible(true);
        playerNameLabel.setText(Player.getName(0) + "'s Turn");
        buyorAuctionWindow = new AskToBuyOrAuction();
        auctionDialog = new AuctionDialog();
    }

    private void initMenu() {
        BoardPanel.setVisible(false);
        chs_plyrs_nms.setVisible(false);

        pnls.add(pnl1);
        pnls.add(pnl2);
        pnls.add(pnl3);
        pnls.add(pnl4);
        pnls.add(pnl5);
        txt_filds.add(jTextField1);
        txt_filds.add(jTextField2);
        txt_filds.add(jTextField3);
        txt_filds.add(jTextField4);
        txt_filds.add(jTextField5);
        cmb_bxs.add(jComboBox1);
        cmb_bxs.add(jComboBox2);
        cmb_bxs.add(jComboBox3);
        cmb_bxs.add(jComboBox4);
        cmb_bxs.add(jComboBox5);

        if (Constants.testing) {
            jButton4.doClick();
            jButton5.doClick();
        }
    }

    private void trade() {

    }

    private void chs_plyrs_nms(int plyrs_num) {
        chs_plyrs_pnl.setVisible(false);
        chs_plyrs_nms.setVisible(true);

        for (int i = 4; i >= plyrs_num; i--) {
            pnls.get(i).setEnabled(false);
            pnls.get(i).setVisible(false);
            pnls.remove(i);
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

        tradeDialog = new javax.swing.JDialog();
        MenuPanel = new javax.swing.JPanel();
        chs_plyrs_nms = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnl1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        pnl2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        pnl3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        pnl4 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        pnl5 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        BackGround1 = new javax.swing.JLabel();
        chs_plyrs_pnl = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        BackGround2 = new javax.swing.JLabel();
        BoardPanel = new javax.swing.JPanel();
        WithdrawBtn = new javax.swing.JButton();
        RollDiceButton = new javax.swing.JButton();
        EndTurnButton = new javax.swing.JButton();
        DicePanel = new javax.swing.JPanel();
        d1_label = new javax.swing.JLabel();
        d2_label = new javax.swing.JLabel();
        cardPanel = new javax.swing.JPanel();
        detailedCardLabel = new javax.swing.JLabel();
        currentCardLabel = new javax.swing.JLabel();
        BoardLabel = new javax.swing.JLabel();
        mvhPanel = new javax.swing.JPanel();
        functionsPanel = new javax.swing.JPanel();
        buildHouseButton = new javax.swing.JButton();
        sellHouseButton = new javax.swing.JButton();
        mortgageButton = new javax.swing.JButton();
        unmortgageButton = new javax.swing.JButton();
        endGameButton = new javax.swing.JButton();
        playerNameLabel = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        BackGround = new javax.swing.JLabel();

        javax.swing.GroupLayout tradeDialogLayout = new javax.swing.GroupLayout(tradeDialog.getContentPane());
        tradeDialog.getContentPane().setLayout(tradeDialogLayout);
        tradeDialogLayout.setHorizontalGroup(
            tradeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        tradeDialogLayout.setVerticalGroup(
            tradeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monopoly");
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 760));

        MenuPanel.setMaximumSize(new java.awt.Dimension(1280, 720));
        MenuPanel.setMinimumSize(new java.awt.Dimension(1280, 720));
        MenuPanel.setName(""); // NOI18N
        MenuPanel.setLayout(null);

        chs_plyrs_nms.setLayout(null);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Create Players");
        chs_plyrs_nms.add(jLabel3);
        jLabel3.setBounds(0, 120, 1250, 135);

        jButton5.setText("Start Game");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        chs_plyrs_nms.add(jButton5);
        jButton5.setBounds(530, 240, 190, 60);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(0, 1));

        pnl1.setOpaque(false);

        jTextField1.setText("Player 1");
        pnl1.add(jTextField1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hat", "Horse", "Iron", "Car", "Ship" }));
        pnl1.add(jComboBox1);

        jPanel3.add(pnl1);

        pnl2.setOpaque(false);

        jTextField2.setText("Player 2");
        jTextField2.setToolTipText("");
        pnl2.add(jTextField2);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hat", "Horse", "Iron", "Car", "Ship" }));
        jComboBox2.setSelectedItem("Horse");
        pnl2.add(jComboBox2);

        jPanel3.add(pnl2);

        pnl3.setOpaque(false);

        jTextField3.setText("Player 3");
        pnl3.add(jTextField3);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hat", "Horse", "Iron", "Car", "Ship" }));
        jComboBox3.setSelectedItem("Iron");
        pnl3.add(jComboBox3);

        jPanel3.add(pnl3);

        pnl4.setOpaque(false);

        jTextField4.setText("Player 4");
        pnl4.add(jTextField4);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hat", "Horse", "Iron", "Car", "Ship" }));
        jComboBox4.setSelectedItem("Car");
        pnl4.add(jComboBox4);

        jPanel3.add(pnl4);

        pnl5.setOpaque(false);

        jTextField5.setText("Player 5");
        pnl5.add(jTextField5);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hat", "Horse", "Iron", "Car", "Ship" }));
        jComboBox5.setSelectedItem("Ship");
        pnl5.add(jComboBox5);

        jPanel3.add(pnl5);

        chs_plyrs_nms.add(jPanel3);
        jPanel3.setBounds(10, 340, 1240, 390);

        BackGround1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/Menu.jpg"))); // NOI18N
        chs_plyrs_nms.add(BackGround1);
        BackGround1.setBounds(-160, -170, 1560, 1070);

        MenuPanel.add(chs_plyrs_nms);
        chs_plyrs_nms.setBounds(0, 0, 1250, 790);

        chs_plyrs_pnl.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Choose Number of Players");
        chs_plyrs_pnl.add(jLabel2);
        jLabel2.setBounds(-10, 110, 1260, 135);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("2 Players");
        jButton1.setName("2"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1jButtonActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("3 Players");
        jButton2.setName("3"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2jButtonActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setText("4 Players");
        jButton3.setName("4"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3jButtonActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        jButton4.setText("5 Players");
        jButton4.setName("5"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4jButtonActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        chs_plyrs_pnl.add(jPanel2);
        jPanel2.setBounds(180, 350, 930, 130);

        BackGround2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/Menu.jpg"))); // NOI18N
        chs_plyrs_pnl.add(BackGround2);
        BackGround2.setBounds(-160, -170, 1560, 1070);

        MenuPanel.add(chs_plyrs_pnl);
        chs_plyrs_pnl.setBounds(0, 0, 1250, 790);

        BoardPanel.setMaximumSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setMinimumSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setName(""); // NOI18N
        BoardPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        BoardPanel.setLayout(null);

        WithdrawBtn.setText("Declare Bankruptcy");
        WithdrawBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WithdrawBtnActionPerformed(evt);
            }
        });
        BoardPanel.add(WithdrawBtn);
        WithdrawBtn.setBounds(300, 550, 160, 60);

        RollDiceButton.setText("Roll Dice");
        RollDiceButton.setBorder(null);
        RollDiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RollDiceButtonActionPerformed(evt);
            }
        });
        BoardPanel.add(RollDiceButton);
        RollDiceButton.setBounds(300, 550, 160, 60);

        EndTurnButton.setText("End Turn");
        EndTurnButton.setBorder(null);
        EndTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EndTurnButtonActionPerformed(evt);
            }
        });
        BoardPanel.add(EndTurnButton);
        EndTurnButton.setBounds(300, 550, 160, 60);

        DicePanel.setBackground(new java.awt.Color(210, 234, 220));
        DicePanel.setPreferredSize(new java.awt.Dimension(240, 140));
        DicePanel.add(d1_label);
        d1_label.getAccessibleContext().setAccessibleName("d1_label");

        DicePanel.add(d2_label);
        d2_label.getAccessibleContext().setAccessibleName("d2_label");
        d2_label.getAccessibleContext().setAccessibleDescription("");

        BoardPanel.add(DicePanel);
        DicePanel.setBounds(230, 500, 240, 120);

        cardPanel.setBackground(new java.awt.Color(210, 234, 220));
        cardPanel.setToolTipText("");
        cardPanel.setMinimumSize(new java.awt.Dimension(252, 284));
        cardPanel.setOpaque(false);
        cardPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        detailedCardLabel.setBackground(new java.awt.Color(210, 234, 220));
        cardPanel.add(detailedCardLabel);
        cardPanel.add(currentCardLabel);

        BoardPanel.add(cardPanel);
        cardPanel.setBounds(170, 220, 410, 280);

        BoardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/board.jpg"))); // NOI18N
        BoardLabel.setMaximumSize(new java.awt.Dimension(1280, 720));
        BoardLabel.setMinimumSize(new java.awt.Dimension(720, 720));
        BoardLabel.setPreferredSize(new java.awt.Dimension(720, 720));
        BoardPanel.add(BoardLabel);
        BoardLabel.setBounds(20, 10, 720, 720);

        mvhPanel.setBackground(new java.awt.Color(0, 0, 0));
        mvhPanel.setOpaque(false);
        mvhPanel.setLayout(null);
        BoardPanel.add(mvhPanel);
        mvhPanel.setBounds(20, 10, 720, 720);

        functionsPanel.setOpaque(false);

        buildHouseButton.setText("Build House");
        buildHouseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildHouseButtonActionPerformed(evt);
            }
        });

        sellHouseButton.setText("Sell House");
        sellHouseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellHouseButtonActionPerformed(evt);
            }
        });

        mortgageButton.setText("Mortgage");
        mortgageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mortgageButtonActionPerformed(evt);
            }
        });

        unmortgageButton.setText("Unmortgage");
        unmortgageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unmortgageButtonActionPerformed(evt);
            }
        });

        endGameButton.setText("End Game");
        endGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endGameButtonActionPerformed(evt);
            }
        });

        playerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton6.setText("Trade");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout functionsPanelLayout = new javax.swing.GroupLayout(functionsPanel);
        functionsPanel.setLayout(functionsPanelLayout);
        functionsPanelLayout.setHorizontalGroup(
            functionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(functionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buildHouseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sellHouseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mortgageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(unmortgageButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        functionsPanelLayout.setVerticalGroup(
            functionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buildHouseButton)
                .addGap(18, 18, 18)
                .addComponent(sellHouseButton)
                .addGap(18, 18, 18)
                .addComponent(mortgageButton)
                .addGap(18, 18, 18)
                .addComponent(unmortgageButton)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(endGameButton)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        BoardPanel.add(functionsPanel);
        functionsPanel.setBounds(790, 30, 170, 420);

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/Menu.jpg"))); // NOI18N
        BoardPanel.add(BackGround);
        BackGround.setBounds(-10, -80, 1320, 950);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RollDiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RollDiceButtonActionPerformed

        if (!(BuildHouse || Mortgage || SellHouse || Unmortgage)) {
            disableRollDiceBtn();
            moving = true;
            Constants.carSys.GenerateDiceAndMove();
        }

    }//GEN-LAST:event_RollDiceButtonActionPerformed

    private void EndTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EndTurnButtonActionPerformed

        if (!(BuildHouse || Mortgage || SellHouse || Unmortgage)) {
            Constants.carSys.switchTurn();
        }
    }//GEN-LAST:event_EndTurnButtonActionPerformed

    private void jButton1jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1jButtonActionPerformed
        chs_plyrs_nms(2);
    }//GEN-LAST:event_jButton1jButtonActionPerformed

    private void jButton2jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2jButtonActionPerformed
        chs_plyrs_nms(3);
    }//GEN-LAST:event_jButton2jButtonActionPerformed

    private void jButton3jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3jButtonActionPerformed
        chs_plyrs_nms(4);
    }//GEN-LAST:event_jButton3jButtonActionPerformed

    private void jButton4jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4jButtonActionPerformed
        chs_plyrs_nms(5);
    }//GEN-LAST:event_jButton4jButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        for (int i = 0; i < pnls.size(); i++) {
            addPlayer(txt_filds.get(i).getText(), cmb_bxs.get(i).getSelectedItem().toString());
        }

        initGame();
    }//GEN-LAST:event_jButton5ActionPerformed

    public void enableButtons(JButton button, boolean enable) {

        for (Component c : functionsPanel.getComponents()) {
            if (!c.equals(button)) {
                c.setEnabled(enable);
            }

        }
    }

    private void buildHouseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildHouseButtonActionPerformed

        if (!moving) {
            if (!BuildHouse) {
                buildHouseButton.setText("Stop");
                BuildHouse = true;
                enableButtons(buildHouseButton, false);
            } else {
                buildHouseButton.setText("Build House");
                BuildHouse = false;
                enableButtons(buildHouseButton, true);
            }
        }

    }//GEN-LAST:event_buildHouseButtonActionPerformed

    private void endGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endGameButtonActionPerformed
        if (!moving) {
            Constants.carSys.endGame();
        }
    }//GEN-LAST:event_endGameButtonActionPerformed

    private void sellHouseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellHouseButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sellHouseButtonActionPerformed

    private void mortgageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mortgageButtonActionPerformed

        if (!moving) {
            if (!Mortgage) {
                mortgageButton.setText("Stop");
                Mortgage = true;
                enableButtons(mortgageButton, false);
            } else {
                mortgageButton.setText("Mortgage");
                Mortgage = false;
                enableButtons(mortgageButton, true);
            }
        }
    }//GEN-LAST:event_mortgageButtonActionPerformed

    private void unmortgageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unmortgageButtonActionPerformed
        if (!moving) {
            if (!Unmortgage) {
                unmortgageButton.setText("Stop");
                Unmortgage = true;
                enableButtons(unmortgageButton, false);
            } else {
                unmortgageButton.setText("Unmortgage");
                Unmortgage = false;
                enableButtons(unmortgageButton, true);
            }
        }
    }//GEN-LAST:event_unmortgageButtonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        tradeDialog.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void WithdrawBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WithdrawBtnActionPerformed
        removeLabel(Player.turn);
        Constants.carSys.removePlayer(Player.turn);
        WithdrawBtn.setVisible(false);
        EndTurnButtonActionPerformed(evt);

    }//GEN-LAST:event_WithdrawBtnActionPerformed
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

    public JLabel getBoardLabel() {
        return this.BoardLabel;
    }

    public void disableRollDiceBtn() {
        RollDiceButton.setVisible(false);
        DicePanel.setVisible(true);
    }

    public void enableEndTurnBtn(boolean enable) {
        moving = false;
        EndTurnButton.setVisible(enable);
    }

    public void enableDicePanel(boolean enable) {
        DicePanel.setVisible(enable);
    }

    public void enableRollDiceBtn() {
        moving = false;
        RollDiceButton.setVisible(true);
        DicePanel.setVisible(false);
    }

    public void drawDetailedLocation(int curPosition) {
        try {
            detailedCardLabel.setIcon(detailedIcons[curPosition]);
        } catch (Exception e) {
            detailedCardLabel.setIcon(null);
        }
    }

    //drawing chance/community cards
    public Icon getChanceCard(int id) {
        try {
            return chanceIcons[id];
        } catch (Exception e) {
            return null;
        }
    }

    public void drawCurrentLocation(int curPosition) {

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
                public void mouseClicked(MouseEvent e) {
                    if (BuildHouse) {
                        Constants.carSys.buildHouse(j);
                    } else if (SellHouse) {
                    } else if (Mortgage) {
                        Constants.carSys.Mortgage(j);
                    } else if (Unmortgage) {
                        Constants.carSys.UnMortgage(j);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!moving) {
                        JGameWindow.this.drawCurrentLocation(j);
                        JGameWindow.this.drawDetailedLocation(j);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {

                    if (!moving) {
                        JGameWindow.this.drawCurrentLocation(-1);
                        JGameWindow.this.drawDetailedLocation(-1);
                    }
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
                chanceIcons[i] = new javax.swing.ImageIcon(getClass().getResource("/drawables/Chance/" + i + ".png"));
            } catch (Exception e) {
                chanceIcons[i] = null;
            }

        }
    }

    /////////auction
    public int[] startAuction() {
        return auctionDialog.start();
    }

    public int startAskToBuyorAuction(int curPosition) {
        return buyorAuctionWindow.startBuyorAuction(locationIcons[curPosition], detailedIcons[curPosition]);
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
    private javax.swing.JLabel BackGround;
    private javax.swing.JLabel BackGround1;
    private javax.swing.JLabel BackGround2;
    private javax.swing.JLabel BoardLabel;
    private javax.swing.JPanel BoardPanel;
    private javax.swing.JPanel DicePanel;
    private javax.swing.JButton EndTurnButton;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JButton RollDiceButton;
    private javax.swing.JButton WithdrawBtn;
    private javax.swing.JButton buildHouseButton;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JPanel chs_plyrs_nms;
    private javax.swing.JPanel chs_plyrs_pnl;
    private javax.swing.JLabel currentCardLabel;
    private javax.swing.JLabel d1_label;
    private javax.swing.JLabel d2_label;
    private javax.swing.JLabel detailedCardLabel;
    private javax.swing.JButton endGameButton;
    private javax.swing.JPanel functionsPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton mortgageButton;
    private javax.swing.JPanel mvhPanel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JButton sellHouseButton;
    private javax.swing.JDialog tradeDialog;
    private javax.swing.JButton unmortgageButton;
    // End of variables declaration//GEN-END:variables
}
