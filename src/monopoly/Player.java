/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Player {

    //Player number
    public int num;

    private String name;

    private ArrayList<Integer> citiesOwned;

    private int money;

    private boolean active; //will be used if the player is in jail

    //Player label (pic)
    public JLabel label;

    //City Number
    public int currentCity;

    //Array of all the players
    public static ArrayList<Player> playersList = new ArrayList<>();

    public static int playersCount = 0;

    private static int Turn = 0;

    public Player() {

        //initialize current city to zero
        currentCity = 0;

        //initialize player number to the playersCount and increment
        num = ++playersCount;

        //add player in allPlayers List
        playersList.add(this);

        //create a label for the player
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                label = new JLabel();
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/CarLeft" + num + ".png")));
                label.setBounds(620, 620, 45, 50);
                Constants.gameWindow.getJlabel1().add(label);
                Constants.gameWindow.getJlabel1().validate();
                Constants.gameWindow.getJlabel1().repaint();
            }
        });

        //debugPrintPlayer();
    }

    public static void MoveTurn() {

        Turn = (Turn + 1) % playersCount;

    }

    public static Player getPlayer() {
        return playersList.get(Turn);
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

    public boolean deductMoney(int money) {
        if (this.money >= money) {
            this.money -= money;
            return true;
        } else {
            return false;
        }
    }

}
