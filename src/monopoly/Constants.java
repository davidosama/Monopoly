package monopoly;

<<<<<<< HEAD
import java.util.ArrayList;
import javax.swing.JLabel;


public class Constants {

    //Main window
    public static JGameWindow gameWindow = new JGameWindow();

=======
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Constants {

>>>>>>> master
    //Players Number window
    public static JChoosePlayersWindow choosePlayersWindow = new JChoosePlayersWindow();

    //set true for fast debugging
<<<<<<< HEAD
    public static Boolean testing = false;
=======
    public static Boolean testing = true;
>>>>>>> master

    //Car and Dice System
    public static CarAndDiceSystem carSys = new CarAndDiceSystem();

<<<<<<< HEAD
    public static int BoardWidth;
    public static int BoardHeight;


    public static int CarHeight;
    public static int CarWidth;
    
    public static int ResizeFactor = 1;
    public static int CityWidth = ResizeFactor*56;
    public static int CornerWidth = ResizeFactor*108;
    public static int CornerHeight = ResizeFactor*108;
    public static Player curPlayer;
    public static JLabel curLabel;    
    
=======
    public static Color[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    public static int BoardWidth;
    public static int BoardHeight;

    public static int CarHeight;
    public static int CarWidth;

    public static int ResizeFactor = 1;
    public static int CityWidth = ResizeFactor * 59;
    public static int CornerWidth = ResizeFactor * 97;
    public static int CornerHeight = ResizeFactor * 97;
    public static Player curPlayer;
    public static JLabel curLabel;

    //Main window
    public static JGameWindow gameWindow = new JGameWindow();

    public static int Carlvl = 17;
>>>>>>> master

}
