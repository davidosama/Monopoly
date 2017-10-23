package monopoly;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import static monopoly.JGameWindow.curLabel;
import static monopoly.JGameWindow.playersLabels;

public class Monopoly {

    private static Dimension dim;

    public static void main(String[] args) throws InterruptedException, IOException {

        Board board = new Board();
           Cards.CreateAllCards();
        //get dimens of screen
        dim = Toolkit.getDefaultToolkit().getScreenSize();

         //For testing, start with 5 players
        if (Constants.testing) {
            CreatePlayers(5);
        } else {
            startChoosePlayersWindow();
        }
           Cards.DoCards(Player.playersList);
    }

    private static void startChoosePlayersWindow() {

        //Sets the ChoosePlayersWindow position to the screen center
        Constants.choosePlayersWindow.setLocation(dim.width / 2 - Constants.choosePlayersWindow.getSize().width / 2, dim.height / 2 - Constants.choosePlayersWindow.getSize().height / 2);

        //Show ChoosePlayersWindow
        Constants.choosePlayersWindow.setVisible(true);
    }

    //Creates Players and starts Game window
    public static void CreatePlayers(int number) {
        startGameWindow();
        //Creates and add players into the array
        for (int i = 0; i < number; i++) {
            Player.playersList.add(new Player());
            JGameWindow.addLabel(new JLabel());
        }
//wait for the thread that creates the cars, i think there might be a better solution i'll search for it later    
try{Thread.sleep(400);}
 catch (Exception e){}

        curLabel = playersLabels.get(0);
        Player.curPlayer = Player.playersList.get(0);

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
