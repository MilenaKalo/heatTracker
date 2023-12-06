package de.htwberlin.webapp.health;

import de.htwberlin.webapp.dog.Dog;

import java.time.LocalDate;

public class HealthRecord {

    private Long id;
    private LocalDate startDate;

    private LocalDate endDate;
    private String symptom;
    private String note;

    private Dog dog;

    public HealthRecord() {

    }
    public HealthRecord(Long id, LocalDate startDate, String symptom, String note, LocalDate endDate, Dog dog) {
        this.id = id;
        this.startDate = startDate;
        this.symptom = symptom;
        this.note = note;
        this.endDate = endDate;
        this.dog = dog;
    }
    // Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public Dog getDog() {
            return dog;
        }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
