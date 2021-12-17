package novi.basics;

import java.util.ArrayList;
import java.util.Scanner;

public class Bussen extends Cardgame{

    private ArrayList<PlayingCards[]> field;
    //constructor weglaten

    public void bussenRonde1(ArrayList<Player> spelers){
        Scanner input = new Scanner(System.in);
        for(Player speler : spelers){ //eerste kaart per speler.
            PlayingCards currentCard = assignAndReturnCard(speler);
            System.out.println(speler.getName() + ", Rood of Zwart?");
            String roodOfZwart = input.nextLine();
            System.out.println("Je kaart is: " + currentCard.getType() + " " + currentCard.getValue());
            if(roodOfZwart.equals(currentCard.getColour())){
                System.out.println("gewonnen!");
            }
            else{
                System.out.println("Helaas! Zuipen!!");
                speler.zuipen(1);
            }
            System.out.println("Totaal aantal slokken van " + speler.getName() + ": " + speler.getSlokkenGenomen() + "\n");
        }
        for(Player speler : spelers){ //tweede kaart per speler
            PlayingCards currentCard = assignAndReturnCard(speler);
            System.out.println(speler.getName() + ", Hoger of Lager?");
            String hogerOfLager = input.nextLine();
            System.out.println("Je tweede kaart is: " + currentCard.getType() + " " + currentCard.getValue());
            String result = "";
            if(currentCard.getHiddenvalue() > speler.getHand().get(0).getHiddenvalue()){
                result = "Hoger";
            }
            else{
                result = "Lager";
            }
            if(hogerOfLager.equals(result)){
                System.out.println("gewonnen!");
            }
            else{
                System.out.println("Helaas! Zuipen!!");
                speler.zuipen(1);
            }
            System.out.println("Totaal aantal slokken van " + speler.getName() + ": " + speler.getSlokkenGenomen() + "\n");

        }
        for(Player speler : spelers) { //Derde kaart per speler
            PlayingCards currentCard = assignAndReturnCard(speler);
            System.out.println(speler.getName() + ", Binnen of Buiten?");
            String binnenOfBuiten = input.nextLine();
            System.out.println("Je derde kaart is: " + currentCard.getType() + " " + currentCard.getValue());
            String result = "";
            if((currentCard.getHiddenvalue() > speler.getHand().get(0).getHiddenvalue() && currentCard.getHiddenvalue() < speler.getHand().get(1).getHiddenvalue()) || (currentCard.getHiddenvalue() > speler.getHand().get(1).getHiddenvalue() && currentCard.getHiddenvalue() < speler.getHand().get(0).getHiddenvalue())){
                result = "Binnen";
            }
            else{
                result = "Buiten";
            }
            if(binnenOfBuiten.equals(result)){
                System.out.println("gewonnen!");
            }
            else{
                System.out.println("Helaas! Zuipen!!");
                speler.zuipen(1);
            }
            System.out.println("Totaal aantal slokken van " + speler.getName() + ": " + speler.getSlokkenGenomen() + "\n");
        }
        for(Player speler : spelers) { //Vierde kaart per speler
            PlayingCards currentCard = assignAndReturnCard(speler);
            System.out.println(speler.getName() + ", Welk type kaart?");
            String typeCard = input.nextLine();
            System.out.println("Je vierde kaart is: " + currentCard.getType() + " " + currentCard.getValue());
            if (typeCard.equals(currentCard.getType())) {
                System.out.println("gewonnen!");
            } else {
                System.out.println("Helaas! Zuipen!!");
                speler.zuipen(1);
            }
            boolean regenboog = true;
            for (PlayingCards kaart : speler.getHand()) {
                if (currentCard.getType().equals(kaart.getType())) {
                    regenboog = false;
                    break;
                }
            }
            if (regenboog) {
                System.out.println("REGENBOOG! Iedereen behalve " + speler.getName() + " zuipen!");
                for(Player regenboogDrinker : spelers){
                    if(regenboogDrinker.getName().equals(speler.getName())){
                        continue;
                    }
                    else{
                        regenboogDrinker.zuipen(1);
                    }
                }
            }
            System.out.println("Totaal aantal slokken van " + speler.getName() + ": " + speler.getSlokkenGenomen() + "\n");
        }
    }

