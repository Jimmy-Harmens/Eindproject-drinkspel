package novi.basics;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FuckTheDealer extends Cardgame{
    //unique fields:
    private int[] counter = new int[13];
    private String[] field = new String[13];

    //constructor weglaten
    public FuckTheDealer(){
        super();
        for(int space : counter){
            this.counter[space] = 0;
        }
        for(int i=0;i<13;i++){
            this.field[i] = getDeck().get(i).getValue();
        }
    }

    public void FTDronde1(ArrayList<Player> spelers){
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        Player dealer = spelers.get(rand.nextInt(spelers.size()));
        int dealerPoints = 0;
        Player currentPlayer = spelers.get((spelers.indexOf(dealer)+ 1) % spelers.size());
        System.out.println("De eerste dealer is: " + dealer.getName());
        while(getDeck().size()>0){
            if(dealerPoints == 3){
                System.out.println("Gefeliciteerd " + dealer.getName() + ", je bent niet meer de dealer!");
                dealer = spelers.get((spelers.indexOf(dealer)+ 1) % spelers.size());
                System.out.println(dealer.getName() + " is nu de dealer");
                dealerPoints = 0;
            }
            if(spelers.get((spelers.indexOf(currentPlayer)+1)% spelers.size()) != dealer){
                currentPlayer = spelers.get((spelers.indexOf(currentPlayer)+1)% spelers.size());
            }
            else{
                currentPlayer = spelers.get((spelers.indexOf(currentPlayer)+2) % spelers.size());
            }
            printField();
            System.out.print("\n");
            PlayingCards currentCard = getCardFromDeck();
            System.out.println(currentPlayer.getName() + ", wat is de waarde van de volgende kaart?");
            String guess1 = input.nextLine();
            String guess1Cap = guess1.substring(0,1).toUpperCase() + guess1.substring(1);
            String hint = "";
            if(guess1Cap.equals(currentCard.getValue())){
                System.out.println("Correct! " + dealer.getName() + " drinkt dubbel!");
                dealer.zuipen(2);
                dealerPoints = 0;
            }
            else if(valueToHiddenValue.get(guess1Cap) < currentCard.getHiddenvalue()){
                hint = "hoger";
            }
            else if(valueToHiddenValue.get(guess1Cap) > currentCard.getHiddenvalue()){
                hint = "lager";
            }
            else{
                hint = "een eldritch horror ofzo. Iets ging namelijk heel erg fout";
            }
            if(!guess1Cap.equals(currentCard.getValue())){
                System.out.println("De echte waarde is " + hint + "\n" + currentPlayer.getName() + ", wat denk je nu dat de waarde is?");
                String guess2 = input.nextLine();
                String guess2Cap = guess2.substring(0,1).toUpperCase() + guess2.substring(1);
                if(guess2Cap.equals(currentCard.getValue())){
                    System.out.println("Correct! " + dealer.getName() + " neemt een slok.");
                    dealer.zuipen(1);
                    dealerPoints = 0;
                }
                else{
                    System.out.println("Helaas. De echte waarde was: " + currentCard.getValue());
                    dealerPoints++;
                }
            }

            //veld updaten
            for(int i=0;i<13;i++){
                if(i == currentCard.getHiddenvalue()){
                    counter[i]++;
                }
            }
        }
    }
    public void printField(){
        for(int i=0;i<13;i++){
            System.out.print(field[i] + ": " + counter[i] + " | ");
        }
    }
}
