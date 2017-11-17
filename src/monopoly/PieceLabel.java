/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

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
public class PieceLabel extends JLabel {

    private int position;
    private int playerNum;

    public PieceLabel(int playerNum, String iconName) {

        this.position = 0;
        this.playerNum = playerNum;

        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/drawables/objects/" + iconName + ".png"));
        this.setIcon(icon);
        this.setBounds(Constants.BoardWidth - Constants.CornerWidth + (Constants.CityWidth - icon.getIconWidth()), Constants.BoardHeight - icon.getIconHeight() - this.playerNum * Constants.Carlvl - 5,
                icon.getIconWidth(), icon.getIconHeight());

    }

    private GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    private BufferedImage getBufferedImage(ImageIcon icon) {

        BufferedImage image = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return image;
    }

    private ImageIcon rotateImageIcon(ImageIcon icon, double angleDegrees) {

        // converting an imageicon to a bufferedimage
        BufferedImage image = getBufferedImage(icon);
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

    private void rotatePiece() {
        ImageIcon rotatedIcon = rotateImageIcon((ImageIcon) this.getIcon(), 90);
        this.setIcon(rotatedIcon);
        this.setSize(rotatedIcon.getIconWidth(), rotatedIcon.getIconHeight());

    }

    public void MoveOneCity() {

//        forget about all the constants and complex operations, 
//        it's just to make the cars resizable and moving in a the exact places
//        simply, all the cars starts at fixed position, 
//        this function moves the car one step(up left right depinding on current city) 
//        by addin(or subtracting) Constants.CityWidth
        for (int i = 0; i < Constants.CityWidth; i++) {
            if (position >= 0 && position <= 9) {
                this.setLocation(this.getX() - 1,
                        this.getY());
            }

            if (position >= 10 && position <= 19) {
                this.setLocation(this.getX(),
                        this.getY() - 1);
            }

            if (position >= 20 && position <= 29) {
                this.setLocation(this.getX() + 1,
                        this.getY());
            }

            if (position >= 30 && position <= 39) {
                this.setLocation(this.getX(),
                        this.getY() + 1);
            }
            try {
                Thread.sleep(Constants.timerMs / Constants.CityWidth);
            } catch (Exception e) {
            }
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
        Constants.gameWindow.drawCurrentLocation(position);
        Constants.gameWindow.drawDetailedLocation(-1);

    }

    private void Corner() {

        rotatePiece();
        switch (position) {
            case 0:
                this.setLocation(Constants.BoardWidth - Constants.CornerWidth + (Constants.CityWidth - this.getWidth()),
                        Constants.BoardHeight - this.getHeight() - playerNum * Constants.Carlvl);
                break;

            case 10:
                this.setLocation(playerNum * Constants.Carlvl,
                        Constants.BoardHeight - Constants.LocationHeight + (Constants.CityWidth - this.getHeight()));
                break;

            case 20:
                this.setLocation(Constants.CornerWidth - this.getWidth() - (Constants.CityWidth - this.getWidth()) / 2, playerNum * Constants.Carlvl);
                break;

            case 30:
                this.setLocation(Constants.BoardWidth - this.getWidth() - playerNum * Constants.Carlvl, Constants.LocationHeight - this.getHeight() - (Constants.CityWidth - this.getHeight()));
                break;

        }
    }

}
