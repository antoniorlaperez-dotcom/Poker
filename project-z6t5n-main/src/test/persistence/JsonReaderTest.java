package persistence;

import model.Player;
import model.PokerGame;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralPokerGame() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderGeneralPokerData.json");
            PokerGame pg = reader.read();

            assertEquals(20.0, pg.getBuyIn());
            assertEquals(5.0, pg.getBlackValue());
            assertEquals(2.5, pg.getGreenValue());
            assertEquals(1.0, pg.getBlueValue());
            assertEquals(0.5, pg.getRedValue());
            assertEquals(0.25, pg.getWhiteValue());

            List<Player> players = pg.getPlayers();
            assertEquals(2, players.size());
            assertEquals("Alice", players.get(0).getName());
            assertEquals("Bob", players.get(1).getName());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
