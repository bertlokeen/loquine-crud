package com.loquinecrud.loquinecrud.controller;

import com.loquinecrud.loquinecrud.model.Author;
import com.loquinecrud.loquinecrud.model.Book;
import com.loquinecrud.loquinecrud.service.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book create(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable long id) {
        return bookRepository.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable long id, @Valid @RequestBody Book book) {
        return bookRepository.findById(id).map(data -> {
            data.setName(book.getName());
            data.setDescription(book.getDescription());
            data.setAuthor(book.getAuthor());
            data.setPublished_at(book.getPublished_at());

            Book updated = bookRepository.save(data);

            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return bookRepository.findById(id).map(book -> {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