    public void bussenRonde2(ArrayList<Player> spelers){
        System.out.println("hoeveel rijen willen we in de kerstboom?");
        Scanner input = new Scanner(System.in);
        int aantalRijen = input.nextInt();
        //hier wordt het field aangemaakt gebaseerd op het aantal rijen
        this.field = new ArrayList<>();
        for(int rijNummer=0; rijNummer<aantalRijen; rijNummer++){
            PlayingCards[] cardsToAdd = new PlayingCards[rijNummer+1];
            for(int veldKaart=0; veldKaart<rijNummer+1; veldKaart++){
                PlayingCards card = getCardFromDeck();
                card.revealed = false;
                cardsToAdd[veldKaart] = card;
            }
            field.add(cardsToAdd);
        }
        //einde van field aanmaken
        for(PlayingCards[] currentRow : field){
            int slokkenVoorRij = currentRow.length;
            for(PlayingCards currentCard : currentRow){
                currentCard.revealed = true;
                System.out.println("press enter for next card");
                input.nextLine();
                //print field
                for(PlayingCards[] rij : field){
                    System.out.print("rij " + (field.indexOf(rij)+1) + ": ");
                    for(int i=aantalRijen-field.indexOf(rij)-1; i>0; i--)
                        System.out.print(" ");
                    for(PlayingCards card : rij){
                        if(card.revealed){
                            System.out.print(card.getShorthand() + " ");
                        }
                        else{
                            System.out.print("?? ");
                        }
                    }
                    System.out.print("\n");
                }
                for(Player speler : spelers){
                    System.out.println(speler.getOverview());
                }
                //end of print field, moet eigenlijk in een eigen methode
                for(Player speler : spelers){
                    int kaartenNeergelegd = 0;
                    ArrayList<PlayingCards> deckDuplicate = speler.getHand();
                    ArrayList<PlayingCards> kaartenInleveren = new ArrayList<>();
                    for(PlayingCards kaart : deckDuplicate){ //check elke kaart in de spelers hand tegen de huidige kaart.
                        if(kaart.getValue().equals(currentCard.getValue())){
                            kaartenNeergelegd++;
                            kaartenInleveren.add(kaart);
                        }
                    }
                    for(PlayingCards kaart : kaartenInleveren){
                        speler.hand.remove(kaart);
                    }
                    if(kaartenNeergelegd>0){
                        int uitdeelSlokken = kaartenNeergelegd*slokkenVoorRij;
                        System.out.println(speler.getName() + ", je mocht " + (kaartenNeergelegd) + " kaarten neerleggen.\nJe mag nu " + uitdeelSlokken + " slokken uitdelen.");
                        while(uitdeelSlokken>0){
                            System.out.println("Wie is nu de pineut?");
                            String pineut = input.nextLine();
                            System.out.println("En hoeveel mag de pineut drinken?");
                            String aantalSlokken = input.nextLine();
                            speler.giveSlokken(spelers, Integer.parseInt(aantalSlokken), pineut);
                            uitdeelSlokken -= Integer.parseInt(aantalSlokken);
                            if(uitdeelSlokken>0){
                                System.out.println(speler.getName() + ", je mag nog " + uitdeelSlokken + " slokken uitdelen.");
                            }
                        }
                    }
                }
            }
        }

        //hier even het field printen om te zien of dit goed werkt:
        /*
        for(PlayingCards[] rij : field){
            System.out.print("rij " + (field.indexOf(rij)+1) + ": ");
            for(int i=aantalRijen-field.indexOf(rij)-1; i>0; i--)
                System.out.print(" ");
            for(PlayingCards card : rij){
                if(card.revealed){
                    System.out.print(card.getShorthand() + " ");
                }
                else{
                    System.out.print("?? ");
                }
            }
            System.out.print("\n");
        }
        */
    }
}
