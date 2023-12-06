package de.htwberlin.webapp.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "HealthRecord")
public class HealthRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private LocalDate startDate;

    private LocalDate endDate;
    private String symptom;
    private String note;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private FemaleDogEntity dog;

    protected HealthRecordEntity() {

    }
    public HealthRecordEntity( LocalDate startDate, LocalDate endDate, String symptom, String note,  FemaleDogEntity dog) {
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


    public FemaleDogEntity getDog() {
        return dog;
    }

    public void setDog(FemaleDogEntity dog) {
        this.dog = dog;
    }
}
