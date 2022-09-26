package com.example.obrestjpa.controller;

import com.example.obrestjpa.entities.Book;
import com.example.obrestjpa.repository.BookRepository;
import com.example.obrestjpa.service.BookService;
import com.example.obrestjpa.util.CsvHelper;
import com.example.obrestjpa.util.ExcelHelper;
import com.example.obrestjpa.util.PdfGenerator;
import com.example.obrestjpa.util.ResponseMessage;
import com.lowagie.text.DocumentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Book")
public class BookController {

    BookRepository repository;

    private final Logger log = LoggerFactory.getLogger(BookController.class); // creamos una variable para ir guardando los logs de las llamadas a la API REST

    @Autowired
    private BookService bookService;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

//    @RequestMapping("/")
//    public String HW2(){
//        return null;
//    }


    //CRUD sobre la entidad Book

    //1. Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAllBooks(){
        return repository.findAll();
    }



    //2. Buscar un libro por ID

    //Opción 1
    /*
    @GetMapping("/api/books/{id}")
    public Book findByIdBook(@PathVariable Long id){
        Optional<Book> bookOptional = repository.findById(id);

        //Opción 1
        if(bookOptional.isPresent()){
            return bookOptional.get();
        }
        return null;

        //Opción 2
        return bookOptional.orElse(null);
    }
    */


    //Opción 2
    @GetMapping("/api/books/{id}")
    @ApiOperation("Buscar un libro por clave primaria id Long")
    public ResponseEntity<Book> findByIdBook(@PathVariable Long id){
        Optional<Book> bookOptional = repository.findById(id);

        //Opción 1
        if(bookOptional.isPresent()){
            return ResponseEntity.ok(bookOptional.get());
        }
        return ResponseEntity.notFound().build();

        /*
        //Opción 2
                if(bookOptional.isPresent()){
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        */
    }


    //3. Crear un libro
    @PostMapping("api/createBook/")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // guardar el libro recibido por parámetro en la base de datos
        if(book.getId() != null){ // quiere decir que existe el id y por tanto no es una creación
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = repository.save(book);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }


    //4. Actualizar un libro
    @PutMapping("/api/updateBook/")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        if(book.getId() == null ){ // si no tiene id quiere decir que sí es una creación
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if(!repository.existsById(book.getId())){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        // El proceso de actualización
        Book result = repository.save(book);
        return ResponseEntity.ok(result); // el libro devuelto tiene una clave primaria
    }


    //5. Eliminar un libro
    @DeleteMapping("/api/deleteBook/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        if(!repository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    //6. Eliminar todos los libros
    @DeleteMapping("/api/deleteBooks/")
    public ResponseEntity<Book> deleteAllBooks(){
        log.warn("Trying to delete a non existent book");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }




    //EXTRA v1.0. Exportar todos los libros en un fichero PDF

    @GetMapping("/api/exportar")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        String currentDateTime = dateFormat.format(new Date());

        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=Libros_" + currentDateTime + ".pdf";

        response.setHeader(headerkey, headervalue);

        List<Book> bookList = bookService.getBookList();

        PdfGenerator generator = new PdfGenerator();
        generator.generate(bookList, response);

    }
}
