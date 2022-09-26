package com.example.obrestjpa.controller;

import com.example.obrestjpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

//
//    private TestRestTemplate template;
//
//    @Autowired
//    private RestTemplateBuilder builder;
//
//    @LocalServerPort
//    private int port;
//
//    @BeforeEach
//    void setUp() {
//        builder = builder.rootUri("http://localhost:" + port);
//        template = new TestRestTemplate(builder);
//    }
//
//    @Test
//    void findAllBooks() {
//        ResponseEntity<Book[]> response = template.getForEntity("/api/books", Book[].class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(200, response.getStatusCodeValue());
//
//        List<Book> books = Arrays.asList(response.getBody());
//        System.out.println(books.size());
//    }
//
//    @Test
//    void findByIdBook() {
//        ResponseEntity<Book> response  =
//                template.getForEntity("/api/books/2", Book.class);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void createBook() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        String json = """
//                {
//                    "title": "Libro creado desde Spring Test",
//                    "author": "Yuval Noah",
//                    "pages": 650,
//                    "price": 19.99,
//                    "releaseDate": "2019-12-01",
//                    "online": false
//                }
//                """;
//
//        HttpEntity<String> request = new HttpEntity<>(json,headers);
//
//        ResponseEntity<Book> response = template.exchange("api/createBook/", HttpMethod.POST, request, Book.class);
//
//        Book result = response.getBody();
//
//        assertEquals(1L, result.getId());
//        assertEquals("Libro creado con pruebas jUnit de Spring Test", result.getTitle());
//
//    }
//
//    @Test
//    void updateBook() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        String json = """
//                {
//                    "title": "Libro creado con Spring Test",
//                    "author": "JUnit 5",
//                    "pages": 698,
//                    "price": 99.99,
//                    "releaseDate": "2009-12-01",
//                    "online": true
//                }
//                """;
//
//        HttpEntity<String> request = new HttpEntity<>(json,headers);
//
//        ResponseEntity<Book> response = template.exchange("api/createBook/", HttpMethod.POST, request, Book.class);
//
//        Book result = response.getBody();
//
//        assertEquals(1L, result.getId());
//        assertEquals("Libro creado con pruebas jUnit de Spring Test", result.getTitle());
//    }
//
//    @Test
//    void deleteBook() {
//        ResponseEntity<Book> response  =
//                template.getForEntity("/api/books/2", Book.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    void deleteAllBooks() {
//    }
}