package be.ehb.mct;

import java.time.LocalDate;

public class Author {

    private final String name;
    private final String firstName;
    private final LocalDate birthdate;

    public Author(String name, String firstName, LocalDate birthdate){
        this.name = name;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }

}
