package monopoly;

public class Constants {

    //Main window
    public static JGameWindow gameWindow = new JGameWindow();

    //Players Number window
    public static JChoosePlayersWindow choosePlayersWindow = new JChoosePlayersWindow();

    //useless for now
    public static Boolean testing = true;

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
    
    

}
