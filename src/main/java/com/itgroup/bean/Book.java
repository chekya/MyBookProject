package com.itgroup.bean;

public class Book {
    private int id ;
    private String title ;
    private String author ;
    private String publisher ;
    private String publication_date ;
    private int number_of_pages ;
    private String category ;
    private int price ;
    private String ebook ;

    public Book(int id, String title, String author, String publisher, String publication_date, int number_of_pages, String category, int price, String ebook) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publication_date = publication_date;
        this.number_of_pages = number_of_pages;
        this.category = category;
        this.price = price;
        this.ebook = ebook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publication_date='" + publication_date + '\'' +
                ", number_of_pages=" + number_of_pages +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", ebook='" + ebook + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEbook() {
        return ebook;
    }

    public void setEbook(String ebook) {
        this.ebook = ebook;
    }
}
