package be.ehb.mct.model;

import java.util.Objects;

public final class Book {

    private final String title;
    private double price;
    private final Author author;
    private final int isbn;
    private final int totalPages;
    private final Genre genres;
    private final String language;

    public Book(String title, double price, Author author, int isbn, int totalPages, Genre genres, String language) throws IllegalArgumentException{
        if (title == null) throw new IllegalArgumentException("Title needs to a be string");
        this.title = title;
        if (price == 0 || price<0) throw new IllegalArgumentException("Price must be higher than 0");
        this.price = price;
        this.author = author;
        this.isbn = isbn;
        this.totalPages = totalPages;
        this.genres = genres;
        this.language = language;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) throws IllegalArgumentException {
        if (price == 0  )
        this.price = price;
    }

    public Author getAuthor() { return author; }

    public int getTotalPages() { return totalPages; }

    public Genre getGenres() { return genres; }

    public String getTitle() {
        return title;
    }

    public int getIsbn() { return isbn; }

    public String getLanguage() { return language; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 && totalPages == book.totalPages && title.equals(book.title) && author.equals(book.author) && genres == book.genres;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, author, isbn, totalPages, genres, language);
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


