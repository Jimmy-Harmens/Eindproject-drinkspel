package novi.basics;

import java.util.ArrayList;
import java.util.Scanner;

public class KamelenRacen extends Cardgame{
    private PlayingCards[][] field;
    private int raceLengte;

    public KamelenRacen(){
        super();
        Scanner input = new Scanner(System.in);
        System.out.println("Hoelang willen we de race?");
        String raceLengteString = input.nextLine();
        this.raceLengte = Integer.parseInt(raceLengteString)+2;
        this.field = new PlayingCards[raceLengte][5];
    }

    public void kamelenRaceGame(ArrayList<Player> spelers){
        int counter = 0;
        for(PlayingCards[] row : field){
            if(counter != raceLengte-1 && counter != 0){
                PlayingCards kaartToevoegen = getCardFromDeck();
                kaartToevoegen.revealed = true;
                row[0] = kaartToevoegen;
            }
            else if(counter != 0){
                for(int i=0;i<4;i++){
                    row[i+1] = new PlayingCards(13*i+12);
                }
            }
            counter++;
        }//vul het veld (IMPORTANT! er zijn dus vier extra kaarten in het spel; de asen)

        boolean hasWinner = false;
        while(!hasWinner){
            PlayingCards comparingCard = getCardFromDeck();
            int rowPosition = 0;
            for(PlayingCards[] row : field){
                int cardPosition = 0;
                for(PlayingCards card : row){
                    if(card != null && cardPosition != 0 && card.getType().equals(comparingCard.getType())){
                        field[rowPosition][cardPosition] = null;
                        field[rowPosition-1][cardPosition] = card;
                        boolean turnoverCard = false;
                        break;
                    }
                    cardPosition++;
                }
                rowPosition++;
            }
            printField();
            System.out.println("");
            for(PlayingCards card : field[0]){
                if(card != null){
                    hasWinner = true;
                    break;
                }
            }
        }
    }

    public void printField(){
        for(PlayingCards[] row: field){
            for(PlayingCards card : row){
                if(card == null){
                    System.out.print("__ ");
                }
                else{
                    if(card.revealed){
                        System.out.print("?? ");
                    }
                    else{
                        System.out.print(card.getShorthand() + " ");
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
