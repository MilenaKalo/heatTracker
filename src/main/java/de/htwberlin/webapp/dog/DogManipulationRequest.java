package de.htwberlin.webapp.dog;

import java.time.LocalDate;

public class DogManipulationRequest {
    private String name;

    private LocalDate birthDate;

    private String breed;

    private Long ownerId;
    public DogManipulationRequest ( String name, LocalDate birthDate, String breed, Long ownerId) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.ownerId = ownerId;
    }
    public DogManipulationRequest () {

    }
    // Getters and Setters
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
