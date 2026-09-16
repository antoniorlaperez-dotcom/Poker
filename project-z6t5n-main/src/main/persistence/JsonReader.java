package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of poker app data to file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads poker app data from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PokerGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePokerGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses poker app data from JSON object and returns it
    private PokerGame parsePokerGame(JSONObject jsonObject) {
        Double buyIn = jsonObject.getDouble("buy-in");
        PokerGame pg = new PokerGame();
        pg.setBuyIn(buyIn);
        addChipValues(pg, jsonObject);
        addPlayers(pg, jsonObject);
        return pg;
    }

    private void addChipValues(PokerGame pg, JSONObject jsonObject) {
        JSONObject chipValues = jsonObject.getJSONObject("chipValues");
        Double blackChipValue = chipValues.getDouble("Black");
        Double greenChipValue = chipValues.getDouble("Green");
        Double blueChipValue = chipValues.getDouble("Blue");
        Double redChipValue = chipValues.getDouble("Red");
        Double whiteChipValue = chipValues.getDouble("White");
        pg.setBlackChipValue(blackChipValue);
        pg.setGreenChipValue(greenChipValue);
        pg.setBlueChipValue(blueChipValue);
        pg.setRedChipValue(redChipValue);
        pg.setWhiteChipValue(whiteChipValue);
    }

    // MODIFIES: pg
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addPlayers(PokerGame pg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(pg, nextPlayer);
        }
    }

    // MODIFIES: pg
    // EFFECTS: parses player from JSON object and adds it to poker game object
    private void addPlayer(PokerGame pg, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        pg.addPlayer(name);
    }
}
