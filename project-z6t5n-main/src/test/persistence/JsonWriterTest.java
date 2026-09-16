package persistence;

import org.junit.jupiter.api.Test;

import model.PokerGame;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PokerGame pg = new PokerGame();
            pokerGameSetUp(pg);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPokerData.json");
            writer.open();
            writer.write(pg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPokerData.json");
            pg = reader.read();
            List<String> players = pg.getPlayerNames();
            assertEquals(3, players.size());
            assertEquals("Jeff", players.get(0));
            assertEquals("Bob", players.get(1));
            assertEquals("Davy", players.get(2));
            assertEquals(5, pg.getBlackValue());
            assertEquals(2, pg.getGreenValue());
            assertEquals(1, pg.getBlueValue());
            assertEquals(1, pg.getRedValue());
            assertEquals(1, pg.getWhiteValue());
            assertEquals(10, pg.getBuyIn());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void pokerGameSetUp(PokerGame pg) {
        pg.addPlayer("Jeff");
        pg.addPlayer("Bob");
        pg.addPlayer("Davy");
        pg.setBuyIn(10);
        pg.setBlackChipValue(5);
        pg.setGreenChipValue(2);
        pg.setBlueChipValue(1);
        pg.setRedChipValue(1);
        pg.setWhiteChipValue(1);
    }
}
