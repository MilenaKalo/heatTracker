package de.htwberlin.webapp.persistence;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
public class UserEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="firstname", nullable = false)
    private String firstname;
    @Column(name="lastname", nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<FemaleDogEntity> dogs = new ArrayList<>();
    protected UserEntity() {
    }

    public UserEntity(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;

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

    public List<FemaleDogEntity> getDogs() {
        return dogs;
    }

    public void setDogs(List<FemaleDogEntity> dogs) {
        this.dogs = dogs;
    }
}


