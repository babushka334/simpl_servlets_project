package by.mops.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class User implements Serializable {
    private Long id;
    private String username;
    private Boolean isAdmin;
    private Integer role;
    private LocalDate birthday;
    private String firstName;
    private String lastName;
    private Double balance = 0.0;
    private List<Bet> bets;

    public User(Long id, String username, Boolean isAdmin, Integer role, LocalDate birthday, String firstName, String lastName, Double balance) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
        this.role = role;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public User(Long id, String username, Boolean isAdmin, Integer role, LocalDate birthday, String firstName, String lastName, List<Bet> bets) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
        this.role = role;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bets = bets;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User(){}

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", isAdmin=" + isAdmin +
                ", role=" + role +
                ", birthday=" + birthday +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bets=" + bets +
                '}';
    }
}
