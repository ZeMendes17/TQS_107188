package pt.ua.deti.lab7_3testContainers;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import pt.ua.deti.lab7_3testContainers.repository.BookRepo;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import pt.ua.deti.lab7_3testContainers.model.Book;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationTests {
    
    // Prepare the PostgreSQL Database
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:13.2")
        .withDatabaseName("books")
        .withUsername("user")
        .withPassword("password");

    @Autowired
    private BookRepo bookRepo;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @AfterEach
    public void clean() {
        bookRepo.deleteAll();
    }

    @Test
    @Order(1)
    public void allBooksTest() {
        bookRepo.save(new Book("The Hobbit", "J.R.R. Tolkien", 2012));
        bookRepo.save(new Book("The Lord of the Rings", "J.R.R. Tolkien", 2014));

        List<Book> books = bookRepo.findAll();
        assertEquals(2, books.size());
    }

    @Test
    @Order(2)
    public void findBookByTitleTest() {
        bookRepo.save(new Book("The Hobbit", "J.R.R. Tolkien", 2012));
        bookRepo.save(new Book("The Lord of the Rings", "J.R.R. Tolkien", 2014));

        List<Book> books = bookRepo.findByTitle("The Hobbit");
        assertEquals(1, books.size());
        Book book= books.get(0);
        assertEquals("The Hobbit", book.getTitle());
    }

    @Test
    @Order(3)
    public void findBookByAuthorTest() {
        bookRepo.save(new Book("The Hobbit", "J.R.R. Tolkien", 2012));
        bookRepo.save(new Book("The Lord of the Rings", "J.R.R. Tolkien", 2014));

        List<Book> books = bookRepo.findByAuthor("J.R.R. Tolkien");
        assertEquals(2, books.size());
    }
}
