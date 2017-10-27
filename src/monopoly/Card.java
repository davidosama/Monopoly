package monopoly;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

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
        int playerNum = curPlayer.num; // the number of the player

        //to determine whether chance or community card
        Card curCard;
        if (type.equals("chance")) {
            curCard = chanceCards.get(0);

        } else {
            curCard = communityCards.get(0);
        }

       // group.get(1).remove(group.get(1).get(0));
        //Card c2 = new Card(CardID, ToDo, value);
        //group.get(1).add(c2);

        // to do the commands of the card taken 
        int value;
        switch (curCard.key) {
            case "Take":
                curPlayer.deductMoney(curCard.value);
                break;
                
            case "Give":
                curPlayer.addMoney(curCard.value);
                break;
                
            case "GiveAll":
                int toincrease = curCard.value;
                value = curCard.value * (Players.size() - 1);
                
                for (int i = 0; i < Players.size(); i++) {
                    if (i != playerNum) {
                        Players.get(i).addMoney(toincrease);
                    } else {
                        Players.get(i).deductMoney(value);
                    }

                    break;
                }

            case "TakeAll":
                int toreduce = curCard.value;
                value = curCard.value * (Players.size() - 1);
                for (int i = 0; i < Players.size(); i++) {
                    if (i == playerNum) {
                        Players.get(i).addMoney(value);
                    } else {
                        Players.get(i).deductMoney(toreduce);
                    }
                }
                break;
                
            case "Go":
                if(curCard.value > player.)
                //needs work
                

        }
        return -1;

    }
}
