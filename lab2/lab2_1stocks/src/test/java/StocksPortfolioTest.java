import pt.ua.deti.IStockmarketService;
import pt.ua.deti.Stock;
import pt.ua.deti.StocksPortfolio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // annotation needed to use the InjectMocks and Mock annot.
public class StocksPortfolioTest {
    @InjectMocks // class that wants to use the mock
    StocksPortfolio stocksPortfolio; // instance of the subject under test

    @Mock // mock instance
    IStockmarketService stockmarketService; // mock substitute

    @Test
    void getTotalValue() {
        // load the mock with the proper expectations
        when(stockmarketService.lookUpPrice("TSLA")).thenReturn(4.0);
        when(stockmarketService.lookUpPrice("NVDA")).thenReturn(2.5);

        // create our stocks
        Stock tesla = new Stock("TSLA", 2);
        Stock nvidia = new Stock("NVDA", 4);

        // Execute the test (add the stocks to the portfolio)
        stocksPortfolio.addStock(tesla);
        stocksPortfolio.addStock(nvidia);
        // get the total value
        double totalValue = stocksPortfolio.totalValue();

        // verify the result (assert)
        // assert using JUnit5
        assertEquals(18.0, totalValue);
        // assert using Hamcrest
        assertThat(totalValue, is(18.0));

        // verify the use of the mock (verify)
        verify(stockmarketService, times(2)).lookUpPrice(anyString());
    }
}
