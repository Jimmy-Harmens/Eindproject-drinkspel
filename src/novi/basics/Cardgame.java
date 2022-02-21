package novi.basics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Cardgame {
    private ArrayList<PlayingCards> deck;
    public final HashMap<String,Integer> valueToHiddenValue;
    public Cardgame(){
        this.deck = new ArrayList<>();
        for(int i=0; i<52; i++){
            PlayingCards card = new PlayingCards(i);
            deck.add(card);
        }
        this.valueToHiddenValue = new HashMap<>();
        for(int i=0; i<13; i++){
            valueToHiddenValue.put(deck.get(i).getValue(),deck.get(i).getHiddenvalue());
        }
    }

    public PlayingCards assignAndReturnCard(Player speler){
        Random rand = new Random();
        int cardID = rand.nextInt(deck.size());
        PlayingCards gekozenKaart = deck.get(cardID);
        speler.hand.add(gekozenKaart);
        deck.remove(gekozenKaart);
        return gekozenKaart;
    }
    public PlayingCards getCardFromDeck(){
        Random rand = new Random();
        int cardID = rand.nextInt(deck.size());
        PlayingCards gekozenKaart = deck.get(cardID);
        deck.remove(gekozenKaart);
        return gekozenKaart;
    }
    public ArrayList<PlayingCards> getDeck(){
        return deck;
    }
    public void resetDeck(){
        deck.clear();
        for(int i=0; i<52; i++){
            PlayingCards card = new PlayingCards(i);
            deck.add(card);
        }
    }
}
