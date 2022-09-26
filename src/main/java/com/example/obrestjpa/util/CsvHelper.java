package com.example.obrestjpa.util;

import com.example.obrestjpa.entities.Book;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {

    public static String TYPE = "text/csv";
    //static String[] headers = { "Id", "Title", "Author", "Pages", "Price", "Release Date", "Digital" };
    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Book> csvToBBDD(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<Book> bookList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Book book = new Book(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Title"),
                        csvRecord.get("Author"),
                        Integer.parseInt(csvRecord.get("Pages")),
                        Double.parseDouble(csvRecord.get("Price")),
                        LocalDate.parse(csvRecord.get("Release Date")),
                        Boolean.parseBoolean(csvRecord.get("Digital"))
                );
                bookList.add(book);
            }
            return bookList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
