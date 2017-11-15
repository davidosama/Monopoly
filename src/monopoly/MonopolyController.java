/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MonopolyController {

    int d1, d2, res;
    private Player curPlayer;
    //For dice shuffling
    private int diceTimerCounter;
    private javax.swing.Timer diceTimer;

    private Random rand;

    // runnable interface for the thread
    private Runnable carRunnable;

    public MonopolyController() {

        //For testing, speed things up
        if (Constants.testing) {
            Constants.timerMs = 60;
        }

        carRunnable = new Runnable() {
            @Override
            public void run() {
                move();
               
                Constants.gameWindow.enableDicePanel(false);
                Constants.gameWindow.drawDetailedLocation(curPlayer.position);
                
                int result = -1;
                property p = Constants.board.getProperty(curPlayer.position);
                
                if(p.type.equals("community") || p.type.equals("chance")) result = doCard();
                else if(p.type.equals("railroad")||p.type.equals("city") || p.type.equals("company")) property(p);
               
                if (result != -1) {
                    res = result;
                    carRunnable.run();
                } else {
                    if (!(d1 == d2)) {
                        Constants.gameWindow.enableEndTurnBtn(true);
                    } else {
                        Constants.gameWindow.enableRollDiceBtn();
                    }
                }
            }

        };
        rand = new Random();

        //For dice shuffling
        diceTimer = new javax.swing.Timer(Constants.timerMs, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                diceTimerCounter--;
                d1 = rand.nextInt(6) + 1;
                d2 = rand.nextInt(6) + 1;
                res = d1 + d2;
                 //just for testing
                
                
                Constants.gameWindow.drawDice(d1, d2);

                if (diceTimerCounter == 0) {
                    Thread thread = new Thread(carRunnable);
                    thread.start();
                    diceTimer.stop();
                }
            }
        }
        );
    }

    public void move() {
        for (int i = 0; i < res; i++) {
            Constants.gameWindow.moveCarLabel();
        }
        curPlayer.move(res);
    }

    public void GenerateDiceAndMove() {

        curPlayer = Player.getPlayer();
        //Start Dice Throw
        diceTimerCounter = 5;
        diceTimer.start();

    }

    public void switchTurn() {
        //Forwart to next Turn
        Player.MoveTurn();
        Constants.gameWindow.enableEndTurnBtn(false);
        Constants.gameWindow.enableRollDiceBtn();

    }

    public void PayRent(Player owner, property p) {
       
        JOptionPane.showMessageDialog(null, "Unfortunately,This property is owned by Player " + p.owner + " so you will have to pay him a rent");
       if(p.type.equals("company"))
        {
            int x;
            if(owner.numberOfCompanies==1)
                x = 4;
            else x =10;
            
            curPlayer.deductMoney(x*res); owner.addMoney(x*res);
        }
       
       else
       {
        curPlayer.deductMoney(p.rent);
        owner.addMoney(p.rent);
       }

    }

    public void askToBuy() {
        property p = ((property)Constants.board.allCities.get(curPlayer.position));
        String[] options = {"Buy", "Auction", "Don't Buy"};
        int choice = JOptionPane.showOptionDialog(null, "You stopped at "
                + p.name
                + "\nDo you want to buy it ?", "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            boolean n = curPlayer.buy(p.position,p.price);
              if (n) {
                  p.owner=curPlayer.num;
                  if(p.type.equals("company"))
                      curPlayer.numberOfCompanies++;
                  
                  else if(p.type.equals("railroad"))
                      curPlayer.numberOfRailRoads++;
                  
                  else
                  {
                      normalCity c = (normalCity)p;
                      curPlayer.updateGroups(c.colorID);
                  }
                  
                  updateCurrentRent(p);
                  JOptionPane.showMessageDialog(null, "Congratulations, now you own " + Constants.board.allCities.get(p.position).name);
                } else {
                    JOptionPane.showConfirmDialog(null, "You don't have enough money");
                }
        } else if (choice == 1) {
            Constants.gameWindow.startAuction(curPlayer.num);

        }
    }

    public void endAuction(int winner, int highestbid) {

        Player.playersList.get(winner).buy(curPlayer.position, highestbid);
        JOptionPane.showMessageDialog(null, "Player " + Player.playersList.get(winner).num + " has won the Auction");

    }
               
    public void property(property p)
    {

            if(p.type.equals("city")&&p.owner == curPlayer.num)
                JOptionPane.showConfirmDialog(null, "Do you want to build ?");
            
            else if(p.owner == -1) {askToBuy();}
            else if(p.owner!= curPlayer.num)
            {
                Player owner = Player.playersList.get(p.owner);
                PayRent(owner,p);
            }
        
    }

 public void updateCurrentRent(property p)
    {
        Player owner = Player.playersList.get(p.owner);        
        if(p.type.equals("railroad"))            
          p.curRent = (owner.numberOfRailRoads*p.rent);

        
        
        else if(p.type.equals("city"))
        {            
         //tony   
        }
        
        
    }   
   
    
    static int doCard() { //to take a card 
            
            
        ArrayList<Player> players = Player.playersList;
        Player player = Player.getPlayer();
        int playerNum = player.num; // the number of the player
        Card curCard = null;
        String type = Constants.board.getLocationType(player.position);
                      

        if (type.equalsIgnoreCase("chance")) {
            //removing a card and then adding it to the bottom
            curCard = Card.chanceCards.remove(0);
            Card.chanceCards.add(curCard);

        } else if (type.equalsIgnoreCase("community")) {
            curCard = Card.communityCards.remove(0);
            Card.communityCards.add(curCard);
        } else {
            //
        }

        JOptionPane.showMessageDialog(Constants.gameWindow.getBoardLabel(), null, null, JOptionPane.PLAIN_MESSAGE, Constants.gameWindow.getChanceCard(curCard.id));
        // to do the commands of the card taken 
        int value;

        switch (curCard.key) {
            case "Take":
                player.deductMoney(curCard.value);
                break;

            case "Give":
                player.addMoney(curCard.value);
                break;

            case "GiveAll":
                int toincrease = curCard.value;
                value = curCard.value * (players.size() - 1);

                for (int i = 0; i < players.size(); i++) {
                    if (i != playerNum) {
                        players.get(i).addMoney(toincrease);
                    } else {
                        players.get(i).deductMoney(value);
                    }

                    break;
                }

            case "TakeAll":
                int toreduce = curCard.value;
                value = curCard.value * (players.size() - 1);
                for (int i = 0; i < players.size(); i++) {
                    if (i == playerNum) {
                        players.get(i).addMoney(value);
                    } else {
                        players.get(i).deductMoney(toreduce);
                    }
                }
                break;

            case "Go":
                if (curCard.value >= player.position) {
                    return curCard.value - player.position;
                } else {
                    return 40 - (player.position - curCard.value);
                }

        }
        return -1;

    }

}
