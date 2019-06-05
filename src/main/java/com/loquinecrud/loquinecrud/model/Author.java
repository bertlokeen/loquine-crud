package com.loquinecrud.loquinecrud.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String contact_number;

    @NonNull @Past
    private Date date_of_birth;

    public Author() {}

    public Author(long id, String name, String contact_number, @Past Date date_of_birth, List<Book> books) {
        this.id = id;
        this.name = name;
        this.contact_number = contact_number;
        this.date_of_birth = date_of_birth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }
}
