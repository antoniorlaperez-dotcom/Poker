package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


// Represents a an pokerGame with values for all chips, buy-in, players
// in the game and payment information 
public class PokerGame implements Writable {

    private Chip blackChip;
    private Chip greenChip;
    private Chip blueChip;
    private Chip redChip;
    private Chip whiteChip;
    private double buyIn; 
    private ArrayList<Player> playerList;
    private ArrayList<Payment> payments;

    //EFFECTS: Constructs new pokergame object with empty list of players, buy-in of 0 and
    // constructs 5 instances of chips (black, green, blue, red, and white) with inital 0 value.
    public PokerGame() {
        playerList = new ArrayList<>();
        blackChip = new Chip();
        greenChip = new Chip();
        blueChip = new Chip();
        redChip = new Chip();
        whiteChip = new Chip();
        buyIn = 0; 
        payments = new ArrayList<>();
    }

    public void setBlackChipValue(double value) {
        blackChip.setValue(value);
    }

    public Chip getBlackChip() {
        return blackChip;
    }

    public double getBlackValue() {
        return blackChip.getValue();
    }

    public void setGreenChipValue(double value) {
        greenChip.setValue(value);
    }

    public Chip getGreenChip() {
        return greenChip;
    }

    public double getGreenValue() {
        return greenChip.getValue();
    }

    public void setBlueChipValue(double value) {
        blueChip.setValue(value);
    }

    public Chip getBlueChip() {
        return blueChip;
    }

    public double getBlueValue() {
        return blueChip.getValue();
    }

    public void setRedChipValue(double value) {
        redChip.setValue(value);
    }

    public Chip getRedChip() {
        return redChip;
    }

    public double getRedValue() {
        return redChip.getValue();
    }

    public void setWhiteChipValue(double quantity) {
        whiteChip.setValue(quantity);
    }

    public Chip getWhiteChip() {
        return whiteChip;
    }

    public double getWhiteValue() {
        return whiteChip.getValue();
    }

    public void setBuyIn(double buyIn) {
        this.buyIn = buyIn;
    }

    public double getBuyIn() {
        return buyIn;
    }

    public ArrayList<Player> getPlayers() {   
        return playerList;
    }

    public ArrayList<Payment> getPayments() {   
        return payments;
    }

    // EFFECTS: returns all the names of the players in a list
    public ArrayList<String> getPlayerNames() {
        ArrayList<String> playerNames = new ArrayList<String>();
        for (Player p : playerList) {
            playerNames.add(p.getName());
        }
        return playerNames;
    }

    // MODIFIES: the player 
    // EFFECTS: sets cards for given player 
    public void setPlayerCards(String playerName, String card1, String card2) {
        Player player = findPlayer(playerName);
        player.setCards(card1, card2);
    }

    // EFFECTS: returns the player given the name, return null if not in list
    public Player findPlayer(String name) {
        for (Player p : playerList) {
            if (p.getName().equals(name)) {
                return p;
            }             
        }
        return null;   // will never happen
    }

    // MODIFIES: this
    // EFFECTS: adds player to the end of list of players with given name
    //          and logs the event to the EventLog
    public void addPlayer(String name) {
        Player player = new Player(name);
        playerList.add(player);
        EventLog.getInstance().logEvent(new Event("Player added: " + name));
    }
 
    // REQUIRES: player name to already be in playerList or will do nothing. 
    // MODIFIES: this
    // EFFECTS: removes player with given name from list of players
    //          and logs the event to the EventLog
    public void removePlayer(String name) {
        for (Player p : playerList) {
            if (p.getName().equals(name)) {
                playerList.remove(p);
                EventLog.getInstance().logEvent(new Event("Player removed: " + name));
                return;
            }             
        }
    }

    // REQUIRES: at least two players are playing
    // EFFECTS: compares hands of each player and prints player with strongest 
    // hand. If same strongest hand, the player who was added first to the list is returned.
    public String playerWithStrongestHand() {
        Player strongestPlayer = playerList.get(0);
        for (Player p : playerList) {
            strongestPlayer = compareStrength(strongestPlayer, p);
        }
        return strongestPlayer.getName();
    }

