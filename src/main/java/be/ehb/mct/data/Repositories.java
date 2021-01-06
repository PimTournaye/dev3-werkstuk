package be.ehb.mct.data;

public class Repositories {
    private static final BookRepository BOOKS_REPO = new BookRepository();
    private static final AuthorRepository AUTHOR_REPO = new AuthorRepository();

    private Repositories() { }

    public static BookRepository getBookRepository() {
            return BOOKS_REPO;
        }
    public static AuthorRepository getAuthorRepository() {
        return AUTHOR_REPO;
    }
}
