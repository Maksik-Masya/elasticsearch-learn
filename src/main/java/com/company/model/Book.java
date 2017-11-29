package com.company.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Random;

@Document(indexName = "library", type = "books")
public class Book {

    @Id
    private Long id;

    private String title;

    private String author;

    private String releaseDate;

    public Book() {
    }

    public Book(final String title, final String author, final String releaseDate) {
        this.id = generateID();
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

    private long generateID() {
        final Random rn = new Random();
        final int minimum = 1;
        final int maximum = 100000;
        final int range = maximum - minimum + 1;
        return (long) (rn.nextInt(range) + minimum);
    }
}

