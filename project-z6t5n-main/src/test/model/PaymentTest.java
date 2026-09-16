package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class PaymentTest {
    private Payment testPayment;
    private Player testPayer = new Player("Bob");
    private Player testPayee = new Player("Jeff");

    @BeforeEach
    void runBefore() {
        testPayment = new Payment(testPayer, testPayee, 5);
    }

    @Test
    void testConstrutor() {
        assertEquals(5, testPayment.getAmount());
        assertEquals(testPayer, testPayment.getPayer());
        assertEquals(testPayee, testPayment.getPayee());
    }
}
