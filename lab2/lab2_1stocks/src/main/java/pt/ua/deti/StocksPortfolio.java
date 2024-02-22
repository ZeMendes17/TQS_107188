package pt.ua.deti;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private final IStockmarketService stockmarket;
    private final List<Stock> stocks;

    // constructor
    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<>();
    }

    // methods
    public void addStock(Stock s) {
        stocks.add(s);
    }

    public double totalValue() {
        double total = 0;
        for (Stock s : stocks) {
            total += stockmarket.lookUpPrice(s.getLabel()) * s.getQuantity();
        }

        return total;
    }
}
