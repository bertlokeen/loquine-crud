package com.loquinecrud.loquinecrud;

import com.loquinecrud.loquinecrud.model.Author;
import com.loquinecrud.loquinecrud.service.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.LongStream;

@Component
public class AuthorRepositoryCommandLineRunner implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        authorRepository.deleteAll();
        LongStream.range(1, 10).mapToObj(i -> {
            Author author = new Author();
            author.setName("Author Name " + i);
            author.setContact_number("+6391234568" + i);
            author.setDate_of_birth(new Date());

            return author;
        }).map(author -> authorRepository.save(author)).forEach(System.out::println);
    }
}
