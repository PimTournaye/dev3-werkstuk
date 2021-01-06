package be.ehb.mct.data;

import be.ehb.mct.model.Author;
import be.ehb.mct.model.Book;
import be.ehb.mct.model.Genre;

import java.time.LocalDate;
import java.util.Date;

public class CLIApplication {
    public static void main(String[] args) {

        System.out.println("------------- INSERT AUTHOR ---------------");
        Author author = new Author(7, "Billet", "Miguel", LocalDate.of(1995, 2,15));
        Repositories.getAuthorRepository().addAuthor(author);
        System.out.println(author.toString());

        System.out.println("------------- INSERT BOOK ---------------");
        Book book = new Book("", 69.25, author, 10, 143, Genre.ADVENTURE, "Nederlands");
        Repositories.getBookRepository().addBook(book);

        System.out.println("------------- GET ALL BOOKS -------------");

        //System.out.println(Repositories.getBookRepository().getBook(3));

        SQLConnection.resetDatabase();
    }
}
