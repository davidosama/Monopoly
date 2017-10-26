package monopoly;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Card {

    private int id;
    private String Key;
    private int Value;

    private static ArrayList<Card> communityCards, chanceCards;

    public Card(int id, String Key, int Value) {
        this.id = id;
        this.Key = Key;
        this.Value = Value;
    }

    static void CreateAllCards() {
        String fileCards = ".\\src\\text_files\\Cards.txt";
        //reading Card from file
        communityCards = new ArrayList<Card>();
        chanceCards = new ArrayList<Card>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileCards))) {
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

    static int DoCards(ArrayList<Player> Players, String type) { //to take a card 
        Player curPlayer = Player.getPlayer();
        int id = curPlayer.num; // the number of the player
        int CardID = 0; // the id of the card chosen 
        String ToDo = ""; // the type of the card 
        int value = 0; // the value of the card 
        //to determine whether chance or community card
        Card curCard;
        if (type.equals("chance")) {
            curCard = chanceCards.get(0);

        } else {
            curCard = communityCards.get(0);
        }

       // group.get(1).remove(group.get(1).get(0));
        Card c2 = new Card(CardID, ToDo, value);
        //group.get(1).add(c2);

        // to do the commands of the card taken 
        switch (curCard.Key) {
            case "Take":
                curPlayer.deductMoney(curCard.Value);

                break;
            case "Give":
                curPlayer.addMoney(curCard.Value);
                break;
            case "GiveAll":
                int toincrease = value;
                value = value * (Players.size() - 1);
                for (int i = 0; i < Players.size(); i++) {
                    if (Players.get(i).num != id) {
                        Players.get(i).addMoney(toincrease);
                    } else {
                        Players.get(i).deductMoney(value);
                    }

                    break;

                }

            case "TakeAll":
                int toreduce = value;
                value = value * (Players.size() - 1);
                for (int i = 0; i < Players.size(); i++) {
                    if (Players.get(i).num == id) {
                        Players.get(id).addMoney(value);
                    } else {
                        Players.get(i).deductMoney(toreduce);
                    }
                }
                break;
            case "Go":
                //needs work
                return value;

        }
        return -1;

    }
}
