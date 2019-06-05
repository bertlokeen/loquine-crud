package com.loquinecrud.loquinecrud.controller;

import com.loquinecrud.loquinecrud.exception.ResourceNotFoundException;
import com.loquinecrud.loquinecrud.model.Author;
import com.loquinecrud.loquinecrud.service.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/authors")
@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> index() {
        return authorRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Author create(@Valid @RequestBody Author author) {
        return authorRepository.save(author);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> get(@PathVariable long id) {
        return authorRepository.findById(id).map(author -> ResponseEntity.ok().body(author))
                .orElseThrow(() -> new ResourceNotFoundException("Resource with id " + id + "not found."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable long id, @Valid @RequestBody Author author) {
        return authorRepository.findById(id).map(data -> {
            data.setName(author.getName());
            data.setContact_number(author.getContact_number());
            data.setDate_of_birth(author.getDate_of_birth());

            Author updated = authorRepository.save(data);

            return ResponseEntity.ok().body(updated);
        }).orElseThrow(() -> new ResourceNotFoundException("Resource with id " + id + "not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return authorRepository.findById(id).map(author -> {
                    authorRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Resource with id " + id + "not found."));
    }
}
