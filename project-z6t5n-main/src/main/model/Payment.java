package model;

// Represnts data for the information of a player who owes another player
//  and the amount they do.
public class Payment {
    private Player payer;
    private Player payee;
    private double amount;

    // EFFECTS: costructs a payment object with given payer, payee, and amount
    public Payment(Player payer, Player payee, double amount) {
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    public Player getPayer() {
        return payer;
    }

    public Player getPayee() {
        return payee;
    }

    public double getAmount() {
        return amount;
    }
}
