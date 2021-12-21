package novi.basics;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Player> spelers = new ArrayList<>();
        boolean playersComplete = false;
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("Add a player? \nType y or yes to add a player\nType n or no to continue!");
            String addPlayer = input.nextLine();
            if(addPlayer.equals("y")||addPlayer.equals("yes")){
                System.out.println("Player " + (spelers.size()+1) + ", please enter your name:");
                Player newPlayer = new Player(input.nextLine());
                spelers.add(newPlayer);
            }
            else if(addPlayer.equals("n")||addPlayer.equals("no")){
                playersComplete = true;
                System.out.println("here are the contenders!");
                for(Player contender : spelers){
                    System.out.println(contender.getName());
                }
            }
            else{
                System.out.println("please give a valid answer.");
            }
        }while(!playersComplete);

        Bussen eersteSpel = new Bussen();
        eersteSpel.bussenRonde1(spelers);
        eersteSpel.bussenRonde2(spelers);
        eersteSpel.bussenRonde3(spelers);
    }
}
