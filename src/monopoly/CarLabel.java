/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 *
 * @author Kord
 */
public class CarLabel extends JLabel {

    private int position;
    private int playerNum;

    public CarLabel(int playerNum) {

        this.position = 0;
        this.playerNum = playerNum;

        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/drawables/CarLeft" + playerNum + ".png"));
        this.setIcon(icon);

        //yes i know it's not the right place for setting these values, i'll fix it later
        Constants.CarHeight = icon.getIconHeight();
        Constants.CarWidth = icon.getIconWidth();

        this.setBounds(Constants.BoardWidth - Constants.CornerWidth + (Constants.CityWidth - Constants.CarWidth), Constants.BoardHeight - icon.getIconHeight() - this.playerNum * Constants.Carlvl - 5,
                icon.getIconWidth(), icon.getIconHeight());

    }

    private GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    public ImageIcon rotateImage(ImageIcon icon, double angleDegrees) {

        // converting an imageicon to a bufferedimage
        BufferedImage image = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g1 = image.createGraphics();
        icon.paintIcon(null, g1, 0, 0);
        g1.dispose();

        //rotating the image
        double angle = (angleDegrees / 180) * Math.PI;
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return new ImageIcon(result);
    }

    public void rotateCar() {
        ImageIcon icon = rotateImage((ImageIcon) this.getIcon(), 90);
        this.setIcon(icon);
        this.setSize(icon.getIconWidth(), icon.getIconHeight());

    }

    public void MoveOneCity() {

//        forget about all the constants and complex operations, 
//        it's just to make the cars resizable and moving in a the exact places
//        simply, all the cars starts at fixed position, 
//        this function moves the car one step(up left right depinding on current city) 
//        by addin(or subtracting) Constants.CityWidth
        if (position >= 0 && position <= 9) {

            this.setLocation(this.getX() - Constants.CityWidth,
                    this.getY());
        }

        if (position >= 10 && position <= 19) {
            this.setLocation(this.getX(),
                    this.getY() - Constants.CityWidth);
        }

        if (position >= 20 && position <= 29) {
            this.setLocation(this.getX() + Constants.CityWidth,
                    this.getY());
        }

        if (position >= 30 && position <= 39) {
            this.setLocation(this.getX(),
                    this.getY() + Constants.CityWidth);
        }

        //increment the player current city by the extra moves (movesNum)
        position++;

        //beacuse there are 40 cities
        position = position % 40;

        //reached corner if currentCity is 10 20 30 OR zero, special case cause we can't just add CityWidth to Move,
        //we shall change the car direction(Updatephoto) and move a larger step 
        //corners are bigger (in pixels) than regular cities
        if (position % 10 == 0) {
            Corner();
        }
        Constants.gameWindow.drawCurrentCard(position);

    }

    public void Corner() {

        rotateCar();
        switch (position) {
            case 0:
                this.setLocation(Constants.BoardWidth - Constants.CornerWidth + (Constants.CityWidth - Constants.CarWidth),
                        Constants.BoardHeight - Constants.CarHeight - playerNum * Constants.Carlvl);
                break;

            case 10:
                this.setLocation(playerNum * Constants.Carlvl,
                        Constants.BoardHeight - Constants.CornerHeight + (Constants.CityWidth - Constants.CarWidth));
                break;

            case 20:
                this.setLocation(Constants.CornerWidth - Constants.CarWidth - (Constants.CityWidth - Constants.CarWidth) / 2, playerNum * Constants.Carlvl);
                break;

            case 30:
                this.setLocation(Constants.BoardWidth - Constants.CarHeight - playerNum * Constants.Carlvl, Constants.CornerHeight - Constants.CarWidth - (Constants.CityWidth - Constants.CarWidth));
                break;

        }
    }

}
