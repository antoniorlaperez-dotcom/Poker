package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;


@ExcludeFromJacocoGeneratedReport
public class CardTest {
    private Card testCard2;   
    private Card testCard3;   
    private Card testCard4;   
    private Card testCard5;
    private Card testCard6;
    private Card testCard7;
    private Card testCard8;
    private Card testCard9;
    private Card testCard10;
    private Card testCard11;
    private Card testCard12;
    private Card testCard13;
    private Card testCard14;


    @BeforeEach
    void runBefore() {
        testCard2 = new Card("2");
        testCard3 = new Card("3");
        testCard4 = new Card("4");
        testCard5 = new Card("5");
        testCard6 = new Card("6");
        testCard7 = new Card("7");
        testCard8 = new Card("8");
        testCard9 = new Card("9");
        testCard10 = new Card("10");
        testCard11 = new Card("jack");
        testCard12 = new Card("queen");
        testCard13 = new Card("king");
        testCard14 = new Card("ace");

    }

    @Test
    void testConstructor() {
        assertEquals("2", testCard2.getType());
        assertEquals("jack", testCard11.getType());
    }

    @Test
    void testGetValueOfType() {
        assertEquals(2, testCard2.getValueOfType());
        assertEquals(3, testCard3.getValueOfType());
        assertEquals(4, testCard4.getValueOfType());
        assertEquals(5, testCard5.getValueOfType());
        assertEquals(6, testCard6.getValueOfType());
        assertEquals(7, testCard7.getValueOfType());
        assertEquals(8, testCard8.getValueOfType());
        assertEquals(9, testCard9.getValueOfType());
        assertEquals(10, testCard10.getValueOfType());
        assertEquals(11, testCard11.getValueOfType());
        assertEquals(12, testCard12.getValueOfType());
        assertEquals(13, testCard13.getValueOfType());
        assertEquals(14, testCard14.getValueOfType());
    } 
}
