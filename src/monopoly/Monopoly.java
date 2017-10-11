package monopoly;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Monopoly {
    
    //TESTING ONLY
    public static Player p1;
    public static Player p2;


    public static void main(String[] args) {

        //Sets the window position to the screen center
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Constants.window.setLocation(dim.width / 2 - Constants.window.getSize().width / 2, dim.height / 2 - Constants.window.getSize().height / 2);

        //Show window
        Constants.window.setVisible(true);
        
        //Create player 1
        p1 = new Player();
        p2 = new Player();
    }
}
