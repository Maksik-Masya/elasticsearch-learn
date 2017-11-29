package com.company.service;

import com.company.model.Book;
import com.company.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    public void delete(final Book book) {
        bookRepository.delete(book);
    }

    public Book findOne(final Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Page<Book> findAll(final Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<Book> findByAuthor(final String author, final PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }

    public List<Book> findByTitle(final String title) {
        return bookRepository.findByTitle(title);
    }

}