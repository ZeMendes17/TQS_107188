package pt.ua.deti;

import java.util.List;
import java.util.ArrayList;
public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addAllBooks(Collection<Book> books) {
        this.books.addAll(books);
    }

    public List<Book> booksByPublishedDate(Date start, Date end) {
        return books.stream()
            .filter(b -> b.getPublished().after(start) && b.getPublished().before(end))
            .collect(Collectors.toList());
    }

    public List<Book> booksByTitle(String title) {
        return books.stream()
            .filter(b -> Objects.equals(title, b.getTitle()))
            .collect(Collectors.toList());
    }

    public List<Book> booksByAuthor(String author) {
        return books.stream()
            .filter(b -> Objects.equals(author, b.getAuthor()))
            .collect(Collectors.toList());
    }
}
