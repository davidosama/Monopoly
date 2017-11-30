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

    public String name;
    public String iconName;

    private ArrayList<Integer> propertiesOwned;
    public ArrayList<Integer> Groups;
    public int numberOfCompanies;
    public int numberOfRailRoads;
    private int money;

    //City Number
    public int position;

    //Array of all the players
    public static ArrayList<Player> playersList = new ArrayList<>();

    public static int playersCount = 0;

    private static int turn = 0;

    //Jail variable needed
    public boolean inJail = false;
    public int turnsInJail = 0;

    public Player(String name, String iconName) {

        //initialize current city to zero
        position = 0;
        money = 1000;

        this.name = name;

        //initialize player number to the playersCount and increment
        num = playersCount++;

        propertiesOwned = new ArrayList();
        Groups = new ArrayList();
        numberOfCompanies = 0;
        numberOfRailRoads = 0;

        //debugPrintPlayer();
    }

    public static void MoveTurn() {
        turn = (turn + 1) % playersList.size();
        Constants.gameWindow.changeTurn(turn);
    }

    public static Player getCurrentPlayer() {
        return playersList.get(turn);
    }

    public static Player getPlayer(int n) {

        return playersList.get(n);
    }

    public ArrayList getCitiesOwned() {
        return this.propertiesOwned;
    }

    public ArrayList<Integer> getCitiesOwnedInt() {
        return this.propertiesOwned;
    }

    public boolean buy(int city, int cost) {
        if (money >= cost) {
            propertiesOwned.add(city);
            money -= cost;
            return true;
        } else {
            return false;
        }
    }

    public boolean sell(int city, int cost) {
        if (propertiesOwned.contains(city)) {
            propertiesOwned.remove(new Integer(city));
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
        if (this.position + steps >= 40 && !this.inJail) //reached or passed go
        {
            this.money += 200;
        }
        this.position += steps;
        this.position %= 40;
    }

    public void updateGroups(int ColorID) {
        int occurences = 0;
        for (int i = 0; i < propertiesOwned.size(); i++) {
            //this function needs work
            Property p = Constants.board.getProperty(propertiesOwned.get(i));
            if (p.type.equals("city")) {
                City c = (City) p;
                if (c.colorID == ColorID) {
                    occurences++;
                }
            }

            if (occurences == 3) {
                Groups.add(ColorID);
                break;
            }

            //i tried to replace 0 and 7 with enum but couldn't do it
            if (occurences == 2 && (ColorID == 0 || ColorID == 7)) {
                Groups.add(ColorID);
                break;
            }

        }

    }

    public boolean mortgage(int city) {
        if (this.getCitiesOwnedInt().contains(city)) {
            if (!((Property) Constants.board.getProperty(city)).isMortgaged) {
                if (Constants.board.getProperty(city).type.equals("city")) {
                    City c = (City) Constants.board.getProperty(city);
                    if (c.houses_count != 0) {
                        MonopolyController.showDialog("Sell all houses first before mortgaging the city.");
                        return false;   //city must have zero houses on it
                    }
                }
                Property p = Constants.board.getProperty(city);
                this.addMoney(p.mortgage);
                p.isMortgaged = true;
                MonopolyController.showDialog(p.name + " is now mortgaged.");
                return true;
            } else {
                MonopolyController.showDialog("This property is already mortgaged.");
            }
        } else {
            MonopolyController.showDialog("You don't own this property to mortgage.");
        }

        return false;
    }

    public boolean unmortgage(int city) {
        if (this.getCitiesOwnedInt().contains(city)) {
            if (Constants.board.getProperty(city).isMortgaged) {
                Property p = Constants.board.getProperty(city);
                this.deductMoney(p.mortgage);
                p.isMortgaged = false;
                MonopolyController.showDialog(p.name + " unmortgaged.");
                return true;
            } else {
                MonopolyController.showDialog("This property is already NOT mortgaged.");
            }
        } else {
            MonopolyController.showDialog("You don't own this property to unmortgage.");
        }
        return false;
    }

    public boolean buyhouse(int city, int housesCost) {
        if (money >= housesCost) {
            ((City) Constants.board.allCities.get(city)).houses_count++;
            money -= housesCost;
            return true;
        } else {
            return false;
        }
    }

    public static String getName(int playerNumber) {
        return playersList.get(playerNumber).name;
    }

    public static int getTurn() {
        return turn;
    }

    public boolean giveMoney(int p2, int cash) {
        if (deductMoney(cash)) {
            Player p = getPlayer(p2);
            p.addMoney(cash);
            return true;
        } else {
            return false;
        }

    }

    public boolean giveProperty(int p2, int property) {

        Property prop = Constants.board.getProperty(property);
        if (prop.owner == num) {
            //you should first check if there are built houses
            prop.owner = p2;
            propertiesOwned.remove(prop);
            if (prop instanceof City) {
                City c = (City) prop;

                for (int i = 0; i < Groups.size(); i++) {
                    if (c.colorID == Groups.get(i)) {
                        Groups.remove(i);
                    }
                }
            }

            return true;
        }

        return false;
    }
}
