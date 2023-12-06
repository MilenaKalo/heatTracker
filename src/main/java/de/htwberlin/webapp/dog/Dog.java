package de.htwberlin.webapp.dog;


import de.htwberlin.webapp.cycle.Cycle;
import de.htwberlin.webapp.login.User;

import java.time.LocalDate;
import java.util.List;

public class Dog {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String breed;

    private User owner;

    private List<Long> cycles;

    private List<Long> healthRecords;

    public Dog(Long id, String name, LocalDate birthDate, String breed, User owner, List<Long> cycles, List<Long> healthRecords) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.owner = owner;
        this.cycles = cycles;
        this.healthRecords = healthRecords;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public List<Long> getCycles() {
        return cycles;
    }

    public void setCycles(List<Long> cycles) {
        this.cycles = cycles;
    }

    public List<Long> getHealthRecords() {
        return healthRecords;
    }

    public void setHealthRecords(List<Long> healthRecords) {
        this.healthRecords = healthRecords;
    }
}

