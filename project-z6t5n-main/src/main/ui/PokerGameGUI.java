package ui;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

import model.PokerGame;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.Event;
import model.EventLog;

// GUI for the Poker Game application
public class PokerGameGUI extends JFrame {

    private static final String JSON_STORE = "./data/pokerData.json";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private PokerGame pokerGame;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTextField inputField;
    private JTextArea displayArea;

    // EFFECTS: sets up window, shows image to screen, then shows main GUI
    public PokerGameGUI() {
        pokerGame = new PokerGame();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        showIntroScreen();
        initializeMainWindow();
    }

    // EFFECTS: sets up the image and displays it for 5 seconds
    private void showIntroScreen() {
        JWindow window = new JWindow();

        ImageIcon image = new ImageIcon("./data/introImage.png");

        Image scaled = image.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        window.getContentPane().add(imageLabel, BorderLayout.CENTER);
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Expected
        }
        window.dispose();
    }

    // MODIFIES: this
    // EFFECTS: sets up the main application window with all components
    private void initializeMainWindow() {
        setTitle("Poker Game Manager");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventLog();
                dispose();
                System.exit(0);
            }
        });
        
        add(createInputPanel(), BorderLayout.NORTH);
        add(createDisplayArea(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    // EFFECTS: prints all events in the EventLog to the console
    private void printEventLog() {
        System.out.println("\nEvent Log:");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
            System.out.println();
        }
    }

    // EFFECTS: creates panel with a text field for entering player names
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("  Player Name: ");
        inputField = new JTextField();

        panel.add(label, BorderLayout.WEST);
        panel.add(inputField, BorderLayout.CENTER);

        return panel;
    }

    // EFFECTS: creates scrollable text area for displaying information
    private JScrollPane createDisplayArea() {
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        return new JScrollPane(displayArea);
    }

    // EFFECTS: creates panel with all the buttons
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 5, 5));

        JButton addButton = new JButton("Add Player");
        JButton removeButton = new JButton("Remove Player");
        JButton viewButton = new JButton("View Players");
        JButton saveButton = new JButton("Save Players");
        JButton loadButton = new JButton("Load Players");

        addButton.addActionListener(e -> addPlayer());
        removeButton.addActionListener(e -> removePlayer());
        viewButton.addActionListener(e -> viewPlayers());
        saveButton.addActionListener(e -> savePlayers());
        loadButton.addActionListener(e -> loadPlayers());

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(viewButton);
        panel.add(saveButton);
        panel.add(loadButton);

        return panel;
    }

    // MODIFIES: this, pokerGame
    // EFFECTS: adds player with name from text field to poker game
    private void addPlayer() {
        String name = inputField.getText().trim();
        if (name.isEmpty()) {
            displayArea.setText("Please enter a player name.");
            return;
        }
        pokerGame.addPlayer(name);
        displayArea.setText(name + " has been added!");
        inputField.setText("");
    }

    // MODIFIES: this, pokerGame
    // EFFECTS: removes player with name from text field from poker game
    private void removePlayer() {
        String name = inputField.getText().trim();
        if (name.isEmpty()) {
            displayArea.setText("Please enter a player name to remove.");
            return;
        }
        if (pokerGame.findPlayer(name) == null) {
            displayArea.setText("Player \"" + name + "\" not found.");
        } else {
            pokerGame.removePlayer(name);
            displayArea.setText(name + " has been removed!");
        }
        inputField.setText("");
    }

    // EFFECTS: displays all player names in the display area
    private void viewPlayers() {
        if (pokerGame.getPlayerNames().isEmpty()) {
            displayArea.setText("No players added yet.");
            return;
        }
        StringBuilder sb = new StringBuilder("Current Players:\n\n");
        int i = 1;
        for (String name : pokerGame.getPlayerNames()) {
            sb.append(i + ". " + name + "\n");
            i++;
        }
        displayArea.setText(sb.toString());
    }

    // EFFECTS: saves poker game data to JSON file
    private void savePlayers() {
        try {
            jsonWriter.open();
            jsonWriter.write(pokerGame);
            jsonWriter.close();
            displayArea.setText("Data saved successfully!");
        } catch (FileNotFoundException e) {
            displayArea.setText("Error: Unable to save to file.");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads poker game data from JSON file
    private void loadPlayers() {
        try {
            pokerGame = jsonReader.read();
            displayArea.setText("Data loaded successfully!\n\nPlayers loaded: "
                    + pokerGame.getPlayerNames().size());
        } catch (IOException e) {
            displayArea.setText("Error: No saved data found.");
        }
    }
}