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
        this.raceLengte = Integer.parseInt(raceLengteString)+1;
        this.field = new PlayingCards[raceLengte][5];
    }

    public void kamelenRaceGame(ArrayList<Player> spelers){
        int counter = 0;
        for(PlayingCards[] row : field){
            if(counter != raceLengte-1){
                PlayingCards kaartToevoegen = getCardFromDeck();
                kaartToevoegen.revealed = true;
                row[0] = kaartToevoegen;
            }
            else{
                for(int i=0;i<4;i++){
                    row[i+1] = new PlayingCards(13*i+12);
                }
            }
            counter++;
        }//vul het veld (IMPORTANT! er zijn dus vier extra kaarten in het spel; de asen)

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
