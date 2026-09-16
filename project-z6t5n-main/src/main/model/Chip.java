package model;

// Represents a chip and its allocated value
public class Chip {
    private double value;

    // EFFECTS: contructrs a chip object wiht 0 value 
    public Chip()  {
        value = 0;
    }
    
    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
