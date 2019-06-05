package com.loquinecrud.loquinecrud.service;

import com.loquinecrud.loquinecrud.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
