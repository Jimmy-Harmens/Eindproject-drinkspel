package novi.basics;

public class PlayingCards {
    private String colour;
    private String type;
    private String value;
    private final String shorthand;
    private final int hiddenvalue;
    public boolean revealed;

    public PlayingCards(int cardID){
        assignColour(cardID);
        assignType(cardID);
        assignValue(cardID);
        this.hiddenvalue = cardID % 13;
        this.shorthand = type.substring(0,1) + value.charAt(0);
    }
    private void assignColour(int cardID){
        if(cardID < 26){
            this.colour = "Zwart";
        }
        else if(cardID < 52){
            this.colour = "Rood";
        }
        else{
            this.colour = "Joker";
        }
    }
    private void assignType(int cardID){
        if(cardID < 13){
            this.type = "Klaver";
        }
        else if(cardID < 26){
            this.type = "Schoppen";
        }
        else if(cardID < 39){
            this.type = "Ruiten";
        }
        else if(cardID < 52){
            this.type = "Harten";
        }
        else {
            this.type = "Joker";
        }
    }
    private void assignValue(int cardID){
        if(cardID % 13 == 9){
            this.value = "Boer";
        }
        else if(cardID % 13 == 10){
            this.value = "Vrouw";
        }
        else if(cardID % 13 == 11){
            this.value = "Heer";
        }
        else if(cardID % 13 == 12){
            this.value = "Aas";
        }
        else if(cardID > 51){
            this.value = "Joker";
        }
        else{
            this.value = String.valueOf(cardID % 13 + 2);
        }
    }
    public String getColour(){
        return colour;
    }
    public String getType(){
        return type;
    }
    public String getValue(){
        return value;
    }
    public int getHiddenvalue(){
        return hiddenvalue;
    }
    public String getShorthand(){
        return shorthand;
    }
}
