package ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.PokerGame;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.IOException;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Poker app apllication
@ExcludeFromJacocoGeneratedReport
public class PokerApp {
    private static final String JSON_STORE = "./data/pokerData.json";
    private Scanner input;
    private boolean appKeepGoing;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private PokerGame pokerGame;

    // EFFECTS: runs the poker application
    public PokerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPokerUi();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPokerUi() {
        appKeepGoing = true;
        init();
        System.out.println("Welcome! Press 'q' anytime to quit.");
        System.out.println("To load data enter 'y'. If not, enter 'n' and continue:");

        while (true) {
            String command = input.next();

            if (command.equals("q")) {
                appKeepGoing = false;
                System.out.println("\nGoodbye!");
                return;
            }

            if (command.equals("y")) {
                loadData();
                System.out.println("\nGoodbye!");
                return;
            } else if (command.equals("n")) {
                noLoadedData();
                System.out.println("\nGoodbye!");
                return;
            } else {
                System.out.println("Not valid input. Try again:");
            }
        }
    }

    // EFFECTS: continues with the poker app if user does not want to load data.
    private void noLoadedData() {
        setUp();
        if (!appKeepGoing) {
            System.out.println("\nGoodbye!");
            return;
        }
        while (true) {
            optionsMenu();
            if (!appKeepGoing) {
                return;
            }
        }
    }

