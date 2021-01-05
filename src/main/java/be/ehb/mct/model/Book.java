package be.ehb.mct.model;

import java.util.Objects;

public final class Book {

    private final String title;
    private double price; //public for reductions??? maybe not immutable
    private final Author author;
    private final int isbn;
    private final int totalPages;
    private final Genre genres;
    private final String language;

    public Book(String title, double price, Author author, int isbn, int totalPages, Genre genres, String language) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.isbn = isbn;
        this.totalPages = totalPages;
        this.genres = genres;
        this.language = language;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() { return author; }

    public int getTotalPages() { return totalPages; }

    public Genre getGenres() { return genres; }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 && totalPages == book.totalPages && title.equals(book.title) && author.equals(book.author) && genres == book.genres;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, author, totalPages, genres);
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", author=" + author +
                ", isbn=" + isbn +
                ", totalPages=" + totalPages +
                ", genres=" + genres +
                ", language='" + language + '\'' +
                '}';
    }
}


