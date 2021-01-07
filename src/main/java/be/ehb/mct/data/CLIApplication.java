package be.ehb.mct.data;

import be.ehb.mct.model.Author;
import be.ehb.mct.model.Book;
import be.ehb.mct.model.Genre;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CLIApplication {
    public static void main(String[] args) {

        System.out.println("------------- INSERT AUTHOR ---------------");
        Author author = new Author(4, "Billet", "Miguel", LocalDate.of(1995, 2,15));
        Repositories.getAuthorRepository().addAuthor(author);
        System.out.println(Repositories.getAuthorRepository().getAuthor(4));

        System.out.println("------------- INSERT BOOK ---------------");
        Book book = new Book(5,"Golden Curry", 69.25, 4,  143, Genre.ADVENTURE, "Nederlands");
        Repositories.getBookRepository().addBook(book);


        System.out.println("--------------- GET BOOK WITH ID ----------------");

        Book selected_book = Repositories.getBookRepository().getBook(5);
        System.out.println(selected_book.toString());
        Author author_selected_book  = Repositories.getAuthorRepository().getAuthor(selected_book.getAuthor());
        System.out.println(author_selected_book.toString());


        System.out.println("--------------- DELETE BOOK ---------------");


        System.out.println("------------- GET ALL BOOKS -------------");
        List<Book> books = Repositories.getBookRepository().getBooks(null);
        for(Book b: books) {
            System.out.println(b);
        }

        SQLConnection.resetDatabase();
    }
}
