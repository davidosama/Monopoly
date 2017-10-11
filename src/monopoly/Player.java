/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Player {

    private String name;
    public JLabel label;
    public int currentCity;

    public Player() {

        //initialize current city to zero
        currentCity = 0;

        //create a label for the player
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                label = new JLabel();
                label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawables/CarLeft.png")));
                label.setBounds(620, 620, 45, 50);
                Constants.window.getJlabel1().add(label);
                Constants.window.getJlabel1().validate();
                Constants.window.getJlabel1().repaint();
            }
        });
    }
}