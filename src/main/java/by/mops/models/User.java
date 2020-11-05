package by.mops.models;

import java.time.LocalDate;
import java.util.List;

public class User {
    private Long id;
    private String username;
    private String password;
    private Boolean isAdmin;
    private Integer role;
    private LocalDate birthday;
    private String firstName;
    private String lastName;
    private List<Bet> bets;

    public User(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String firstName, String lastName, List<Bet> cars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bets = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
