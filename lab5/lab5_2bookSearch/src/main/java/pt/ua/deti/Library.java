package pt.ua.deti;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addAllBooks(Collection<Book> books) {
        this.books.addAll(books);
    }

    public List<Book> booksByPublishedYear(int from, int to) {
        Date fromYear = new Date(from, 1, 1);
        Date toYear = new Date(to, 12, 31);
        return books.stream()
            .filter(b -> b.getPublished().after(fromYear) && b.getPublished().before(toYear))
            .collect(Collectors.toList());
    }

    public List<Book> booksByTitle(String title) {
        return books.stream()
            .filter(b -> b.getTitle().contains(title))
            .collect(Collectors.toList());
    }

    public List<Book> booksByAuthor(String author) {
        return books.stream()
            .filter(b -> Objects.equals(author, b.getAuthor()))
            .collect(Collectors.toList());
    }
}
