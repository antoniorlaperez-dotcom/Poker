package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class PlayerTest {
    Player testPlayer;
    Player testPlayer2;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player("Enrique");
        testPlayer2 = new Player("Ren");
    }

    @Test
    void testConstrutor() {
        assertEquals("Enrique", testPlayer.getName());
        assertEquals(2, testPlayer.getCards().size());
        assertNull(testPlayer.getCards().get(0));
        assertNull(testPlayer.getCards().get(1));
        assertEquals("Ren", testPlayer2.getName());
    }

    @Test
    void testSetCards() {
        testPlayer.setCards("2", "3");
        assertEquals("2", testPlayer.getCards().get(0).getType());
        assertEquals("3", testPlayer.getCards().get(1).getType());

        testPlayer.setCards("jack", "ace");
        assertEquals("jack", testPlayer.getCards().get(0).getType());
        assertEquals("ace", testPlayer.getCards().get(1).getType());
    }

    @Test
    void testSetDollars() {
        testPlayer.setDollars(10);
        testPlayer.setDollars(10);
        assertEquals(10, testPlayer.getDollars());
        testPlayer.setDollars(5);
        assertEquals(5, testPlayer.getDollars());
    }

    @Test
    void testToJson() {
        Player p = new Player("Alice");
        JSONObject json = p.toJson();
        assertEquals("Alice", json.getString("name"));
    }
}
