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
    protected javax.swing.Timer t;
    private Random rand;
    private Player player;

    //For dice shuffling
    private int diceTimerCounter;
    private javax.swing.Timer diceTimer;

    //Timer milliseconds
    private int timerMs = 150;

    public CarAndDiceSystem() {

        //For testing, speed things up
        if (Constants.testing) {
            timerMs = 20;
        }
        rand = new Random();

        t = new javax.swing.Timer(timerMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MoveOneCity();
                Constants.gameWindow.drawCurrentCard(player.currentCity);
                res--;

                if (res == 0) {
                    Constants.gameWindow.enableRollDiceBtn();
                    Constants.gameWindow.drawCity(player.currentCity);
                    if (!(d1 == d2)) {
                        Player.MoveTurn(false);
                    } else {
                        Player.MoveTurn(true);
                    }

                    t.stop();
                }
            }
        }
        );

        //For dice shuffling
        diceTimer = new javax.swing.Timer(timerMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                diceTimerCounter--;
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

                if (diceTimerCounter == 0) {
                    t.start();
                    diceTimer.stop();
                }
            }
        }
        );

    }

    public void GenerateDiceAndMove() {

        Constants.gameWindow.disableRollDiceBtn();

        player = Constants.curPlayer;
        //Start Dice Throw
        diceTimerCounter = 5;
        diceTimer.start();

    }

    public ImageIcon loadImageOfDice(int dice) {
        return new javax.swing.ImageIcon(getClass().getResource("/drawables/" + dice + ".png"));

    }

    public void LoadImageOfPlayer(String dist) {
        JLabel playerJlbl = player.label;
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/drawables/Car" + dist + "" + Constants.curPlayer.num + ".png"));
        playerJlbl.setIcon(icon);
        playerJlbl.setBounds(playerJlbl.getX(), playerJlbl.getY(), icon.getIconWidth(), icon.getIconHeight());

    }

    public void MoveOneCity() {
        
        JLabel playerJlbl = player.label;

//        forget about all the constants and complex operations, 
//        it's just to make the cars resizable and moving in a the exact places
//        simply, all the cars starts at fixed position, 
//        this function moves the car one step(up left right depinding on current city) 
//        by addin(or subtracting) Constants.CityWidth
        if (player.currentCity >= 0 && player.currentCity <= 9) {
            playerJlbl.setLocation(playerJlbl.getX() - Constants.CityWidth,
                    playerJlbl.getY());
        }

        if (player.currentCity >= 10 && player.currentCity <= 19) {
            playerJlbl.setLocation(playerJlbl.getX(),
                    playerJlbl.getY() - Constants.CityWidth);
        }

        if (player.currentCity >= 20 && player.currentCity <= 29) {
            playerJlbl.setLocation(playerJlbl.getX() + Constants.CityWidth,
                    playerJlbl.getY());
        }

        if (player.currentCity >= 30 && player.currentCity <= 39) {
            playerJlbl.setLocation(playerJlbl.getX(),
                    playerJlbl.getY() + Constants.CityWidth);
        }

        //increment the player current city by the extra moves (movesNum)
        player.currentCity++;

        //beacuse there are 40 cities
        player.currentCity = player.currentCity % 40;

        //reached corner if currentCity is 10 20 30 OR zero, special case cause we can't just add CityWidth to Move,
        //we shall change the car direction(Updatephoto) and move a larger step 
        //corners are bigger (in pixels) than regular cities
        if (player.currentCity % 10 == 0) {
            Corner();
        }

    }

    public void Corner() {

        JLabel playerJlbl = player.label;
        switch (player.currentCity) {
            case 0:
                LoadImageOfPlayer("Left");
                playerJlbl.setLocation(Constants.BoardWidth - Constants.CornerWidth + (Constants.CityWidth - Constants.CarWidth),
                        Constants.BoardHeight - Constants.CarHeight - (player.num - 1) * Constants.Carlvl);
                break;

            case 10:
                LoadImageOfPlayer("UP");
                playerJlbl.setLocation(0 + (player.num - 1) * Constants.Carlvl,
                        Constants.BoardHeight - Constants.CornerHeight + (Constants.CityWidth - Constants.CarWidth));
                break;

            case 20:
                LoadImageOfPlayer("Right");
                playerJlbl.setLocation(Constants.CornerWidth - Constants.CarWidth - (Constants.CityWidth - Constants.CarWidth) / 2, (player.num - 1) * Constants.Carlvl);
                break;

            case 30:
                LoadImageOfPlayer("Down");
                playerJlbl.setLocation(Constants.BoardWidth - Constants.CarWidth - (player.num - 1) * Constants.Carlvl, Constants.CornerHeight - Constants.CarWidth - (Constants.CityWidth - Constants.CarWidth));
                break;

        }
    }

}
