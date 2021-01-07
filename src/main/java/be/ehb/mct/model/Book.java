package be.ehb.mct.model;

import be.ehb.mct.data.Repositories;

import java.util.Objects;

public final class Book {

    private final String title;
    private double price;
    private int author_id;
    private int isbn;
    private final int totalPages;
    private final Genre genres;
    private final String language;

    public Book(int isbn, String title, double price, int author_id, int totalPages, Genre genres, String language) throws IllegalArgumentException{
        if (title == null || title.trim().length() == 0) throw new IllegalArgumentException("Title needs to a be string");
        this.title = title;
        if (price == 0 || price<0) throw new IllegalArgumentException("Price must be higher than 0");
        this.price = price;
        this.author_id = author_id;
        this.isbn = isbn;
        this.totalPages = totalPages;
        this.genres = genres;
        this.language = language;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) throws IllegalArgumentException {
        if (price == 0 || 0>price) throw new IllegalArgumentException("Price cannot be zero or negative. Capitalism ho!");
        this.price = price;
    }


    public int getTotalPages() { return totalPages; }

    public String getGenre() { return genres.toString(); }

    public String getTitle() {
        return title;
    }

    public int getIsbn() { return isbn; }

    public String getLanguage() { return language; }

    public void setId(int isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", author_id=" + author_id +
                ", isbn=" + isbn +
                ", totalPages=" + totalPages +
                ", genres=" + genres +
                ", language='" + language + '\'' +
                '}';
    }

    public String toFancyString(){
        return title + " written by " + Repositories.getAuthorRepository().getAuthor(author_id).getFullName() + " in " + language + ", at a price of " + price + "EUR.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 && author_id == book.author_id && isbn == book.isbn && totalPages == book.totalPages && title.equals(book.title) && genres == book.genres && language.equals(book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, author_id, isbn, totalPages, genres, language);
    }

    public int getAuthor() {
        return this.author_id;
    }
}


