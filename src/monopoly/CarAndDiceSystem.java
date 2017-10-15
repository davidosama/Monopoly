/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

public class CarAndDiceSystem {

    int d1, d2, res;
    javax.swing.Timer t;

    public CarAndDiceSystem() {

        t = new javax.swing.Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Player p = Player.getPlayer();
                MoveOneCity();
                Constants.gameWindow.drawCurrentCard(p.currentCity);
                res--;

                if (res == 0) {
                    Constants.gameWindow.enableRollDiceBtn();
                    Constants.gameWindow.drawCity(p.currentCity);
                    if (!(d1 == d2)) {
                        Player.MoveTurn();
                    }

                    t.stop();
                }
            }
        }
        );

    }

    public void GenerateDiceAndMove() {

        if (Constants.carSys.t.isRunning()) {
            return;
        }

        Random rand = new Random();
        d1 = rand.nextInt(6) + 1;
        d2 = rand.nextInt(6) + 1;

        //check if d1 == d2 to play again
        res = d1 + d2;

        //Constants.window.getjTextArea1().setText("d1: " + d1 + "\nd2: " + d2);
        //load dice image
        ImageIcon icon = loadImageOfDice(d1);
        Constants.gameWindow.get_d1_label().setIcon(icon);
        //load dice image
        icon = loadImageOfDice(d2);
        Constants.gameWindow.get_d2_label().setIcon(icon);

        Constants.gameWindow.disableRollDiceBtn();
        t.start();

        //Move the car ely 3aleha el door by the result of the dices
    }

    public ImageIcon loadImageOfDice(int dice)
    {
       return new javax.swing.ImageIcon(getClass().getResource("/drawables/" + dice + ".png"));
            
    }
    
    public void UpdateImageOfPlayer(String dist)
    {
          JLabel playerJlbl = Constants.curPlayer.label;
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/drawables/Car" + dist + "" +  Constants.curPlayer.num + ".png"));
            playerJlbl.setIcon(icon);
            playerJlbl.setBounds(playerJlbl.getX(),playerJlbl.getY(), icon.getIconWidth(), icon.getIconHeight());
        
    }

    
    public void MoveOneCity() {

        Player player = Constants.curPlayer;
        JLabel playerJlbl = player.label;

        //increment the player current city by the extra moves (movesNum)
        player.currentCity ++;

        //beacuse there are 40 cities
        player.currentCity = player.currentCity % 40;

        //Setting car icon based on position (Right,Left,Up..)
//      if (player.currentCity == 9 || player.currentCity == 19 || player.currentCity == 29 || player.currentCity == 39 )
               switch(player.currentCity) 
               {
                   case 10:
                     UpdateImageOfPlayer("UP");
                     break;
            
                   case 20:
                       UpdateImageOfPlayer("Right");
                       break;
                       
                   case 30:
                       UpdateImageOfPlayer("Down");
                       break;
                   case 0:
                       UpdateImageOfPlayer("Left");
                       break;
                }
               
               
               //playerJlbl.setLocation(d1, Constants.BoardHeight );

        switch (player.currentCity) {
            //bottom cities
            case 1:
                playerJlbl.setBounds(570, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 2:
                playerJlbl.setBounds(510, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 3:
                playerJlbl.setBounds(460, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 4:
                playerJlbl.setBounds(400, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 5:
                playerJlbl.setBounds(340, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 6:
                playerJlbl.setBounds(290, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 7:
                playerJlbl.setBounds(230, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 8:
                playerJlbl.setBounds(180, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 9:
                playerJlbl.setBounds(120, 620, Constants.CarDim, Constants.CarDim);
                break;
            //left cities
            case 10:
                playerJlbl.setBounds(60, 620, Constants.CarDim, Constants.CarDim);
                break;
            case 11:
                playerJlbl.setBounds(60, 560, Constants.CarDim, Constants.CarDim);
                break;
            case 12:
                playerJlbl.setBounds(60, 500, Constants.CarDim, Constants.CarDim);
                break;
            case 13:
                playerJlbl.setBounds(60, 450, Constants.CarDim, Constants.CarDim);
                break;
            case 14:
                playerJlbl.setBounds(60, 390, Constants.CarDim, Constants.CarDim);
                break;
            case 15:
                playerJlbl.setBounds(60, 340, Constants.CarDim, Constants.CarDim);
                break;
            case 16:
                playerJlbl.setBounds(60, 280, Constants.CarDim, Constants.CarDim);
                break;
            case 17:
                playerJlbl.setBounds(60, 220, Constants.CarDim, Constants.CarDim);
                break;
            case 18:
                playerJlbl.setBounds(60, 170, Constants.CarDim, Constants.CarDim);
                break;
            case 19:
                playerJlbl.setBounds(60, 110, Constants.CarDim, Constants.CarDim);
                break;
            case 20:
                playerJlbl.setBounds(60, 50, Constants.CarDim, Constants.CarDim);
                break;
            //UP cities
            case 21:
                playerJlbl.setBounds(120, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 22:
                playerJlbl.setBounds(180, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 23:
                playerJlbl.setBounds(230, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 24:
                playerJlbl.setBounds(290, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 25:
                playerJlbl.setBounds(340, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 26:
                playerJlbl.setBounds(400, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 27:
                playerJlbl.setBounds(460, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 28:
                playerJlbl.setBounds(510, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 29:
                playerJlbl.setBounds(570, 50, Constants.CarDim, Constants.CarDim);
                break;
            case 30:
                playerJlbl.setBounds(640, 50, Constants.CarDim, Constants.CarDim);
                break;
            //Right cities
            case 31:
                playerJlbl.setBounds(640, 110, Constants.CarDim, Constants.CarDim);
                break;
            case 32:
                playerJlbl.setBounds(640, 170, Constants.CarDim, Constants.CarDim);
                break;
            case 33:
                playerJlbl.setBounds(640, 220, Constants.CarDim, Constants.CarDim);
                break;
            case 34:
                playerJlbl.setBounds(640, 280, Constants.CarDim, Constants.CarDim);
                break;
            case 35:
                playerJlbl.setBounds(640, 340, Constants.CarDim, Constants.CarDim);
                break;
            case 36:
                playerJlbl.setBounds(640, 390, Constants.CarDim, Constants.CarDim);
                break;
            case 37:
                playerJlbl.setBounds(640, 450, Constants.CarDim, Constants.CarDim);
                break;
            case 38:
                playerJlbl.setBounds(640, 500, Constants.CarDim, Constants.CarDim);
                break;
            case 39:
                playerJlbl.setBounds(640, 560, Constants.CarDim, Constants.CarDim);
                break;
            case 0:
                playerJlbl.setBounds(640, 620, Constants.CarDim, Constants.CarDim);
                break;
            default:
                break;
        }
    }
}
