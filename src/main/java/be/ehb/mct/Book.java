package be.ehb.mct;

public class Book {

    private String title;
    private double price; //public for reductions??? maybe not immutable
    private Author author;
    private int isbn;
    private int totalPages;
    private Genre[] genres; //

    public Book(String title, double price, Author author, int isbn, int totalPages, Genre[] genres) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.isbn = isbn;
        this.totalPages = totalPages;
        this.genres = genres;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

}


