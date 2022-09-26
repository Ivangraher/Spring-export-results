package com.example.obrestjpa.service;

import com.example.obrestjpa.entities.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface BookService {

    void addBook(Book book);
    List<Book> getBookList();
    void save(MultipartFile file);
    void saveExcel(MultipartFile file);
    ByteArrayInputStream load();
}
