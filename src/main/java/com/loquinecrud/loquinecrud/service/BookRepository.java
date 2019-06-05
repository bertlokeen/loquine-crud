package com.loquinecrud.loquinecrud.service;

import com.loquinecrud.loquinecrud.model.Author;
import com.loquinecrud.loquinecrud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
//    Optional<Author> findByAuthorId(Long id, Long author_id);
}
