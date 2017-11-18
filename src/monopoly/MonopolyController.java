/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MonopolyController {

    private static int JAILPOSITION = 10;
    int d1, d2, steps;
    private Player curPlayer;
    //For dice shuffling
    private int diceTimerCounter;
    private javax.swing.Timer diceTimer;

    private int doubleDiceCount = 0;
    private Random rand;

    // runnable interface for the thread
    private Runnable carRunnable;

    public MonopolyController() {

        curPlayer = Player.getPlayer();

        //For testing, speed things up
        if (Constants.testing) {
            Constants.timerMs = 60;
        }

        carRunnable = new Runnable() {
            @Override
            public void run() {
                if (doubleDiceCount == 1) {//Move player to jail in case of 3 double dices happened
                    moveToJail();
                    Constants.gameWindow.enableEndTurnBtn(true);
                } else {
                    if (!curPlayer.inJail) {
                        move();
                    }
                    Constants.gameWindow.hidePlayerInfoWindow();
                    Location L = Constants.board.getLocation(curPlayer.position);

                    if (L.type.equals("community") || L.type.equals("chance")) {
                        doCard();
                    } else if (L.type.equals("railroad") || L.type.equals("city") || L.type.equals("company")) {
                        property((Property) L);
                    } else if (L.type.equals("gotojail")) {
                        moveToJail();
                        Constants.gameWindow.enableEndTurnBtn(true);
                        return;
                    } else if (L.type.equals("jail") && curPlayer.inJail) {
                        if (curPlayer.turnsInJail < 3 && d1 != d2) {
                            curPlayer.turnsInJail++;
                            String[] options = {"Yes", "No"};
                            int choice = JOptionPane.showOptionDialog(null, "Pay $50 to exit from jail?", "",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                            if (choice == 0) {
                                curPlayer.deductMoney(50);
                                curPlayer.turnsInJail = 0;
                                curPlayer.inJail = false;
                            }
                            
                        } else if (curPlayer.turnsInJail < 3 && d1 == d2) {
                            curPlayer.turnsInJail = 0;
                            curPlayer.inJail = false;
                            
                        } else if (curPlayer.turnsInJail == 3 && d1 != d2) {
                            curPlayer.deductMoney(50);
                            curPlayer.turnsInJail = 0;
                            curPlayer.inJail = false;
                            
                            
                        }
                        Constants.gameWindow.enableDicePanel(false);
                        
                    } else if (L.type.equals("supertax")) {
                        showDialog( "You have to pay a tax of $75");
                        curPlayer.deductMoney(75);
                    } else if (L.type.equals("incometax")) {
                        showDialog( "You have to pay a tax of $200");
                        curPlayer.deductMoney(200);

                    }

                    if (!(d1 == d2)) {
                        Constants.gameWindow.enableEndTurnBtn(true);
                    } else {
                        Constants.gameWindow.enableRollDiceBtn();
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
                steps = d1 + d2;
                //just for testing

                Constants.gameWindow.drawDice(d1, d2);

                if (diceTimerCounter == 0) {
                    if (d1 == d2) {
                        doubleDiceCount++;
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
        Constants.gameWindow.enableDicePanel(false);
    }

    public void GenerateDiceAndMove() {
        //Start Dice Throw
        diceTimerCounter = 5;
        diceTimer.start();

    }

    public void switchTurn() {
        //Forwart to next Turn
        Player.MoveTurn();
        curPlayer = Player.getPlayer();
        Constants.gameWindow.enableEndTurnBtn(false);
        Constants.gameWindow.enableRollDiceBtn();
        doubleDiceCount = 0;

    }

    public void moveToJail() {
        showDialog( "You're going to jail");
        if (curPlayer.position > JAILPOSITION) {
            steps = 40 - (curPlayer.position - JAILPOSITION);
        } else {
            steps = JAILPOSITION - curPlayer.position;
        }
        curPlayer.inJail = true;
        
        move();

    }

    public void PayRent(Player owner, Property p) {
        if (!p.isMortgaged) { //check if the property is not mortgaged
            showDialog( "Unfortunately, This property is owned by " + owner.name + ", so you will have to pay him a rent");
            if (p.type.equals("company")) {
                int x;
                if (owner.numberOfCompanies == 1) {
                    x = 4;
                } else {
                    x = 10;
                }
                curPlayer.deductMoney(x * steps);
                owner.addMoney(x * steps);
            } else {
                curPlayer.deductMoney(p.curRent);
                owner.addMoney(p.curRent);
            }
        }
    }

    public void askToBuy() {
        int choice = Constants.gameWindow.startAskToBuyorAuction(curPlayer.position);
        Property p = ((Property) Constants.board.allCities.get(curPlayer.position));

        int price = p.price;
        Player buyer = curPlayer;
        if (choice == 1) {
            int [] arr = Constants.gameWindow.startAuction(curPlayer.num); // this function returns 2 values, the winner and the highest bid
            int winner = arr[0];
            showDialog( Player.getName(winner) + " has won the auction");
            buyer = Player.playersList.get(winner);
            price = arr[1];
        }

        boolean n = buyer.buy(p.position, price);
        if (n) {
            p.owner = buyer.num;
            if (p.type.equals("company")) {
                buyer.numberOfCompanies++;
            } else if (p.type.equals("railroad")) {
                buyer.numberOfRailRoads++;
            } else {
                normalCity c = (normalCity) p;
                buyer.updateGroups(c.colorID);
            }

            updateCurrentRent(p);
            showDialog( "Congratulations, now you own " + Constants.board.allCities.get(p.position).name);
        } else {
            showDialog("You don't have enough money");
        }
    }

    public void property(Property p) {

        if (p.owner == -1) {
            askToBuy();
        } else if (p.owner != curPlayer.num) {
            Player owner = Player.playersList.get(p.owner);
            PayRent(owner, p);
        }

    }

    public void updateCurrentRent(Property p) {
        Player owner = Player.playersList.get(p.owner);
        if (p.type.equals("railroad")) {
            p.curRent = (owner.numberOfRailRoads * p.rent);
        } else if (p.type.equals("city")) {
            switch (((normalCity) p).houses_count) {

                case 0:
                    p.curRent = ((normalCity) p).rent;
                    break;
                case 1:
                    p.curRent = ((normalCity) p).rent_1house;
                    break;
                case 2:
                    p.curRent = ((normalCity) p).rent_2house;
                    break;
                case 3:
                    p.curRent = ((normalCity) p).rent_3house;
                    break;
                case 4:
                    p.curRent = ((normalCity) p).rent_4house;
                    break;
                case 5:
                    p.curRent = ((normalCity) p).rent_hotel;
                    break;
                default:
                    break;
            }
        }
    }

    private void doCard() { //to take a card 

        ArrayList<Player> players = Player.playersList;
        int playerNum = curPlayer.num; // the number of the player
        Card curCard = null;
        Location L = Constants.board.getLocation(curPlayer.position);

        if (L.type.equalsIgnoreCase("chance")) {
            //removing a card and then adding it to the bottom
            curCard = Card.chanceCards.remove(0);
            Card.chanceCards.add(curCard);

        } else if (L.type.equalsIgnoreCase("community")) {
            curCard = Card.communityCards.remove(0);
            Card.communityCards.add(curCard);
        }

        JOptionPane.showMessageDialog(Constants.gameWindow.getBoardLabel(), null, null, JOptionPane.PLAIN_MESSAGE, Constants.gameWindow.getChanceCard(curCard.id));
        // to do the commands of the card taken 
        int value;

        switch (curCard.key) {
            case "Take":
                curPlayer.addMoney(curCard.value);
                break;

            case "Give":
                curPlayer.deductMoney(curCard.value);
                break;

            case "GiveAll":
                int toincrease = curCard.value;
                value = curCard.value * (players.size() - 1);

                for (int i = 0; i < players.size(); i++) {
                    if (i != playerNum) {
                        players.get(i).addMoney(toincrease);
                    } else {
                        players.get(i).deductMoney(value);
                    }

                    break;
                }

            case "TakeAll":
                int toreduce = curCard.value;
                value = curCard.value * (players.size() - 1);
                for (int i = 0; i < players.size(); i++) {
                    if (i == playerNum) {
                        players.get(i).addMoney(value);
                    } else {
                        players.get(i).deductMoney(toreduce);
                    }
                }
                break;

            case "Go":
                if (curCard.value >= curPlayer.position) {
                    steps = curCard.value - curPlayer.position;
                } else {
                    steps = 40 - (curPlayer.position - curCard.value);
                }
                carRunnable.run();

        }

    }

    public void Mortgage(int city) {
        curPlayer.mortgage(city);

    }

    public void UnMortgage(int city) {
        curPlayer.unmortgage(city);

    }

    public boolean buildHouse(int city) {

        int chosenColorID;
        if (Constants.board.allCities.get(city).type.equals("city")) {

            if (!(curPlayer.getCitiesOwned().contains(city))) {
                showDialog( "You don't own this city to build on it");
            } else if (((normalCity) Constants.board.allCities.get(city)).isMortgaged) {
                showDialog( "You have to unmortgage the city first to build on it");
            } else if (((normalCity) Constants.board.allCities.get(city)).houses_count >= 5) {
                showDialog( "You are not allowed to build any more buildings");
            } else {
                chosenColorID = ((normalCity) Constants.board.allCities.get(city)).colorID;
                if (!curPlayer.Groups.contains(chosenColorID)) {
                    showDialog( "You don't own the whole group to build on this city");
                } else {
                    Boolean acceptedBuilding = curPlayer.buyhouse(city, ((normalCity) Constants.board.allCities.get(city)).houseCost);
                    if (acceptedBuilding) {
                        updateCurrentRent(((normalCity) Constants.board.allCities.get(city)));
                        showDialog( "Congratulations, now you build on" + Constants.board.allCities.get(city).name);
                        return true;
                    } else {
                        showDialog( "You don't have enough money");
                    }
                }
            }
        }
        return false;
    }

    public void endGame() {
        int maximum = 0;
        int winner = -1;
        for (int i = 0; i < Player.playersList.size(); i++) {
            int wealth = Player.playersList.get(i).getMoney();
            ArrayList<Integer> Properties = Player.playersList.get(i).getCitiesOwned();
            for (int j = 0; j < Properties.size(); j++) {
                Property p = Constants.board.getProperty(Properties.get(j));
                if (p.type.equals("city")) {
                    wealth += ((normalCity) p).houseCost * ((normalCity) p).houses_count;
                }
                wealth += p.price;
            }
            if (wealth > maximum) {
                maximum = wealth;
                winner = i;
            }
        }
        showDialog( Player.getName(winner) + " has won with a net worth of: " + maximum);
        System.exit(0);
    }
    public static void showDialog(String s){
        JOptionPane.showMessageDialog(Constants.gameWindow.getBoardLabel(), s);
    }

}
