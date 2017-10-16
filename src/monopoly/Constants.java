package monopoly;

import java.util.ArrayList;
import javax.swing.JLabel;


public class Constants {

    //Main window
    public static JGameWindow gameWindow = new JGameWindow();

    //Players Number window
    public static JChoosePlayersWindow choosePlayersWindow = new JChoosePlayersWindow();

    //set true for fast debugging
    public static Boolean testing = false;

    //Car and Dice System
    public static CarAndDiceSystem carSys = new CarAndDiceSystem();

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
    

}
