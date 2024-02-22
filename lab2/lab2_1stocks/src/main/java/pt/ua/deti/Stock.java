package pt.ua.deti;

public class Stock {
    private String label;
    private Integer quantity;

    // constructor
    public Stock(String label, Integer quantity) {
        this.label = label;
        this.quantity = quantity;
    }

    // getters
    public String getLabel() {
        return label;
    }

    public Integer getQuantity() {
        return quantity;
    }

    // setters
    public void setLabel(String label) {
        this.label = label;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
