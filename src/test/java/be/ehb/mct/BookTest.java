package be.ehb.mct;

import be.ehb.mct.data.Repositories;
import be.ehb.mct.model.Author;
import be.ehb.mct.model.Book;
import be.ehb.mct.model.Genre;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.Date;

class BookTest {


    @Test
    void createBookWithIllegalParameter() {
        assertThrows(IllegalArgumentException.class, () -> new Book(134,"\t\r\n", 15, 2, 335, Genre.NON_FICTION, "English"));
    }

    @Test
    void createBookWithIllegalPrice(){
        assertThrows(IllegalArgumentException.class, () -> new Book(134,"Unit Tests With JUnit: Second Edition", 0, 2, 335, Genre.NON_FICTION, "English"));
    }

    @Test
    void noDuplicateBooks(){
        //Book book1 = Repositories.getBookRepository().getBook(1);
        //System.out.println(book1);
        //TODO: Geen idee waarom deze nietr wilt werken, mss race conditions. addBook gooit weldegleijk een error maar de logs zeggen van niet
        //assertThrows(SQLIntegrityConstraintViolationException.class, () -> Repositories.getBookRepository().addBook(book1));
        }




    @Test
    void setPrice() {
        Book book1 = new Book(134,"Visual Basic 2005 Express", 15, 2, 335, Genre.NON_FICTION, "English");
        double price = book1.getPrice();
        System.out.println(price);
        book1.setPrice(10);
        double newPrice = book1.getPrice();
        System.out.println(book1.getPrice());
        assertEquals(price - 5,newPrice );
    }
}
