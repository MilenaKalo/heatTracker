package de.htwberlin.webapp.health;

import java.time.LocalDate;

public class HealthRecordManipulationRequest {

    private LocalDate startDate;

    private LocalDate endDate;
    private String symptom;
    private String note;

    private Long dogId;

    public HealthRecordManipulationRequest() {

    }
    public HealthRecordManipulationRequest(LocalDate startDate, LocalDate endDate,String symptom, String note, Long dogId) {
        this.startDate = startDate;
        this.symptom = symptom;
        this.note = note;
        this.endDate = endDate;
        this.dogId = dogId;
    }
    // Getter und Setter

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


    public Long getDogId() {
        return dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }
}
