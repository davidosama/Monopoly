/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CarAndDiceSystem {

    int d1, d2, res;
    private Player curPlayer;
    //For dice shuffling
    private int diceTimerCounter;
    private javax.swing.Timer diceTimer;

    private Random rand;
    
    // runnable interface for the thread
    private Runnable carRunnable;

    public CarAndDiceSystem() {

        //For testing, speed things up
        if (Constants.testing) {
            Constants.timerMs = 60;
        }

        carRunnable = new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < res; i++) {
                    Constants.gameWindow.moveCarLabel();
                }
                
                curPlayer.move(res);
                
                Constants.gameWindow.enableRollDiceBtn();
                Constants.gameWindow.drawCity(curPlayer.position);
                if (curPlayer.position == 2 || curPlayer.position == 17 || curPlayer.position == 33) {
                    //Community Cards Function
                } else if (curPlayer.position == 7 || curPlayer.position == 22 || curPlayer.position == 36) {
                    //chance Cards Function
                } else if (curPlayer.position == 5 || curPlayer.position == 15 || curPlayer.position == 25 || curPlayer.position == 35) {
                    //RailRoads Function
                } else if (curPlayer.position == 12 || curPlayer.position == 28) {
                    //Company's Function
                } else if (curPlayer.position == 4 || curPlayer.position == 38) {
                    //Pay or income Tax ( 7aga kda ) 
                } else if (curPlayer.position == 30 || curPlayer.position == 0 || curPlayer.position == 10 || curPlayer.position == 20) {
                    //Go to Jail
                } else {
                    //NormalCities
                    //check if it's owned by current Player
                    Boolean isOwnedByCurrPlayer = checkIfOwnedByCurrPlayer(curPlayer.position);
                    if (isOwnedByCurrPlayer) {
                        JOptionPane.showConfirmDialog(null, "Do you want to build ?");
                        //Build Function() 
                    } else if (isOwned(curPlayer.position)) {
                        PayRent(curPlayer.position, curPlayer);
                    } else {
                        //If the city doesn't belong to him or to any Player
                        askToBuy();
                    }
                }
                //Forwart to next Turn
                if (!(d1 == d2)) {
                    Player.MoveTurn(false);
                } else {
                    Player.MoveTurn(true);
                }
            }

        };
        rand = new Random();

        //For dice shuffling
        diceTimer = new javax.swing.Timer(Constants.timerMs, new ActionListener() {
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
                    Thread t = new Thread(carRunnable);
                    t.start();
                    diceTimer.stop();
                }
            }
        }
        );
    }

    public void GenerateDiceAndMove() {

        curPlayer = Player.getPlayer();
        //Start Dice Throw
        diceTimerCounter = 5;
        diceTimer.start();

    }

    public ImageIcon loadImageOfDice(int dice) {
        return new javax.swing.ImageIcon(getClass().getResource("/drawables/d" + dice + ".png"));

    }

    public Boolean checkIfOwnedByCurrPlayer(int CityNum) {
        //check if the city is owned by the current Player
        for (int i = 0; i < curPlayer.getCitiesOwned().size(); i++) {
            if ((int) curPlayer.getCitiesOwned().get(i) == curPlayer.position) {
                return true;

            }
        }
        return false;
    }

    public Boolean isOwned(int CityNum) {
        if (((City) Constants.board.allCities.get(CityNum)).owned == true) {
            return true;
        }
        return false;
    }

    public void BuyCity(int city, Player player) {

        ((City) Constants.board.allCities.get(city)).owned = true;

        for (int i = 0; i < Player.playersList.size(); i++) {
            if (Player.playersList.get(i) == player) {
                Boolean n = Player.playersList.get(i).buy(city, ((City) Constants.board.allCities.get(city)).price);
                ((City) Constants.board.allCities.get(city)).owner = i;
                if (n) {
                    JOptionPane.showMessageDialog(null, "Congratulations, now you own " + Constants.board.allCities.get(city).name);
                } else {
                    JOptionPane.showConfirmDialog(null, "You don't have enough money");
                }

            }
        }
    }

    public void PayRent(int city, Player player) {
        JOptionPane.showMessageDialog(null, "Unfortunately,This city is owned by Player " + Player.playersList.get(((City) Constants.board.allCities.get(city)).owner).num + " so you will have to pay him a rent");
        for (int i = 0; i < Player.playersList.size(); i++) {
            if (Player.playersList.get(i) == player) {
                Player.playersList.get(i).deductMoney(((City) Constants.board.allCities.get(city)).OverallRent);
                Player.playersList.get(((City) Constants.board.allCities.get(city)).owner).addMoney(((City) Constants.board.allCities.get(city)).OverallRent);

            }
        }
    }

    public void askToBuy() {
        normalCity currentCity = (normalCity) Constants.board.allCities.get(curPlayer.position);
        String CityInfo = "\nPrice:" + currentCity.price
                + "\nRent: " + currentCity.rent
                + "\nRent of 1 house: " + currentCity.rent_1house
                + "\nRent of 2 houses: " + currentCity.rent_2house
                + "\nRent of 3 houses: " + currentCity.rent_3house
                + "\nRent of 4 houses: " + currentCity.rent_4house
                + "\nRent of Hotel:" + currentCity.rent_hotel;
        String[] options = {"Buy", "Don't Buy"};
        int choice = JOptionPane.showOptionDialog(null, "You stopped at "
                + currentCity.name
                + CityInfo + "\nDo you want to buy it ?", "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            BuyCity(curPlayer.position, curPlayer);
        }
    }

}
