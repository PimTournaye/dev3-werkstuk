package be.ehb.mct.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Author {

    private final int book_id;
    private int author_id;
    private final String name;
    private final String firstName;
    private final Date birthdate;

    public Author(int book_id, int author_id, String name, String firstName, Date birthdate){
        this.book_id = book_id;
        this.author_id = author_id;
        if (!(firstName instanceof String) || !(name instanceof String) ) throw new IllegalArgumentException("Name needs to be made up of strings");
        this.name = name;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }

    public int getBook_id() { return book_id; }

    public int getAuthor_id() { return author_id; }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name) && firstName.equals(author.firstName) && birthdate.equals(author.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstName, birthdate);
    }


    public void setAuthor_id(int id) { //TODO: hide id?
        if (this.author_id == -1) this.author_id = id;
    }
}
