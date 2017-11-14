/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class MonopolyController {

    int d1, d2, steps;
    private Player curPlayer;
    //For dice shuffling
    private int diceTimerCounter;
    private javax.swing.Timer diceTimer;

    private Random rand;

    // runnable interface for the thread
    private Runnable carRunnable;
    private int doubleDicesCount = 0;

    public MonopolyController() {

        //For testing, speed things up
        if (Constants.testing) {
            Constants.timerMs = 60;
        }

        carRunnable = new Runnable() {
            @Override
            public void run() {

                if (doubleDicesCount == 3) {//Move player to jail in case of 3 double dices happened
                    moveToJail();
                    Constants.gameWindow.enableEndTurnBtn(true);
                } else {
                    move();
                    Constants.gameWindow.enableDicePanel(false);
                    Constants.gameWindow.drawDetailedLocation(curPlayer.position);
                    int result = -1;
                    if (curPlayer.position == 2 || curPlayer.position == 17 || curPlayer.position == 33) {
                        result = Card.DoCards("community");
                    } else if (curPlayer.position == 7 || curPlayer.position == 22 || curPlayer.position == 36) {
                        result = Card.DoCards("chance");
                    } else if (curPlayer.position == 5 || curPlayer.position == 15 || curPlayer.position == 25 || curPlayer.position == 35) {
                        //RailRoads Function
                    } else if (curPlayer.position == 12 || curPlayer.position == 28) {
                        //Company's Function
                    } else if (curPlayer.position == 4 || curPlayer.position == 38) {
                        //Pay or income Tax ( 7aga kda ) 
                    } else if (curPlayer.position == 30 || curPlayer.position == 10) {
                        //Go to Jail
                        moveToJail();
                    } else if (curPlayer.position == 30 || curPlayer.position == 0 || curPlayer.position == 10 || curPlayer.position == 20) {
                        ///////////
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
                    if (result != -1) {
                        steps = result;
                        carRunnable.run();
                    } else {
                        if (!(d1 == d2)) {
                            Constants.gameWindow.enableEndTurnBtn(true);
                        } else {
                            Constants.gameWindow.enableRollDiceBtn();
                        }
                    }
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
                steps = d1 + d2;

                Constants.gameWindow.drawDice(d1, d2);

                if (diceTimerCounter == 0) {
                    if (d1 == d2) {
                        doubleDicesCount++;
                    }
                    Thread thread = new Thread(carRunnable);
                    thread.start();
                    diceTimer.stop();
                }
            }
        }
        );
    }

    public void move() {
        Constants.gameWindow.moveCarLabel(steps);
        curPlayer.move(steps);

//        int m = 30-curPlayer.position;
//        for (int i = 0; i < m; i++) {
//            Constants.gameWindow.moveCarLabel();
//        }
//        curPlayer.move(m);
    }

    public void moveToJail() {

        if (curPlayer.position > 10) {
            steps = 40 - (curPlayer.position - 10);
        } else {
            steps = 10 - curPlayer.position;
        }
        move();

    }

    public void GenerateDiceAndMove() {

        curPlayer = Player.getPlayer();
        //Start Dice Throw
        diceTimerCounter = 5;
        diceTimer.start();

    }

    public void switchTurn() {
        //Forwart to next Turn
        Player.MoveTurn();
        doubleDicesCount = 0;
        Constants.gameWindow.enableEndTurnBtn(false);
        Constants.gameWindow.enableRollDiceBtn();

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
        String[] options = {"Buy", "Auction", "Don't Buy"};
        int choice = JOptionPane.showOptionDialog(null, "You stopped at "
                + currentCity.name
                + CityInfo + "\nDo you want to buy it ?", "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            BuyCity(curPlayer.position, curPlayer);
        } else if (choice == 1) {
            Constants.gameWindow.startAuction(curPlayer.num);

        }
    }

    public void endAuction(int winner, int highestbid) {

        Player.playersList.get(winner).buy(curPlayer.position, highestbid);
        JOptionPane.showMessageDialog(null, "Player " + Player.playersList.get(winner).num + " has won the Auction");

    }

}
