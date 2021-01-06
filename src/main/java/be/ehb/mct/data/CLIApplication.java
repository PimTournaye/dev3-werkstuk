package be.ehb.mct.data;

public class CLIApplication {
    public static void main(String[] args) {

        System.out.println("------------- GET ALL BOOKS -------------");
        Repositories.getBookRepository().getBooks(null);

        SQLConnection.resetDatabase();
    }
}
