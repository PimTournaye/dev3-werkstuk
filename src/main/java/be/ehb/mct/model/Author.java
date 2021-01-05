package be.ehb.mct.model;

import java.time.LocalDate;
import java.util.Objects;

public class Author {

    private final String name;
    private final String firstName;
    private final LocalDate birthdate;

    public Author(String name, String firstName, LocalDate birthdate){
        if (!(firstName instanceof String) || !(name instanceof String) ) throw new IllegalArgumentException("Name needs to be made up of strings");
        this.name = name;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthdate() {
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
}
