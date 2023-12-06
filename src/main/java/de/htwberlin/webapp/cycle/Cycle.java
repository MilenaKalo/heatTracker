package de.htwberlin.webapp.cycle;
import de.htwberlin.webapp.dog.Dog;

import java.time.LocalDate;


public class Cycle {
    private Long id;

    private LocalDate startdate;

    private LocalDate enddate;

    private Dog dog;

    private boolean silentHeat;

    private boolean splitHeat;

    public Cycle(Long id, LocalDate startdate, LocalDate enddate, boolean silentHeat, boolean splitHeat, Dog dog) {
        this.id = id;
        this.startdate = startdate;
        this.enddate = enddate;
        this.silentHeat = silentHeat;
        this.splitHeat = splitHeat;
        this.dog = dog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
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
