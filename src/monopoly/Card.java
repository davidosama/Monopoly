package monopoly;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Card {

    private int id;
    private String key;
    private int value;

    private static ArrayList<Card> communityCards, chanceCards;

    public Card(int id, String Key, int Value) {
        this.id = id;
        this.key = Key;
        this.value = Value;
    }

    static void CreateAllCards() {
        String fileCards = "/text_files/Cards.txt";
        //reading Card from file
        communityCards = new ArrayList<Card>();
        chanceCards = new ArrayList<Card>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Card.class.getResourceAsStream(fileCards)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] CardValues = line.split(",");
                String Key = CardValues[1];
                String Type = CardValues[3];
                int id = Integer.parseInt(CardValues[0]);
                int value = Integer.parseInt(CardValues[2]);
                Card card = new Card(id, Key, value);
                //inserting the card inside the arraylist
                if (Type.equals("Chance")) {
                    chanceCards.add(card);
                } else {
                    communityCards.add(card);
                }
            }
        } catch (Exception e) {
        }
        Collections.shuffle(chanceCards);
        Collections.shuffle(communityCards);
    }

    static int DoCards(String type) { //to take a card 

        ArrayList<Player> players = Player.playersList;
        Player player = Player.getPlayer();
        int playerNum = player.num; // the number of the player
        Card curCard = null;

        if (type.equalsIgnoreCase("chance")) {
            //removing a card and then adding it to the bottom
            curCard = chanceCards.remove(0);
            chanceCards.add(curCard);

        } else if (type.equalsIgnoreCase("community")) {
            curCard = communityCards.remove(0);
            communityCards.add(curCard);
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
