package com.tts.spprojectMaven.controller;

import com.tts.spprojectMaven.model.Book;
import com.tts.spprojectMaven.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    // this is a from of dependency injection
    // we are allowing Book Controller to pick up a candidate for this field
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List <Book>findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }


    @GetMapping("/{id}")
    public Optional<Book> findOne(@PathVariable Long id) {
        return bookRepository.findById(id);
//                .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id);
//                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
//            throw new BookIdMismatchException();
        }
        bookRepository.findById(id);
//                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
}