    // EFFECTS: ranks the hands of the players playing before flop; if tied return first player
    public Player compareStrength(Player p1, Player p2) {
        int valuePlayer1Card1 = p1.getCards().get(0).getValueOfType();
        int valuePlayer1Card2 = p1.getCards().get(1).getValueOfType();
        int valuePlayer2Card1 = p2.getCards().get(0).getValueOfType();
        int valuePlayer2Card2 = p2.getCards().get(1).getValueOfType();

        int highestValuePlayer1 = highestValue(valuePlayer1Card1, valuePlayer1Card2);
        int highestValuePlayer2 = highestValue(valuePlayer2Card1, valuePlayer2Card2);

        boolean hasPairPlayer1 = valuePlayer1Card1 == valuePlayer1Card2;
        boolean hasPairPlayer2 = valuePlayer2Card1 == valuePlayer2Card2;

        if (hasPairPlayer1 && !hasPairPlayer2) {
            return p1;
        } else if (!hasPairPlayer1 && hasPairPlayer2) {
            return p2;

        } else if (highestValuePlayer1 > highestValuePlayer2) {
            return p1;
        } else if (highestValuePlayer1 < highestValuePlayer2) {
            return p2; 
        } else {
            return p1;  // Case where tied
        }
    }

    // EFFECTS: retunrs higher value; return first value if equal
    public int highestValue(int value1, int value2) {
        if (value2 > value1) {
            return value2;
        } else {
            return value1;
        }
    }

    // MODIFIES: this
    // EFFECTS: returns who owes whom
    public void settleDebts() {
        ArrayList<Player> debtPlayers = new ArrayList<>();
        ArrayList<Player> creditPlayers = new ArrayList<>();
        
        for (Player p : playerList) {
            if (p.getDollars() < 0) {
                debtPlayers.add(p);
            } else if (p.getDollars() > 0) {
                creditPlayers.add(p);
            }
        }
        processPayments(debtPlayers, creditPlayers);
    }

    // EFFECTS: creates payment objects to record money transfer
    public void processPayments(ArrayList<Player> debtPlayers, ArrayList<Player> creditPlayers) {
        int d = 0;
        int c = 0;
        while (d < debtPlayers.size() && c < creditPlayers.size()) {
            Player debtPlayer = debtPlayers.get(d);
            Player creditPlayer = creditPlayers.get(c);

            double amount = Math.min(- debtPlayer.getDollars(), creditPlayer.getDollars());
            payments.add(new Payment(debtPlayer, creditPlayer, amount));

            debtPlayer.setDollars(debtPlayer.getDollars() + amount);
            creditPlayer.setDollars(creditPlayer.getDollars() - amount);

            if (debtPlayer.getDollars() == 0) {
                d++;
            }

            if (creditPlayer.getDollars() == 0) {
                c++;
            }
        }
    } 

    // EFFECTS: retunrs list of payment summaries as strings
    public ArrayList<String> paymentSummary() {
        ArrayList<String> summaries = new ArrayList<>();
        for (Payment p : payments) {
            String summary = p.getPayer().getName() + " pays " + p.getPayee().getName() + " $" + p.getAmount();
            summaries.add(summary);
        }
        return summaries;
    }

    // MODIFIES: given player
    // EFFECTS: calculates total dollars for players given how many chips they have and 
    // subtracts it from their buy-in costs. 
    public void setPlayerdollars(String playerName, double blackQuant, double greenQuant, double blueQuant,
                                 double redQuant, double whiteQuant, double additionalBuyIns) {

        Player player = findPlayer(playerName);
        double totBalck = blackQuant * blackChip.getValue();
        double totGreen = greenQuant * greenChip.getValue();
        double totBlue = blueQuant * blueChip.getValue();
        double totRed = redQuant * redChip.getValue();
        double totWhite = whiteQuant * whiteChip.getValue();

        double totalBuyInCost = - buyIn * (1 + additionalBuyIns);

        double totDollars = totBalck + totGreen + totBlue + totRed + totWhite + totalBuyInCost;
        player.setDollars(player.getDollars() + totDollars);
    }

    // MODIFIES: this
    // EFFECTS: resets player dollars to 0 after each book settling
    public void resetDollars() {
        for (Player player : playerList) {
            player.setDollars(0);
        }
    }

    //MODIFIES: this
    // EFFECTS: clears payment records
    public void resetPayments() {
        payments = new ArrayList<>();
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: here we are returning listPlayer, buy-in, and chip values
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("buy-in", buyIn);
        json.put("chipValues", chipValuesToJson());
        json.put("players", playersToJson());
        return json;
    }

    private JSONObject chipValuesToJson() {
        JSONObject json = new JSONObject();
        json.put("Black", getBlackValue());
        json.put("Green", getGreenValue());
        json.put("Blue", getBlueValue());
        json.put("Red", getRedValue());
        json.put("White", getWhiteValue());
        return json;
    }

    // EFFECTS: returns players in the poker game as JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : playerList) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
