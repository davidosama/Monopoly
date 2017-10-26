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
import javax.swing.JOptionPane;

public class CarAndDiceSystem {

    int d1, d2, res;
    protected javax.swing.Timer t;
    private Player curPlayer;
    private JLabel curLabel;

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

        t = new javax.swing.Timer(timerMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MoveOneCity();
                Constants.gameWindow.drawCurrentCard(curPlayer.currentCity);
                res--;

                if (res == 0) {
                    Constants.gameWindow.enableRollDiceBtn();
                    Constants.gameWindow.drawCity(curPlayer.currentCity);

                    if (curPlayer.currentCity == 2 || curPlayer.currentCity == 17 || curPlayer.currentCity == 33) {
                        //Community Cards Function
                    } else if (curPlayer.currentCity == 7 || curPlayer.currentCity == 22 || curPlayer.currentCity == 36) {
                        //chance Cards Function
                    } else if (curPlayer.currentCity == 5 || curPlayer.currentCity == 15 || curPlayer.currentCity == 25 || curPlayer.currentCity == 35) {
                        //RailRoads Function
                    } else if (curPlayer.currentCity == 12 || curPlayer.currentCity == 28) {
                        //Company's Function
                    } else if (curPlayer.currentCity == 4 || curPlayer.currentCity == 38) {
                        //Pay or income Tax ( 7aga kda ) 
                    } else if (curPlayer.currentCity == 30) {
                        //Go to Jail
                    } else {
                        //NormalCities
                        //check if it's owned by current Player
                        Boolean isOwnedByCurrPlayer = checkIfOwnedByCurrPlayer(curPlayer.currentCity);
                        if (isOwnedByCurrPlayer) {
                            JOptionPane.showConfirmDialog(null, "Do you want to build ?");
                            //Build Function() 
                        } else if (isOwned(curPlayer.currentCity)) {
                            PayRent(curPlayer.currentCity, curPlayer);
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
                    t.stop();
                }
            }
        }
        );

        //For dice shuffling
        diceTimer = new javax.swing.Timer(timerMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // I made it local, in the actionPerformed method because when using the random variable many times causes a bug 
                //It's better to initialize a new random every time we want one.
                Random rand = new Random();
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

        curPlayer = Player.getPlayer();
        curLabel = Constants.gameWindow.getCarLabel();
        //Start Dice Throw
        diceTimerCounter = 5;
        diceTimer.start();

    }

    public ImageIcon loadImageOfDice(int dice) {
        return new javax.swing.ImageIcon(getClass().getResource("/drawables/" + dice + ".png"));

    }

    public void LoadImageOfPlayer(String dist) {
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/drawables/Car" + dist + "" + curPlayer.num + ".png"));
        curLabel.setIcon(icon);
        curLabel.setBounds(curLabel.getX(), curLabel.getY(), icon.getIconWidth(), icon.getIconHeight());

    }

    public void MoveOneCity() {

//        forget about all the constants and complex operations, 
//        it's just to make the cars resizable and moving in a the exact places
//        simply, all the cars starts at fixed position, 
//        this function moves the car one step(up left right depinding on current city) 
//        by addin(or subtracting) Constants.CityWidth
        if (curPlayer.currentCity >= 0 && curPlayer.currentCity <= 9) {

            curLabel.setLocation(curLabel.getX() - Constants.CityWidth,
                    curLabel.getY());
        }

        if (curPlayer.currentCity >= 10 && curPlayer.currentCity <= 19) {
            curLabel.setLocation(curLabel.getX(),
                    curLabel.getY() - Constants.CityWidth);
        }

        if (curPlayer.currentCity >= 20 && curPlayer.currentCity <= 29) {
            curLabel.setLocation(curLabel.getX() + Constants.CityWidth,
                    curLabel.getY());
        }

        if (curPlayer.currentCity >= 30 && curPlayer.currentCity <= 39) {
            curLabel.setLocation(curLabel.getX(),
                    curLabel.getY() + Constants.CityWidth);
        }

        //increment the player current city by the extra moves (movesNum)
        curPlayer.currentCity++;

        //beacuse there are 40 cities
        curPlayer.currentCity = curPlayer.currentCity % 40;

        //reached corner if currentCity is 10 20 30 OR zero, special case cause we can't just add CityWidth to Move,
        //we shall change the car direction(Updatephoto) and move a larger step 
        //corners are bigger (in pixels) than regular cities
        if (curPlayer.currentCity % 10 == 0) {
            Corner();
        }

    }

    public void Corner() {

        switch (curPlayer.currentCity) {
            case 0:
                LoadImageOfPlayer("Left");
                curLabel.setLocation(Constants.BoardWidth - Constants.CornerWidth + (Constants.CityWidth - Constants.CarWidth),
                        Constants.BoardHeight - Constants.CarHeight - (curPlayer.num - 1) * Constants.Carlvl);
                break;

            case 10:
                LoadImageOfPlayer("UP");
                curLabel.setLocation(0 + (curPlayer.num - 1) * Constants.Carlvl,
                        Constants.BoardHeight - Constants.CornerHeight + (Constants.CityWidth - Constants.CarWidth));
                break;

            case 20:
                LoadImageOfPlayer("Right");
                curLabel.setLocation(Constants.CornerWidth - Constants.CarWidth - (Constants.CityWidth - Constants.CarWidth) / 2, (curPlayer.num - 1) * Constants.Carlvl);
                break;

            case 30:
                LoadImageOfPlayer("Down");
                curLabel.setLocation(Constants.BoardWidth - Constants.CarHeight - (curPlayer.num - 1) * Constants.Carlvl, Constants.CornerHeight - Constants.CarWidth - (Constants.CityWidth - Constants.CarWidth));
                break;

        }
    }

    public Boolean checkIfOwnedByCurrPlayer(int CityNum) {
        //check if the city is owned by the current Player
        for (int i = 0; i < curPlayer.getCitiesOwned().size(); i++) {
            if ((int) curPlayer.getCitiesOwned().get(i) == curPlayer.currentCity) {
                return true;

            }
        }
        return false;
    }

    public Boolean isOwned(int CityNum) {
        if (Constants.board.allCities.get(CityNum).owned == true) {
            return true;
        }
        return false;
    }

    public void BuyCity(int city, Player player) {

        Constants.board.allCities.get(city).owned = true;

        for (int i = 0; i < Player.playersList.size(); i++) {
            if (Player.playersList.get(i) == player) {
                Boolean n = Player.playersList.get(i).buy(city, Constants.board.allCities.get(city).price);
                Constants.board.allCities.get(city).owner = i;
                if (n) {
                    JOptionPane.showMessageDialog(null, "Congratulations, now you own " + Constants.board.allCities.get(city).name);
                } else {
                    JOptionPane.showConfirmDialog(null, "You don't have enough money");
                }

            }
        }
    }

    public void PayRent(int city, Player player) {
        JOptionPane.showMessageDialog(null, "Unfortunately,This city is owned by Player " + Player.playersList.get(Constants.board.allCities.get(city).owner).num + " so you will have to pay him a rent");
        for (int i = 0; i < Player.playersList.size(); i++) {
            if (Player.playersList.get(i) == player) {
                Player.playersList.get(i).deductMoney(Constants.board.allCities.get(city).OverallRent);
                Player.playersList.get(Constants.board.allCities.get(city).owner).addMoney(Constants.board.allCities.get(city).OverallRent);

            }
        }
    }

    public void askToBuy() {
        normalCity currentCity = (normalCity) Constants.board.allCities.get(curPlayer.currentCity);
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
            BuyCity(curPlayer.currentCity, curPlayer);
        }
    }

}
