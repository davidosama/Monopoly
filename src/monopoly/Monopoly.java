package monopoly;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Monopoly {

    public static void main(String[] args) {

        //Creating window
        JWindow window = new JWindow();

        //Sets the window position to the screen center
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);

        //Show window
        window.setVisible(true);
    }
}
