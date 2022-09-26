package com.example.obrestjpa.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Books")
public class Book {

    //1. Atributos encapsulados

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private Integer pages;
    @Column
    private Double price;
    @Column
    private LocalDate releaseDate;
    @Column
    private Boolean digital;


    //2. Constructores

    public Book(){}

    public Book(Long id, String title, String author, Integer pages, Double price, LocalDate releaseDate, Boolean digital) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.releaseDate = releaseDate;
        this.digital = digital;
    }


    //3. Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getDigital() {
        return digital;
    }

    public void setDigital(Boolean digital) {
        this.digital = digital;
    }

    //4. toString
}
