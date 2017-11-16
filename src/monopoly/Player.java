/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    //Player number
    public int num;

    public String name;
    public String iconName;

    private ArrayList<Integer> propertiesOwned;
    private ArrayList<Integer> Groups;
    public int numberOfCompanies;
    public int numberOfRailRoads;
    private int money;

    private boolean active; //will be used if the player is in jail

    //City Number
    public int position;

    //Array of all the players
    public static ArrayList<Player> playersList = new ArrayList<>();

    public static int playersCount = 0;

    private static int Turn = 0;

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
        numberOfRailRoads=0;

        //debugPrintPlayer();
    }

    public static void MoveTurn() {

        Turn = (Turn + 1) % playersList.size();
        Constants.gameWindow.changeTurn(Turn);
        Constants.gameWindow.setRollBtnClr(Turn);

        Player curPlayer = getPlayer();
       /* Constants.gameWindow.PlayerInfoArea.setText("Money: \n" + curPlayer.money + "\nCities Owned : \n");
        if (curPlayer.propertiesOwned.size() == 0) {
            Constants.gameWindow.PlayerInfoArea.append("No Cities");
        }
        for (int i = 0; i < curPlayer.propertiesOwned.size(); i++) {
            Property c = ((Property) Constants.board.allCities.get(curPlayer.propertiesOwned.get(i)));
            Constants.gameWindow.PlayerInfoArea.append("Name:" + c.name + "Price: " + c.price + "Overall Rent" + c.OverallRent);
        }*/
    }

    public static Player getPlayer() {
        return playersList.get(Turn);
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
        if (this.position + steps >= 40) //reached or passed go
        {
            this.money += 200;
        }
        this.position += steps;
        this.position %= 40;
    }

   
    
    public void updateGroups(int ColorID)
    {
     int occurences=0;
     for(int i=0; i<propertiesOwned.size();i++)
        {
            //this function needs work
            Property p = Constants.board.getProperty(propertiesOwned.get(i));
            if(p.type.equals("city"))
            {
             normalCity c = (normalCity) p;
             if(c.colorID == ColorID) occurences++;
            }
            
            if(occurences==3) {Groups.add(ColorID); break;}
            
            //i tried to replace 0 and 7 with enum but couldn't do it
            if(occurences==2&&(ColorID==0||ColorID==7))
            {Groups.add(ColorID);break;}
            
        }
     
     System.out.println(Groups);
     
     }
    
    
    

}
