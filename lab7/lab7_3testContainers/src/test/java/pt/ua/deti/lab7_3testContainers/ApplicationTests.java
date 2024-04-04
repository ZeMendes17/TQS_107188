package pt.ua.deti.lab7_3testContainers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import pt.ua.deti.lab7_3testContainers.repository.BookRepo;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import pt.ua.deti.lab7_3testContainers.model.Book;

@Testcontainers
@SpringBootTest
public class ApplicationTests {
    
    // Prepare the PostgreSQL Database
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer()
        .withDatabaseName("test")
        .withUsername("user")
        .withPassword("password");

    @Autowired
    private static BookRepo bookRepo;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    // Insert some content
    @BeforeAll
    public static void setup() {
        bookRepo.save(new Book("The Hobbit", "J.R.R. Tolkien", new Date()));
        bookRepo.save(new Book("The Lord of the Rings", "J.R.R. Tolkien", new Date()));
    }

    // Read it back
    @Test
    public void allBooksTest() {
        List<Book> books = bookRepo.findAll();
        assertEquals(2, books.size());
    }

    @Test
    public void findBookByTitleTest() {
        List<Book> books = bookRepo.findByTitle("The Hobbit");
        assertEquals(1, books.size());
        Book book= books.get(0);
        assertEquals("The Hobbit", book.getTitle());
    }

    @Test
    public void findBookByAuthorTest() {
        List<Book> books = bookRepo.findByAuthor("J.R.R. Tolkien");
        assertEquals(2, books.size());
    }
}
