package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Constants {

    //Players Number window
    public static JChoosePlayersWindow choosePlayersWindow = new JChoosePlayersWindow();

    //set true for fast debugging
    public static Boolean testing = true;

    //Car and Dice System
    public static CarAndDiceSystem carSys = new CarAndDiceSystem();

    public static Color[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    public static int BoardWidth;
    public static int BoardHeight;

    public static int CarHeight;
    public static int CarWidth;

    public static int ResizeFactor = 1;
    public static int CityWidth = ResizeFactor * 59;
    public static int CornerWidth = ResizeFactor * 97;
    public static int CornerHeight = ResizeFactor * 97;

    //Main window
    public static JGameWindow gameWindow = new JGameWindow();

    public static int Carlvl = 17;

}
