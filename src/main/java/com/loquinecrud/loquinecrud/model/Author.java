package com.loquinecrud.loquinecrud.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Please provide name")
    private String name;

    @NotBlank(message = "Please provide contact number")
    private String contact_number;

    @Past(message = "Birth date must be a past date")
    private Date date_of_birth;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

    public Author() {}

    public Author(int id, String name, String contact_number, @Past Date date_of_birth, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.contact_number = contact_number;
        this.date_of_birth = date_of_birth;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", books=" + books +
                '}';
    }
}
