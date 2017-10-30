/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Player {

    //Player number
    public int num;

    private String name;

    private ArrayList<Integer> citiesOwned;

    private int money;

    private boolean active; //will be used if the player is in jail


    //City Number
    public int currentCity;

    //Array of all the players
    public static ArrayList<Player> playersList = new ArrayList<>();

    public static int playersCount = 0;

    private static int Turn = 0;

    public Player() {

        //initialize current city to zero
        currentCity = 0;
        money = 1000;

        //initialize player number to the playersCount and increment
        num = ++playersCount;
        System.out.println("askdasldkasldkasldkasjldkasldkjaslkdj Player"+num);
        citiesOwned = new ArrayList();


        //debugPrintPlayer();
    }

    public static void MoveTurn(Boolean samePlayer) {
        
        Player curPlayer = getPlayer();

        if (!samePlayer) {
            Turn = (Turn + 1) % playersCount;
            Constants.gameWindow.changeTurn(Turn);

        }

        Constants.gameWindow.setRollBtnClr(Turn + 1);
        Constants.gameWindow.PlayerInfoArea.setText("Money: \n" + curPlayer.money + "\nCities Owned : \n");
        if (curPlayer.citiesOwned.size() == 0) {
            Constants.gameWindow.PlayerInfoArea.append("No Cities");
        }
        for (int i = 0; i < curPlayer.citiesOwned.size(); i++) {
            City c = Constants.board.allCities.get(curPlayer.citiesOwned.get(i));
            Constants.gameWindow.PlayerInfoArea.append("Name:" + c.name + "Price: " + c.price + "Overall Rent" + c.OverallRent);
        }
    }

    public static Player getPlayer() {
        return playersList.get(Turn);
    }


    public ArrayList getCitiesOwned() {
        return this.citiesOwned;
    }

    private void debugPrintPlayer() {

        System.out.print("\n\nplayer Number: " + num
                + "\nplayers Count: " + playersCount
                + "\nplayer Turn: " + Turn
                + "\ncurrentCity: " + currentCity
        );
    }

    public boolean buy(int city, int cost) {
        if (money >= cost) {
            citiesOwned.add(city);
            money -= cost;
            return true;
        } else {
            return false;
        }
    }

    public boolean sell(int city, int cost) {
        if (citiesOwned.contains(city)) {
            citiesOwned.remove(new Integer(city));
            money += cost;
            return true;
        }
        return false;
    }

//    abstract boolean mortgage(int city, int mortgageCost);
//    abstract boolean trade(int city1, int city2);
    public void addMoney(int money) {
        this.money += money;
    }
    
    public int getMoney(){
        return money;
    }
    
    public boolean deductMoney(int money) {
        if (this.money >= money) {
            this.money -= money;
            return true;
        } else {
            return false;
        }
    }
}
