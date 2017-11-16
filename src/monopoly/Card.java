package monopoly;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Card {

    public int id;
    public String key;
    public int value;

    public static ArrayList<Card> communityCards, chanceCards;

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

}