    // EFFECTS: contines with poker app if user wants to continue with loaded data
    private void loadData() {
        loadApp();
        while (true) {
            optionsMenu();
            if (!appKeepGoing) {
                return;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets start of the pokergame, scanner, and delimiter
    private void setUp() {
        setBuyIn();
        if (!appKeepGoing) {
            return;
        }
        setChipValues();
        if (!appKeepGoing) {
            return;
        }
        setUpPlayers();
        if (!appKeepGoing) {
            return;
        }
    }

    // EFFECTS: displays menu of options
    @SuppressWarnings("methodlength")
    private void optionsMenu() {
        while (true) {
            System.out.println("\nSelect from below or quit:");
            System.out.println("\tc -> change chip value");
            System.out.println("\tf -> view chip values");
            System.out.println("\tb -> change buy-in");
            System.out.println("\td -> view buy-in");
            System.out.println("\tv -> view players");
            System.out.println("\ta -> add players");
            System.out.println("\tr -> remove players");
            System.out.println("\th -> compare player hands preflop");
            System.out.println("\tn -> settle debts");
            System.out.println("\ts -> save poker application");
            System.out.println("\tq -> quit");
            String command = input.next();
            if (command.equals("q")) {
                appKeepGoing = false;
                return;
            } else if (command.equals("n")) {
                settleBooks();
            } else {
                processMenuCommands(command);
            }
            if (!appKeepGoing) {
                return;
            }
        }
    }

    // EFFECTS: process user input and deos the function the user want
    @SuppressWarnings("methodlength")
    private void processMenuCommands(String command) {
        if (command.equals("c")) {
            setChipValues();
        } else if (command.equals("b")) {
            setBuyIn();
        } else if (command.equals("v")) {
            viewPlayer();
        } else if (command.equals("a")) {
            setUpPlayers();
        } else if (command.equals("r")) {
            removePlayers();
        } else if (command.equals("h")) {
            compareHands();
        } else if (command.equals("f")) {
            viewChipValues();
        } else if (command.equals("l")) {
            loadApp();
        } else if (command.equals("s")) {
            saveApp();
        } else if (command.equals("d")) {
            viewBuyIn();
        } else {
            System.out.println("Not valid command. Try again:");
            return;
        }
        if (!appKeepGoing) {
            return;
        }
    }

    // EFFECTS: sets buy-in amount
    private void setBuyIn() {
        System.out.println("Enter a number in dollars for your buy-in:");
        while (true) {
            if (input.hasNextDouble()) {
                pokerGame.setBuyIn(input.nextDouble());
                return;
            } else {
                String command = input.next();
                if (command.equals("q")) {
                    appKeepGoing = false;
                    return;
                }
                System.out.println("Not a valid input. Entre a valid number:");
            }
        }
    }

    // REQUIRES: Chip values are able to sum up to the buy-in amount
    // MODIFIES: this
    // EFFECTS: set chip values for game
    private void setChipValues() {
        double blackValue = promptChipValue("black");
        if (!appKeepGoing) {
            return;
        }
        pokerGame.setBlackChipValue(blackValue);

        double greenValue = promptChipValue("green");
        if (!appKeepGoing) {
            return;
        }
        pokerGame.setGreenChipValue(greenValue);

        double blueValue = promptChipValue("blue");
        if (!appKeepGoing) {
            return;
        }
        pokerGame.setBlueChipValue(blueValue);

        double redValue = promptChipValue("red");
        if (!appKeepGoing) {
            return;
        }
        pokerGame.setRedChipValue(redValue);

        double whiteValue = promptChipValue("white");
        pokerGame.setWhiteChipValue(whiteValue);
    }

    // MODIFIES: this
    // EFFECTS: prompts for chip value and returns it
    private double promptChipValue(String chipColor) {
        System.out.println("Enter a number for the value in dollars of your " + chipColor + " chip:");
        return getDouble();
    }

    // REQUIRES: more than one player playing
    // EFFECTS: prompts how many players and enters in that many with given inputed
    // names
    private void setUpPlayers() {
        System.out.println("How many players?");
        double count = getDouble();
        if (!appKeepGoing) {
            return;
        }

        for (double i = 0; i < count; i++) {
            System.out.println("Enter name:");
            String command = input.next();

            if (command.equals("q")) {
                appKeepGoing = false;
                return;
            }
            pokerGame.addPlayer(command);
        }
    }

    // EFFECTS: settles
    @SuppressWarnings("methodlength")
    private void settleBooks() {
        for (String playerName : pokerGame.getPlayerNames()) {
            System.out.println("Enter chip quantities for " + playerName);
            System.out.println("Black chip:");
            double blackQuant = getDouble();
            if (!appKeepGoing) {
                return;
            }

            System.out.println("Green chip:");
            double greenQuant = getDouble();
            if (!appKeepGoing) {
                return;
            }

            System.out.println("Blue chip:");
            double blueQuant = getDouble();
            if (!appKeepGoing) {
                return;
            }

            System.out.println("Red chip:");
            double redQuant = getDouble();
            if (!appKeepGoing) {
                return;
            }

            System.out.println("White chip:");
            double whiteQuant = getDouble();
            if (!appKeepGoing) {
                return;
            }

            System.out.println("How many times did " + playerName + " buy in again?");
            double additionalBuyIns = getDouble();
            if (!appKeepGoing) {
                return;
            }
            pokerGame.setPlayerdollars(playerName, blackQuant, greenQuant,
                    blueQuant, redQuant, whiteQuant, additionalBuyIns);
        }
        pokerGame.settleDebts();
        showPayments();
        pokerGame.resetDollars();
        pokerGame.resetPayments();
    }

    // EFFECTS: displays a summary of who owese whom
    private void showPayments() {
        for (String payment : pokerGame.paymentSummary()) {
            System.out.println(payment);
        }
    }

    // REQUIRES: Card type to be a valid card type and lowercase
    // MODIFIES: this, pokerGame
    // EFFECTS: prompts cards for each player in the list and shows who has the
    // strongest hand
    private void compareHands() {
        for (String playerName : pokerGame.getPlayerNames()) {
            System.out.println("Enter card 1 tpye for " + playerName + ":");
            String card1 = input.next();
            if (card1.equals("q")) {
                appKeepGoing = false;
                return;
            }
            System.out.println("Enter card 2 type for " + playerName + ":");
            String card2 = input.next();
            if (card2.equals("q")) {
                appKeepGoing = false;
                return;
            }
            pokerGame.setPlayerCards(playerName, card1, card2);
        }
        System.out.println(pokerGame.playerWithStrongestHand() + " has the strongest hand pre-flop");
    }

    // REQUIRES: player name already be in the list of players. If not it will do
    // nothing.
    // MODIFIES: this
    // EFFECTS: removes player for listPlayer given name
    private void removePlayers() {
        System.out.println("Type players name to remove:");
        String command = input.next();
        if (command.equals("q")) {
            appKeepGoing = false;
            return;
        }
        pokerGame.removePlayer(command);
        System.out.println("Player has been removed!");
    }

    // EFFECTS: displays all the players to the user
    private void viewPlayer() {
        for (String playerName : pokerGame.getPlayerNames()) {
            System.out.println(playerName);
        }
    }

    // EFFECTS: displays chip values
    private void viewChipValues() {
        System.out.println("Black chip vlaue: " + pokerGame.getBlackValue());
        System.out.println("Green chip vlaue: " + pokerGame.getGreenValue());
        System.out.println("Blue chip vlaue: " + pokerGame.getBlueValue());
        System.out.println("Red chip vlaue: " + pokerGame.getRedValue());
        System.out.println("White chip vlaue: " + pokerGame.getWhiteValue());
    }

    // EFFECTS: displays buy-in
    private void viewBuyIn() {
        System.out.println("The buy-in is: " + pokerGame.getBuyIn());
    }

    // MODIFIES: this, pok"ergame
    // EFFECTS: requests user to input a double. If it isn't it asks again.
    // If prompt is q then it sets appKeepGoing to false starting the quit of the
    // app.
    private double getDouble() {
        while (true) {
            if (input.hasNextDouble()) {
                return input.nextDouble();
            } else {
                String command = input.next();
                if (command.equals("q")) {
                    appKeepGoing = false;
                    return -1;
                }
                System.out.println("Not a valid input. Entre a valid number:");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes start of the pokergame
    private void init() {
        pokerGame = new PokerGame();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves poker app data to file
    private void saveApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(pokerGame);
            jsonWriter.close();
            System.out.println("Saved buy-in, chip values and players " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads poker app data from file
    private void loadApp() {
        try {
            pokerGame = jsonReader.read();
            System.out.println("Loaded buy-in, chip values, and players from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            System.out.println("No data saved, no matter. Let's make some ourselves.");
            noLoadedData();
        }
    }

}
