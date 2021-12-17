package novi.basics;

import java.util.ArrayList;
import java.util.Random;

public abstract class Cardgame {
    private ArrayList<PlayingCards> deck;

    public Cardgame(){
        ArrayList<PlayingCards> cards = new ArrayList<>();
        for(int i=0; i<52; i++){
            PlayingCards card = new PlayingCards(i);
            cards.add(card);
        }
        this.deck = cards;
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
}
