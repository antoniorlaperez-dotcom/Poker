package model;

// Represents information about the card type and its coresponding value
public class Card {
    private String type;
    
    // REQUIRES: type to be a valid type (ie. 2-10, Jack-Ace)
    // EFFECTS: contructs a card object with given type 
    public Card(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    //REQUIRES: Type to be an actual type of Card (ie. 2-10, Jack-Ace)
    //MODIFIES: this
    //EFFECTS: returns value the card type is accosciated with
    // For numbers it is self-explanatory, but faces cards will 
    // continue in increasing value after 10. (Ace is considered a face card)
    public int getValueOfType() {
        if (type.length() == 1) {
            return Integer.parseInt(type);
        } else if (type.equals("10")) {
            return 10;
        } else if (type.equals("jack")) {
            return 11;
        } else if (type.equals("queen")) {
            return 12;
        } else if (type.equals("king")) {
            return 13;
        } else if (type.equals("ace")) {
            return 14;
        } else {
            return 0; // This should never happen
        }
    }
}

