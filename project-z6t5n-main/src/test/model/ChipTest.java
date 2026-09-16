package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class ChipTest {
    private Chip testChip;
    
    @BeforeEach
    void runBefore() {
        testChip = new Chip();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testChip.getValue());
    }

    @Test
    void testSetters() {
        testChip.setValue(1);
        testChip.setValue(1);
        assertEquals(1, testChip.getValue());
        testChip.setValue(5);
        assertEquals(5, testChip.getValue());
    }
}
