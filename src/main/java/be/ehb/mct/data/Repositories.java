package be.ehb.mct.data;

public class Repositories {
    //private static final StudentRepository STUDENT_REPO = new InMemoryStudentsRepository();
    private static final BookRepository STUDENT_REPO = new BookRepository();
    private static final AuthorRepository ADDRESS_REPO = new AuthorRepository();

    private Repositories() { }

    public static BookRepository getStudentsRepository() {
            return BOOKS_REPO;
        }
    public static AuthorRepository getAddressRepository() {
        return AUTHOR_REPO;
    }
}
