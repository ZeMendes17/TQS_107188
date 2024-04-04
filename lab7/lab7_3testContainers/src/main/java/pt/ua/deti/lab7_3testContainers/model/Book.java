package pt.ua.deti.lab7_3testContainers.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", nullable = false)
    private Date publicationDate;

    // Constructors
    public Book() {
    }

    public Book(String title, String author, Date publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    // toString
    @Override
    public String toString() {
        return "Book [author=" + author + ", id=" + id + ", publicationDate=" + publicationDate + ", title=" + title + "]";
    }
}
