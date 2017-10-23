package monopoly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import monopoly.Constants;
import monopoly.Player;

public class Cards {
    private int id ;
    private String Key;
    private int Value ;
    private static ArrayList<ArrayList<Cards>> group = new ArrayList<ArrayList<Cards>>(2); //here exists 2 arraylists inside a bigger list
    public Cards (int id , String Key , int Value){
        this.id=id;
        this.Key=Key;
        this.Value=Value;
    }
    static void CreateAllCards() throws FileNotFoundException, IOException {
        group.add(new ArrayList <Cards>()); // creating arraylist for chance cards
        group.add(new ArrayList <Cards>()); // creating arraylist for community cards
        String fileCards = ".\\src\\text_files\\Cards.txt";
        //reading Cards from file
        try (BufferedReader br = new BufferedReader(new FileReader(fileCards))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] CardValues = line.split(",");
                int idd = Integer.parseInt(CardValues[0]);
                String Keyy = CardValues[1];
                int Value = Integer.parseInt(CardValues[2]);
                String Type = CardValues [3];
                int id = Integer.parseInt(CardValues[0]);
                int value = Integer.parseInt(CardValues[2]);
                Cards card = new Cards (id,Keyy,Value);
                //inserting the card inside the arraylist
                if(Type.equals("Chance")){
                    group.get(0).add(card);
                }
                else{
                    group.get(1).add(card);
                }
                }
            }
        Collections.shuffle(group.get(0));
        Collections.shuffle(group.get(1));
        }
    
    static int DoCards (ArrayList <Player>Players){ //to take a card 
        int id = Player.curPlayer.num; // the number of the player
        int CardID = 0; // the id of the card chosen 
        String ToDo = ""; // the type of the card 
        int value =0; // the value of the card 
        //to determine whether chance or community card
        if(Player.curPlayer.currentCity==7 || Player.curPlayer.currentCity==22 || Player.curPlayer.currentCity==36){
           CardID =group.get(0).get(0).id;
           ToDo = group.get(0).get(0).Key;
           value = group.get(0).get(0).Value;
           group.get(0).remove(group.get(0).get(0));
           Cards c1 = new Cards (CardID,ToDo,value);
           group.get(0).add(c1);
           
        }
        else{
            CardID =group.get(1).get(0).id;
           ToDo = group.get(1).get(0).Key;
           value = group.get(1).get(0).Value;
           group.get(1).remove(group.get(1).get(0));
           Cards c2 = new Cards (CardID,ToDo,value);
           group.get(1).add(c2);
        }
        // to do the commands of the card taken 
        switch (ToDo){
            case "Take":
                for (int i=0;i<Players.size();i++){
                    if(Players.get(i).num==id){
                        Players.get(i).deductMoney(value);
                    }
                }
                break;
             case "Give":
                       for (int i=0;i<Players.size();i++){
                    if(Players.get(i).num==id){
                        Players.get(i).addMoney(value);
                    }
                }
                 break;
             case "GiveAll":
                 int toincrease=value;
                 value = value*(Players.size()-1);
                  for (int i=0;i<Players.size();i++){
                    if(Players.get(i).num!=id){
                        Players.get(i).addMoney(toincrease);
                    }
                    else{
                         Players.get(i).deductMoney(value);
                    }
                    break;
                
        }
                  
             case "TakeAll" :
                 int toreduce = value;
                 value = value*(Players.size()-1);
                 for (int i=0;i<Players.size();i++){
                       if(Players.get(i).num==id){
                        Players.get(i).addMoney(value);
                    }
                    else{
                         Players.get(i).deductMoney(toreduce);
                    }
                 }
                 break;
             case "Go" :
                 return value;
        
    }
        return -1;
    
}
}

