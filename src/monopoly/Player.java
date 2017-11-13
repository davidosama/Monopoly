/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;

public class Player {

    //Player number
    public int num;

    private String name;

    private ArrayList<Integer> citiesOwned;

    private int money;

    private boolean active; //will be used if the player is in jail

    //City Number
    public int position;

    //Array of all the players
    public static ArrayList<Player> playersList = new ArrayList<>();

    public static int playersCount = 0;

    private static int Turn = 0;

    public Player() {

        //initialize current city to zero
        position = 0;
        money = 1000;

        //initialize player number to the playersCount and increment
        num = playersCount++;

        citiesOwned = new ArrayList();

        //debugPrintPlayer();
    }

    public static void MoveTurn() {

        Turn = (Turn + 1) % playersList.size();
        Constants.gameWindow.changeTurn(Turn);
        Constants.gameWindow.setRollBtnClr(Turn);

        Player curPlayer = getPlayer();
        Constants.gameWindow.PlayerInfoArea.setText("Money: \n" + curPlayer.money + "\nCities Owned : \n");
        if (curPlayer.citiesOwned.size() == 0) {
            Constants.gameWindow.PlayerInfoArea.append("No Cities");
        }
        for (int i = 0; i < curPlayer.citiesOwned.size(); i++) {
            City c = ((City) Constants.board.allCities.get(curPlayer.citiesOwned.get(i)));
            Constants.gameWindow.PlayerInfoArea.append("Name:" + c.name + "Price: " + c.price + "Overall Rent" + c.OverallRent);
        }
    }

    public static Player getPlayer() {
        return playersList.get(Turn);
    }

    public ArrayList getCitiesOwned() {
        return this.citiesOwned;
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

    public int getMoney() {
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

    public void move(int steps) {
        if (this.position + steps >= 40) //reached or passed go
        {
            this.money += 200;
        }
        this.position += steps;
        this.position %= 40;
    }
}
