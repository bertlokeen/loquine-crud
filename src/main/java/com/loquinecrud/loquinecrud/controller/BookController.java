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

    @GetMapping("/authors/{authorId}/books")
    public List<Book> index(@PathVariable long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @PostMapping("/authors/{authorId}/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@PathVariable long authorId, @Valid @RequestBody Book book) {
        return authorRepository.findById(authorId).map(author -> {
                book.setAuthor(author);

                return bookRepository.save(book);
            }).orElseThrow(() -> new ResourceNotFoundException("Author with id " + authorId + "not found."));
    }

    @GetMapping("/authors/{authorId}/books/{id}")
    public ResponseEntity<Book> get(@PathVariable long authorId, @PathVariable long id) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author with id " + authorId + "not found.");
        }

        return bookRepository.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + "not found."));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Book> update(@PathVariable long id, @Valid @RequestBody Book book) {
//        return bookRepository.findById(id).map(data -> {
//            data.setName(book.getName());
//            data.setDescription(book.getDescription());
//            data.setAuthor(book.getAuthor());
//            data.setPublished_at(book.getPublished_at());
//
//            Book updated = bookRepository.save(data);
//
//            return ResponseEntity.ok().body(updated);
//        }).orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable long id) {
//        return bookRepository.findById(id).map(book -> {
//            bookRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }).orElse(ResponseEntity.notFound().build());
//    }
}
