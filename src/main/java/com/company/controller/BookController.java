package com.company.controller;

import com.company.model.Book;
import com.company.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/book")
public class BookController extends BaseController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/save")
    public String addBook(final Model model, @ModelAttribute Book book) {
        LOG.debug("Save or update book: " + book.toString());

        Book updatedBook = bookService.findOne(book.getId());
        if (updatedBook != null) {
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setTitle(book.getTitle());
            updatedBook.setReleaseDate(book.getReleaseDate());
        } else {
            updatedBook = new Book(book.getTitle(), book.getAuthor(), book.getReleaseDate());
        }

        bookService.save(updatedBook);

        return "redirect:/book/list";
    }

    @GetMapping(value = "/edit")
    public String addBook(final Model model, @RequestParam(required = false) Long id) {

        if (id != null) {
            model.addAttribute("book", bookService.findOne(id));
        }

        return "book/edit";
    }

    @GetMapping(value = "/list")
    public String bookList(final Model model) {
        LOG.debug("Get book list");

        Page<Book> books = bookService.findAll(PageRequest.of(0, 10));
        model.addAttribute("books", books.getContent());

        return "book/index";
    }
}