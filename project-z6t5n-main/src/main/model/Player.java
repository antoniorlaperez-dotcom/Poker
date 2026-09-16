package model;

import java.util.ArrayList;

import org.json.JSONObject;

import persistence.Writable;

// Represents a player in the poker game with its respective name
// cards, and dollars
public class Player implements Writable {
    private String name;
    private Card card1;
    private Card card2; 
    private double dollars;

    // EFFECTS: constructs player object with given name, two null cards, 
    // and with 0 dollars
    public Player(String name) {
        this.name = name;
        dollars = 0;
        card1 = null;
        card2 = null;
    }

    public String getName() {
        return name;
    }

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }

    public double getDollars() {
        return dollars;
    }

    // EFFECTS: gets both player's cards and returns a list of them
    public ArrayList<Card> getCards() {
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        return cardList;
    }

    // EFFECTS: sets card tpye and suit for both cards
    public void setCards(String type1, String type2) { 
        card1 = new Card(type1);
        card2 = new Card(type2);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
