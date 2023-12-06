package de.htwberlin.webapp.cycle;

import java.time.LocalDate;

public class CycleManipulationRequest {

    private LocalDate startdate;
    private LocalDate enddate;

    private boolean silentHeat;

    private boolean splitHeat;

    private Long dogId;

    public CycleManipulationRequest() {
    }

    public CycleManipulationRequest(LocalDate startdate, LocalDate enddate, boolean silentHeat, boolean splitHeat, Long dogId) {
        this.startdate = startdate;
        this.enddate = enddate;
        this.silentHeat = silentHeat;
        this.splitHeat = splitHeat;
        this.dogId = dogId;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public Long getDogId() {
        return dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }

    public boolean isSilentHeat() {
        return silentHeat;
    }

    public void setSilentHeat(boolean silentHeat) {
        this.silentHeat = silentHeat;
    }

    public boolean isSplitHeat() {
        return splitHeat;
    }

    public void setSplitHeat(boolean splitHeat) {
        this.splitHeat = splitHeat;
    }
}
