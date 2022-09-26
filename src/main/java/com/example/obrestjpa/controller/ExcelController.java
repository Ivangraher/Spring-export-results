package com.example.obrestjpa.controller;

import java.io.IOException;
import java.util.List;

import com.example.obrestjpa.entities.Book;
import com.example.obrestjpa.service.BookService;
import com.example.obrestjpa.service.BookServiceImpl;
import com.example.obrestjpa.util.ExcelHelper;
import com.example.obrestjpa.util.ResponseMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/api/excel")
@Controller
@Api(value = "Excel")
public class ExcelController {

    @Autowired
    private BookService bookService;


    //EXTRA v3.0. Subir un fichero .excel para crear la BBDD
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestBody MultipartFile file){
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                bookService.saveExcel(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Book>> getAllTutorials() {
        try {
            List<Book> tutorials = bookService.getBookList();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "books.xlsx";
        InputStreamResource file = new InputStreamResource(bookService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
