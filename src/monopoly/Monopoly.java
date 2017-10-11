package monopoly;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Monopoly {

    private static Dimension dim;

    public static void main(String[] args) {

        //get dimens of screen
        dim = Toolkit.getDefaultToolkit().getScreenSize();

        startChoosePlayersWindow();
    }

    private static void startChoosePlayersWindow() {

        //Sets the ChoosePlayersWindow position to the screen center
        Constants.choosePlayersWindow.setLocation(dim.width / 2 - Constants.choosePlayersWindow.getSize().width / 2, dim.height / 2 - Constants.choosePlayersWindow.getSize().height / 2);

        //Show ChoosePlayersWindow
        Constants.choosePlayersWindow.setVisible(true);
    }

    //Creates Players and starts Game window
    public static void CreatePlayers(int number) {

        //Creates and add players into the array
        for (int i = 0; i < number; i++) {
            new Player();
        }
        
        startGameWindow();
    }

    //starts Game window
    private static void startGameWindow() {

        //Hide ChoosePlayersWindow
        Constants.choosePlayersWindow.setVisible(false);

        //Sets the gameWindow position to the screen center
        Constants.gameWindow.setLocation(dim.width / 2 - Constants.gameWindow.getSize().width / 2, dim.height / 2 - Constants.gameWindow.getSize().height / 2);

        //Start gameWindow
        Constants.gameWindow.setVisible(true);
    }

}
