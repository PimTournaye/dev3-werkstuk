package be.ehb.mct.tests;

import be.ehb.mct.model.Author;
import be.ehb.mct.model.Book;
import be.ehb.mct.model.Genre;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BookTest {

    LocalDate date1 = LocalDate.of(1967, 1, 13);
    Author author1 = new Author("Dolce", "Gusto", LocalDate.of(1956, 12, 3));

    @Test
    void createBook() {
        Book release1 = new Book("Visual Basic 2005 Express", 15, author1, 134, 335, Genre.NON_FICTION, "English");
        System.out.println(release1);
    }

    @Test
    void setPrice() {
        Book book1 = new Book("Visual Basic 2005 Express", 15, author1, 134, 335, Genre.NON_FICTION, "English");
        double price = book1.getPrice();
        System.out.println(price);
        book1.setPrice(10);
        System.out.println(book1.getPrice());
    }
}

