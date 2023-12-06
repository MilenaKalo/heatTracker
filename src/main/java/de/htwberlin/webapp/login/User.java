package de.htwberlin.webapp.login;

import java.util.List;

public class User {
    private String username;
    private String password;

    private String firstname;
    private String lastname;

    private Long id;


    private List<Long> dogIds;
    public User() {
    }

    public User(Long id, String username, String password, String firstname, String lastname, List<Long> dogIds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dogIds = dogIds;

    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getDogIds() {
        return dogIds;
    }

    public void setDogIds(List<Long> dogIds) {
        this.dogIds = dogIds;
    }
}


