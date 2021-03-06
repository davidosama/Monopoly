package monopoly;

import java.awt.Color;

public class Constants {

    //Main window
    public static JGameWindow gameWindow;

    public static Board board;

    //set true for fast debugging
    public static Boolean testing = false;

    //Car and Dice System
    public static MonopolyController carSys;

    public static Color[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    public static int BoardWidth;
    public static int BoardHeight;

    public static int ResizeFactor = 1;
    public static int CityWidth = ResizeFactor * 59;
    public static int CornerWidth = ResizeFactor * 95;
    public static int LocationHeight = ResizeFactor * 95;

    //Timer milliseconds
    public static int timerMs = 180;

    public static int Carlvl = 17;

}
