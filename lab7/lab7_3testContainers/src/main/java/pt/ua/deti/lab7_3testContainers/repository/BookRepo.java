package pt.ua.deti.lab7_3testContainers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.deti.lab7_3testContainers.model.Book;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long>{
    List<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
