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


        //Setting car icon based on position (Right,Left,Up..)
               if (player.currentCity >= 0 && player.currentCity <= 9) 
                   playerJlbl.setLocation(playerJlbl.getX() - Constants.CityWidth, 
                                        playerJlbl.getY());
               
               if(player.currentCity >= 10 && player.currentCity <= 19)
                  playerJlbl.setLocation(playerJlbl.getX(),
                                        playerJlbl.getY() - Constants.CityWidth);
              
               if(player.currentCity >= 20 && player.currentCity <= 29)
                  playerJlbl.setLocation(playerJlbl.getX() + Constants.CityWidth,
                                        playerJlbl.getY());
               
               if(player.currentCity >= 30 && player.currentCity <= 39)
                  playerJlbl.setLocation(playerJlbl.getX(),
                                        playerJlbl.getY() + Constants.CityWidth);
 
            
           
        //increment the player current city by the extra moves (movesNum)
        player.currentCity ++;

        //beacuse there are 40 cities
        player.currentCity = player.currentCity % 40;
        
 
     if (player.currentCity %10 == 0) //reached corner
      {   
          switch(player.currentCity) 
               {
              
                     case 0:
                       UpdateImageOfPlayer("Left");
                       playerJlbl.setLocation(Constants.BoardWidth - Constants.CarWidth - Constants.CornerFactor,
                                            Constants.BoardHeight - Constants.CarHeight - 20);
                       break;
                       
                   case 10:
                     UpdateImageOfPlayer("UP");
                     playerJlbl.setLocation(0,
                              Constants.BoardHeight - Constants.CarHeight - Constants.CornerFactor - 20);
                     break;
            
                   case 20:
                       UpdateImageOfPlayer("Right");
                       playerJlbl.setLocation(Constants.CornerFactor,0);
                       break;
                       
                   case 30:
                       UpdateImageOfPlayer("Down");
                       playerJlbl.setLocation(Constants.BoardWidth - Constants.CarWidth, Constants.CornerFactor);
                       break;
                       
            
                }
               
      }
     
   
    }
}
