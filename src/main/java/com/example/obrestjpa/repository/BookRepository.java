package com.example.obrestjpa.repository;

import com.example.obrestjpa.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByPages(Integer pages);
    List<Book> findByAuthor(String author);
    List<Book> findByPrice(Double price);
//    List<Book> findByIsDigital();


}
