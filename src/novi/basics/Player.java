package novi.basics;

import java.util.ArrayList;

public class Player {
    private final String name;
    public ArrayList<PlayingCards> hand;
    private int slokkenGenomen;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
        this.slokkenGenomen = 0;
    }

    public String getName(){
        return name;
    }
    public ArrayList<PlayingCards> getHand(){
        return hand;
    }
    public void clearHand(){
        hand.clear();
    }
    public int getSlokkenGenomen(){
        return slokkenGenomen;
    }
    public String getOverview(){
        String overview = name + "'s deck: ";
        if(hand.size()>0){
            for(PlayingCards kaart: hand){
                overview = overview.concat(kaart.getShorthand() + " ");
            }
        }
        else{
            System.out.println("leeg!");
        }
        overview = overview.concat(" slokken genomen: " + slokkenGenomen);
        return overview;
    }

    public void zuipen(int aantalSlokken){
        slokkenGenomen += aantalSlokken;
    }
    public void giveSlokken(ArrayList<Player> spelers, int aantalSlokken, String doelwit){
        for(Player stakker : spelers){
            if(doelwit.equals(stakker.getName())){
                stakker.zuipen(aantalSlokken);
                break;
            }
        }
    }
}
