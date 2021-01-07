package be.ehb.mct.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Author {

    private int author_id;
    private final String name;
    private final String firstName;
    private final LocalDate birthdate;

    public Author(int author_id, String name, String firstName, LocalDate birthdate){
        this.author_id = author_id;
        if (!(firstName instanceof String) || !(name instanceof String) ) throw new IllegalArgumentException("Name needs to be made up of strings");
        this.name = name;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }
    public String getFullName(){
        return name + " " + firstName;
    }

    public int getAuthor_id() { return author_id; }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setAuthor_id(int id) { //TODO: hide id?
        if (this.author_id == -1) this.author_id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return author_id == author.author_id && Objects.equals(name, author.name) && Objects.equals(firstName, author.firstName) && Objects.equals(birthdate, author.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author_id, name, firstName, birthdate);
    }

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
