package com.example.obrestjpa.util;

import com.example.obrestjpa.entities.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] headers = { "Id", "Title", "Author", "Pages", "Price", "Release Date", "Digital" };
    static String SHEET = "Books";
    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
    public static ByteArrayInputStream booksToExcel(List<Book> bookList) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

            int rowIdx = 1;
            for (Book book : bookList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(book.getId());
                row.createCell(1).setCellValue(book.getTitle());
                row.createCell(2).setCellValue(book.getAuthor());
                row.createCell(3).setCellValue(book.getPages());
                row.createCell(4).setCellValue(book.getPrice());
                row.createCell(5).setCellValue(book.getReleaseDate());
                row.createCell(6).setCellValue(book.getDigital());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Book> excelToBook(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            //Sheet sheet = workbook.getSheet(SHEET);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Book> books = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Book book = new Book();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            book.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            book.setTitle(currentCell.getStringCellValue());
                            break;
                        case 2:
                            book.setAuthor(currentCell.getStringCellValue());
                            break;
                        case 3:
                            book.setPages((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            book.setPrice((double) currentCell.getNumericCellValue());
                            break;
                        case 5:
                            book.setReleaseDate(LocalDate.parse(currentCell.getStringCellValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            break;
                        case 6:
                            book.setDigital(Boolean.parseBoolean(currentCell.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                books.add(book);
            }
            workbook.close();
            return books;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
