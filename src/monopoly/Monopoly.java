package monopoly;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Monopoly {

    private static Dimension dim;

    public static void main(String[] args) {

        Board board = new Board();

        //get dimens of screen
        dim = Toolkit.getDefaultToolkit().getScreenSize();

         //For testing, start with 5 players
        if (Constants.testing) {
            CreatePlayers(5);
        } else {
            startChoosePlayersWindow();
        }
    }

    private static void startChoosePlayersWindow() {

        Constants.choosePlayersWindow = new JChoosePlayersWindow();
        //Sets the ChoosePlayersWindow position to the screen center
        Constants.choosePlayersWindow.setLocationRelativeTo(null);

        //Show ChoosePlayersWindow
        Constants.choosePlayersWindow.setVisible(true);
    }

    //Creates Players and starts Game window
    public static void CreatePlayers(int number) {

        //Creates and add players into the array
        for (int i = 0; i < number; i++) {
            Player.playersList.add(new Player());
        }

       
    }

    //starts Game window
   

}
