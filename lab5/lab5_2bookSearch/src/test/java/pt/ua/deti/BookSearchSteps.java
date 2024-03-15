package pt.ua.deti;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookSearchSteps {
    Library library = new Library();
    List<Book> books = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public Date iso8601Date(String date) {
        String[] parts = date.split("-");
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];
        return new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    @Given("that the library has the following books")
    public void getBooks(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            String title = columns.get("title");
            String author = columns.get("author");
            String published = columns.get("published");
            Date publishedDate = iso8601Date(published);
            Book book = new Book(title, author, publishedDate);
            library.addBook(book);
        }
    }

    @When("the customer searches for books published between {int} and {int}")
    public void searchBooksByDate(int from, int to) {
        books = library.booksByPublishedYear(from, to);
    }

    @When("the customer searches for books with the title {string}")
    public void searchBooksByTitle(String title) {
        books = library.booksByTitle(title);
    }

    @When("the customer searches for books written by {string}")
    public void searchBooksByAuthor(String author) {
        books = library.booksByAuthor(author);
    }

    @Then("{int} books should have been found")
    public void booksFound(int count) {
        assertEquals(count, books.size());
    }

    @And("Book {int} should have the title {string}")
    public void bookTitle(int index, String title) {
        assertEquals(title, books.get(index - 1).getTitle());
    }
}
