package com.example.obrestjpa.service;

import com.example.obrestjpa.entities.Book;
import com.example.obrestjpa.repository.BookRepository;
import com.example.obrestjpa.util.CsvHelper;
import com.example.obrestjpa.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository repository;


    @Override
    public void addBook(Book book) {
        repository.save(book);
    }

     @Override
    public void save(MultipartFile file) {
        try {
            List<Book> bookList = CsvHelper.csvToBBDD(file.getInputStream());
            repository.saveAll(bookList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveExcel(MultipartFile file) {
        try {
            List<Book> bookList = ExcelHelper.excelToBook(file.getInputStream());
            this.repository.saveAll(bookList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream load() {
        List<Book> bookList = repository.findAll();

        ByteArrayInputStream in = ExcelHelper.booksToExcel(bookList);
        return in;
    }

    @Override
    public List<Book> getBookList() { return repository.findAll(); }
}
