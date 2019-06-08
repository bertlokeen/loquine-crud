package com.loquinecrud.loquinecrud.controller;

import com.loquinecrud.loquinecrud.exception.ResourceNotFoundException;
import com.loquinecrud.loquinecrud.model.Author;
import com.loquinecrud.loquinecrud.model.Book;
import com.loquinecrud.loquinecrud.service.AuthorRepository;
import com.loquinecrud.loquinecrud.service.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class BookController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // Get book list by author
    @GetMapping("/authors/{authorId}/books")
    public List<Book> index(@PathVariable long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    // Add book to author
    @PostMapping("/authors/{authorId}/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@PathVariable long authorId, @Valid @RequestBody Book book) {
        return authorRepository.findById(authorId).map(author -> {
                book.setAuthor(author);

                return bookRepository.save(book);
            }).orElseThrow(() -> new ResourceNotFoundException("Author with id " + authorId + "not found."));
    }

    // Get book by author
    @GetMapping("/authors/{authorId}/books/{id}")
    public ResponseEntity<Book> get(@PathVariable long authorId, @PathVariable long id) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author with id " + authorId + "not found.");
        }

        return bookRepository.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + "not found."));
    }

    // Update book by author
    @PutMapping("/authors/{authorId}/books/{id}")
    public ResponseEntity<Book> update(@PathVariable long authorId, @PathVariable long id, @Valid @RequestBody Book book) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + authorId + "not found."));

        return bookRepository.findById(id).map(data -> {
            data.setName(book.getName());
            data.setDescription(book.getDescription());
            data.setAuthor(book.getAuthor());
            data.setPublished_at(book.getPublished_at());

            Book update = bookRepository.save(data);

            return ResponseEntity.ok().body(update);
        }).orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + "not found."));
    }

    // Delete book by author
    @DeleteMapping("/authors/{authorId}/books/{id}")
    public ResponseEntity<?> delete(@PathVariable long authorId, @PathVariable long id) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author with id " + authorId + "not found.");
        }

        return bookRepository.findById(id).map(book -> {
            bookRepository.delete(book);

            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + "not found."));
    }
}
