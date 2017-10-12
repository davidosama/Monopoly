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
}
