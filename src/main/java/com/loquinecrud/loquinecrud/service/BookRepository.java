package com.loquinecrud.loquinecrud.service;

import com.loquinecrud.loquinecrud.model.Author;
import com.loquinecrud.loquinecrud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorId(Integer id);
}
