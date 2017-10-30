package monopoly;

import java.awt.Color;

public class Constants {

    //Main window
    public static JGameWindow gameWindow;

    public static Board board;
    public static JPlayerInfo playerInfoWin;

    //set true for fast debugging
    public static Boolean testing = true;

    //Car and Dice System
    public static CarAndDiceSystem carSys;

    public static Color[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    public static int BoardWidth;
    public static int BoardHeight;

    public static int ResizeFactor = 1;
    public static int CityWidth = ResizeFactor * 59;
    public static int CornerWidth = ResizeFactor * 97;
    public static int CornerHeight = ResizeFactor * 97;

    //Timer milliseconds
    public static int timerMs = 180;

    public static int Carlvl = 17;

}
