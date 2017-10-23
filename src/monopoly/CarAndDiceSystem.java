/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CarAndDiceSystem {

    int d1, d2, res;
    protected javax.swing.Timer t;
    private Player player;

    //For dice shuffling
    private int diceTimerCounter;
    private javax.swing.Timer diceTimer;

    //Timer milliseconds
    private int timerMs = 150;

    public CarAndDiceSystem() {

        //For testing, speed things up
        if (Constants.testing) {
            timerMs = 20;
        }

        

        t = new javax.swing.Timer(timerMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                MoveOneCity();
                Constants.gameWindow.drawCurrentCard(player.currentCity);
                res--;
                
                if (res == 0) {
                    Constants.gameWindow.enableRollDiceBtn();
                    Constants.gameWindow.drawCity(player.currentCity);
                    
                    if(player.currentCity==2||player.currentCity==17||player.currentCity==33){
                    //Community Cards Function
                    }
                    else if(player.currentCity==7||player.currentCity==22||player.currentCity==36){
                        //chance Cards Function
                    }
                    else if(player.currentCity==5||player.currentCity==15||player.currentCity==25||player.currentCity==35){
                        //RailRoads Function
                    }
                    else if(player.currentCity==12||player.currentCity==28){
                        //Company's Function
                    }
                    else if(player.currentCity==4||player.currentCity==38)
                    {
                        //Pay or income Tax ( 7aga kda ) 
                    }
                    else if(player.currentCity==30)
                    {
                        //Go to Jail
                    }
                    else{
                        //NormalCities
                    //check if it's owned by current Player
                    Boolean isOwnedByCurrPlayer=checkIfOwnedByCurrPlayer(player.currentCity);
                    if(isOwnedByCurrPlayer)
                    {
                        JOptionPane.showConfirmDialog(null, "Do you want to build ?");
                        //Build Function() 
                    }
                    else if(isOwned(player.currentCity)){
                        PayRent(player.currentCity,Player.curPlayer);
                    }
                    else{
                        //If the city doesn't belong to him or to any Player
                        askToBuy();
                    }
                    //Forwart to next Turn
                    if (!(d1 == d2)) {
                        Player.MoveTurn(false);
                    } else {
                        Player.MoveTurn(true);
                    }

                    t.stop();
                }
                }
            }
        }
        );

        //For dice shuffling
        diceTimer = new javax.swing.Timer(timerMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // I made it local, in the actionPerformed method because when using the random variable many times causes a bug 
                //It's better to initialize a new random every time we want one.
                Random rand = new Random();
                diceTimerCounter--;
                d1 = rand.nextInt(6) + 1;
                d2 = rand.nextInt(6) + 1;
                //check if d1 == d2 to play again

                res = d1 + d2;
                //Constants.window.getjTextArea1().setText("d1: " + d1 + "\nd2: " + d2);
                //load dice image
                ImageIcon icon = loadImageOfDice(d1);
                Constants.gameWindow.get_d1_label().setIcon(icon);
                //load dice image
                icon = loadImageOfDice(d2);
                Constants.gameWindow.get_d2_label().setIcon(icon);

                if (diceTimerCounter == 0) {
                    t.start();
                    diceTimer.stop();
                }
            }
        }
        );

    }
        
    public void GenerateDiceAndMove() {

        Constants.gameWindow.disableRollDiceBtn();

        player = Player.curPlayer;
        //Start Dice Throw
        diceTimerCounter = 5;
        diceTimer.start();

    }

    public ImageIcon loadImageOfDice(int dice) {
        return new javax.swing.ImageIcon(getClass().getResource("/drawables/" + dice + ".png"));

    }

    public void LoadImageOfPlayer(String dist) {
        JLabel playerJlbl = JGameWindow.curLabel;
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/drawables/Car" + dist + "" + Player.curPlayer.num + ".png"));
        playerJlbl.setIcon(icon);
        playerJlbl.setBounds(playerJlbl.getX(), playerJlbl.getY(), icon.getIconWidth(), icon.getIconHeight());

    }

    public void MoveOneCity() {
        player = Player.curPlayer;
        JLabel playerJlbl = JGameWindow.curLabel;

//        forget about all the constants and complex operations, 
//        it's just to make the cars resizable and moving in a the exact places
//        simply, all the cars starts at fixed position, 
//        this function moves the car one step(up left right depinding on current city) 
//        by addin(or subtracting) Constants.CityWidth
    if (player.currentCity >= 0 && player.currentCity <= 9) {
            
            playerJlbl.setLocation(playerJlbl.getX() - Constants.CityWidth,
                    playerJlbl.getY());
        }

        if (player.currentCity >= 10 && player.currentCity <= 19) {
            playerJlbl.setLocation(playerJlbl.getX(),
                    playerJlbl.getY() - Constants.CityWidth);
        }

        if (player.currentCity >= 20 && player.currentCity <= 29) {
            playerJlbl.setLocation(playerJlbl.getX() + Constants.CityWidth,
                    playerJlbl.getY());
        }

        if (player.currentCity >= 30 && player.currentCity <= 39) {
            playerJlbl.setLocation(playerJlbl.getX(),
                    playerJlbl.getY() + Constants.CityWidth);
        }
        

        //increment the player current city by the extra moves (movesNum)
        player.currentCity++;

        //beacuse there are 40 cities
        player.currentCity = player.currentCity % 40;

        //reached corner if currentCity is 10 20 30 OR zero, special case cause we can't just add CityWidth to Move,
        //we shall change the car direction(Updatephoto) and move a larger step 
        //corners are bigger (in pixels) than regular cities
        if (player.currentCity % 10 == 0) {
            Corner();
        }

    }

    public void Corner() {

        JLabel playerJlbl = JGameWindow.curLabel;
        switch (player.currentCity) {
            case 0:
                LoadImageOfPlayer("Left");
                playerJlbl.setLocation(Constants.BoardWidth - Constants.CornerWidth + (Constants.CityWidth - Constants.CarWidth),
                        Constants.BoardHeight - Constants.CarHeight - (player.num - 1) * Constants.Carlvl);
                break;

            case 10:
                LoadImageOfPlayer("UP");
                playerJlbl.setLocation(0 + (player.num - 1) * Constants.Carlvl,
                        Constants.BoardHeight - Constants.CornerHeight + (Constants.CityWidth - Constants.CarWidth));
                break;

            case 20:
                LoadImageOfPlayer("Right");
                playerJlbl.setLocation(Constants.CornerWidth - Constants.CarWidth - (Constants.CityWidth - Constants.CarWidth) / 2, (player.num - 1) * Constants.Carlvl);
                break;

            case 30:
                LoadImageOfPlayer("Down");
                playerJlbl.setLocation(Constants.BoardWidth - Constants.CarHeight - (player.num - 1) * Constants.Carlvl, Constants.CornerHeight - Constants.CarWidth - (Constants.CityWidth - Constants.CarWidth));
                break;

        }
    }
    
    public Boolean checkIfOwnedByCurrPlayer(int CityNum)
    {
        //check if the city is owned by the current Player
        for(int i =0;i<player.getCitiesOwned().size();i++)
        {
            if((int)player.getCitiesOwned().get(i)==player.currentCity)
            {
                return true;

            }
        }
        return false;
    }
    
    public Boolean isOwned(int CityNum)
    {
        if(Monopoly.board.normalCities.get(CityNum).owned==true)
            return true;
        return false;
    }
    
    public void BuyCity(int city,Player player){
        
        Monopoly.board.normalCities.get(city).owned=true;
        
        for(int i=0;i<Player.playersList.size();i++)
        {   
            if(Player.playersList.get(i)==player){
                Boolean n = Player.playersList.get(i).buy(city, Monopoly.board.normalCities.get(city).price);
                Monopoly.board.normalCities.get(city).owner=i;
                if(n){
                    JOptionPane.showMessageDialog(null, "Congratulations, now you own "+ Monopoly.board.normalCities.get(city).name);
                }
                else{
                    JOptionPane.showConfirmDialog(null, "You don't have enough money");
                }
                
            }
                
        }
    }
    
    public void PayRent(int city,Player player)
    {
        JOptionPane.showConfirmDialog(null, "Unfortunately,This city is owned by Player "+Player.playersList.get(Monopoly.board.normalCities.get(city).owner).num+ " so you will have to pay him a rent");
        for(int i=0;i<Player.playersList.size();i++)
        {   
            if(Player.playersList.get(i)==player){
                Player.playersList.get(i).deductMoney(Monopoly.board.normalCities.get(city).OverallRent);
                Player.playersList.get(Monopoly.board.normalCities.get(city).owner).addMoney(Monopoly.board.normalCities.get(city).OverallRent);
                
            }
                
        }
        
    }
    
    public void askToBuy(){
        normalCity currentCity = Monopoly.board.normalCities.get(player.currentCity);
        String CityInfo = "\nPrice:"+currentCity.price+
                                "\nRent: "+currentCity.rent+
                                "\nRent of 1 house: "+currentCity.rent_1house+
                                "\nRent of 2 houses: "+currentCity.rent_2house+
                                "\nRent of 3 houses: "+currentCity.rent_3house+
                                "\nRent of 4 houses: "+currentCity.rent_4house+
                                "\nRent of Hotel:"+currentCity.rent_hotel;
        String[] options = {"Buy", "Don't Buy"};
        int choice=JOptionPane.showOptionDialog(null, "You stopped at "+
                currentCity.name+
                CityInfo+"\nDo you want to buy it ?","",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null ,options,options[0]);
        if(choice==0){
            BuyCity(player.currentCity,Player.curPlayer);
        }
    }

}
