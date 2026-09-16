package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class PokerGametest {
    private PokerGame testPokerGame;

    @BeforeEach
    void runBefore() {
        testPokerGame = new PokerGame();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testPokerGame.getPlayers().size());
        assertEquals(0, testPokerGame.getPayments().size());
        assertEquals(0, testPokerGame.getBuyIn());
        assertEquals(0, testPokerGame.getBlackChip().getValue());
        assertEquals(0, testPokerGame.getGreenChip().getValue());
        assertEquals(0, testPokerGame.getBlueChip().getValue());
        assertEquals(0, testPokerGame.getRedChip().getValue());
        assertEquals(0, testPokerGame.getWhiteChip().getValue());
    }

    @Test
    void testSetBuyIn() {
        testPokerGame.setBuyIn(5);
        testPokerGame.setBuyIn(5);
        assertEquals(5, testPokerGame.getBuyIn());

        testPokerGame.setBuyIn(20);
        assertEquals(20, testPokerGame.getBuyIn());

    }

    @Test
    void testAddPlayer() {
        testPokerGame.addPlayer("Antonio");
        assertEquals("Antonio", testPokerGame.getPlayers().get(0).getName());
        assertEquals(1, testPokerGame.getPlayers().size());

        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");
        assertEquals(3, testPokerGame.getPlayers().size());
        assertEquals("Antonio", testPokerGame.getPlayers().get(0).getName());
        assertEquals("Enrique", testPokerGame.getPlayers().get(1).getName());
        assertEquals("Ren", testPokerGame.getPlayers().get(2).getName());
    }

    @Test
    void testRemovePlayer() {
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Ren");

        testPokerGame.removePlayer("Jack");
        assertEquals(3, testPokerGame.getPlayers().size());
        assertEquals("Enrique", testPokerGame.getPlayers().get(0).getName());
        assertEquals("Antonio", testPokerGame.getPlayers().get(1).getName());
        assertEquals("Ren", testPokerGame.getPlayers().get(2).getName());

        testPokerGame.removePlayer("Antonio");
        assertEquals(2, testPokerGame.getPlayers().size());
        assertEquals("Enrique", testPokerGame.getPlayers().get(0).getName());
        assertEquals("Ren", testPokerGame.getPlayers().get(1).getName());

        testPokerGame.removePlayer("Enrique");
        testPokerGame.removePlayer("Ren");

        assertEquals(0, testPokerGame.getPlayers().size());
    }

    @Test // normal cases are tested in other methods
    void testFindPlayerNullCondition() {
        testPokerGame.addPlayer("Antonio");
        Player testPlayer = testPokerGame.findPlayer("jack");
        assertNull(testPlayer);
    }

    @Test
    void testPlayerWithStrongestHandHighDouble() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");
        Player testPlayer = testPokerGame.findPlayer("Antonio");
        Player testPlayer2 = testPokerGame.findPlayer("Enrique");
        Player testPlayer3 = testPokerGame.findPlayer("Ren");

        testPlayer.setCards("ace", "2");
        testPlayer2.setCards("10", "10");
        testPlayer3.setCards("jack", "club");

        assertEquals("Enrique", testPokerGame.playerWithStrongestHand());
    }

    @Test
    void testPlayerWithStrongestHandTiedHighDouble() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");

        testPokerGame.getPlayers().get(0).setCards("ace", "2");
        testPokerGame.getPlayers().get(1).setCards("jack", "jack");
        testPokerGame.getPlayers().get(2).setCards("jack", "jack");

        assertEquals("Enrique", testPokerGame.playerWithStrongestHand());
    }

    @Test
    void testPlayerWithStrongestHandHighCard() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");

        testPokerGame.getPlayers().get(0).setCards("ace", "2");
        testPokerGame.getPlayers().get(1).setCards("jack", "5");
        testPokerGame.getPlayers().get(2).setCards("queen", "8");

        assertEquals("Antonio", testPokerGame.playerWithStrongestHand());
    }

    @Test
    void testPlayerWithStrongestHandTiedHighCard() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");

        testPokerGame.getPlayers().get(0).setCards("ace", "2");
        testPokerGame.getPlayers().get(1).setCards("ace", "3");
        testPokerGame.getPlayers().get(2).setCards("jack", "6");

        assertEquals("Antonio", testPokerGame.playerWithStrongestHand());
    }

    @Test
    void testCompareStrenghtHighCardTied() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        Player testPlayer = testPokerGame.findPlayer("Antonio");
        Player testPlayer2 = testPokerGame.findPlayer("Enrique");
        testPlayer.setCards("ace", "2");
        testPlayer2.setCards("ace", "4");

        assertEquals(testPlayer, testPokerGame.compareStrength(testPlayer, testPlayer2));
    }

    @Test
    void testCompareStrenghtHighTied() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        Player testPlayer = testPokerGame.findPlayer("Antonio");
        Player testPlayer2 = testPokerGame.findPlayer("Enrique");
        testPlayer.setCards("ace", "2");
        testPlayer2.setCards("jack", "4");

        assertEquals(testPlayer, testPokerGame.compareStrength(testPlayer, testPlayer2));
    }

    @Test
    void testCompareStrenghtTiedHighDouble() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        Player testPlayer = testPokerGame.findPlayer("Antonio");
        Player testPlayer2 = testPokerGame.findPlayer("Enrique");
        testPlayer.setCards("queen", "queen");
        testPlayer2.setCards("queen", "queen");

        assertEquals(testPlayer, testPokerGame.compareStrength(testPlayer, testPlayer2));
    }

    @Test
    void testCompareStrenghtHighDouble() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        Player testPlayer = testPokerGame.findPlayer("Antonio");
        Player testPlayer2 = testPokerGame.findPlayer("Enrique");
        testPlayer.setCards("2", "queen");
        testPlayer2.setCards("queen", "queen");

        assertEquals(testPlayer2, testPokerGame.compareStrength(testPlayer, testPlayer2));
    }

    @Test
    void testCompareStrengthPlayer2HigherCard() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        Player testPlayer = testPokerGame.findPlayer("Antonio");
        Player testPlayer2 = testPokerGame.findPlayer("Enrique");
        testPlayer.setCards("jack", "4");
        testPlayer2.setCards("ace", "2");

        assertEquals(testPlayer2, testPokerGame.compareStrength(testPlayer, testPlayer2));
    }

    @Test
    void testSetPlayerDollars() {
        testPokerGame.setBuyIn(100);
        testPokerGame.setBlackChipValue(100);
        testPokerGame.setGreenChipValue(25);
        testPokerGame.setBlueChipValue(10);
        testPokerGame.setRedChipValue(5);
        testPokerGame.setWhiteChipValue(1);

        testPokerGame.addPlayer("Antonio");

        testPokerGame.setPlayerdollars("Antonio", 2, 4, 0, 0, 0, 1);

        Player player = testPokerGame.findPlayer("Antonio");
        assertEquals(100, player.getDollars());
    }

    @Test
    void testGetPlayerNames() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");

        ArrayList<String> names = testPokerGame.getPlayerNames();
        assertEquals(3, names.size());
        assertEquals("Antonio", names.get(0));
        assertEquals("Enrique", names.get(1));
        assertEquals("Ren", names.get(2));
    }

    @Test
    void testSetPlayerCards() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.setPlayerCards("Antonio", "ace", "king");

        Player player = testPokerGame.findPlayer("Antonio");
        assertEquals("ace", player.getCards().get(0).getType());
        assertEquals("king", player.getCards().get(1).getType());
    }

    @Test
    void testHighestValue() {
        assertEquals(10, testPokerGame.highestValue(5, 10));
        assertEquals(10, testPokerGame.highestValue(10, 5));
        assertEquals(7, testPokerGame.highestValue(7, 7));
    }

    @Test
    void testSetlleDebtsTwoPlayer() {
        testPokerGame.setBuyIn(100);
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");

        testPokerGame.findPlayer("Antonio").setDollars(50);
        testPokerGame.findPlayer("Enrique").setDollars(-50);

        testPokerGame.settleDebts();

        assertEquals(1, testPokerGame.getPayments().size());
        assertEquals(0, testPokerGame.findPlayer("Antonio").getDollars());
        assertEquals(0, testPokerGame.findPlayer("Enrique").getDollars());
    }

    @Test
    void testSetlleDebtsThreePlayer() {
        testPokerGame.setBuyIn(100);
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");

        testPokerGame.findPlayer("Antonio").setDollars(100);
        testPokerGame.findPlayer("Enrique").setDollars(-60);
        testPokerGame.findPlayer("Ren").setDollars(-40);

        testPokerGame.settleDebts();

        assertEquals(2, testPokerGame.getPayments().size());
    }

    @Test
    void testSettleDebtsPlayerWithZeroDollars() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");

        testPokerGame.findPlayer("Antonio").setDollars(50);
        testPokerGame.findPlayer("Enrique").setDollars(-50);
        testPokerGame.findPlayer("Ren").setDollars(0); // skips both branches

        testPokerGame.settleDebts();

        assertEquals(1, testPokerGame.getPayments().size());
    }

    @Test
    void testPaymentSummary() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");

        testPokerGame.findPlayer("Antonio").setDollars(50);
        testPokerGame.findPlayer("Enrique").setDollars(-50);

        testPokerGame.settleDebts();

        ArrayList<String> summaries = testPokerGame.paymentSummary();
        assertEquals(1, summaries.size());
        assertTrue(summaries.get(0).contains("Enrique"));
        assertTrue(summaries.get(0).contains("Antonio"));
        assertTrue(summaries.get(0).contains("50"));
    }

    @Test
    void testProcessPaymentsPartialPayment() {
        testPokerGame.addPlayer("Antonio");
        testPokerGame.addPlayer("Enrique");
        testPokerGame.addPlayer("Ren");

        testPokerGame.findPlayer("Antonio").setDollars(-100);
        testPokerGame.findPlayer("Enrique").setDollars(60);
        testPokerGame.findPlayer("Ren").setDollars(40);

        testPokerGame.settleDebts();
        assertEquals(2, testPokerGame.getPayments().size());
    }

    @Test
    void testToJson() {
        testPokerGame.setBuyIn(20.0);
        testPokerGame.setBlackChipValue(5.0);
        testPokerGame.addPlayer("Alice");

        JSONObject json = testPokerGame.toJson();
        assertEquals(20.0, json.getDouble("buy-in"));
        assertEquals(5.0, json.getJSONObject("chipValues").getDouble("Black"));
        assertEquals(1, json.getJSONArray("players").length());
    }

    @Test
    void testResetDollars() {
        testPokerGame.addPlayer("Jay");
        testPokerGame.setBuyIn(10.0);
        testPokerGame.setWhiteChipValue(1.0);
        testPokerGame.setPlayerdollars("Jay", 0, 0, 0, 0, 20, 0);
        testPokerGame.resetDollars();
        assertEquals(0, testPokerGame.findPlayer("Jay").getDollars());
    }

    @Test
    void testResetPayments() {
        testPokerGame.addPlayer("Alice");
        testPokerGame.addPlayer("Bob");
        testPokerGame.setBuyIn(10.0);
        testPokerGame.setWhiteChipValue(1.0);
        testPokerGame.setPlayerdollars("Alice", 0, 0, 0, 0, 20, 0);
        testPokerGame.setPlayerdollars("Bob", 0, 0, 0, 0, 0, 0);
        testPokerGame.settleDebts();
        testPokerGame.resetPayments();
        assertEquals(0, testPokerGame.getPayments().size());
    }
}
