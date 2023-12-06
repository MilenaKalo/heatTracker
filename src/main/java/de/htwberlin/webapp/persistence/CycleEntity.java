package de.htwberlin.webapp.persistence;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name= "Cycle")
public class CycleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_date", nullable = false)
    private LocalDate startdate;
    @Column(name = "end_date", nullable = false)
    private LocalDate enddate;

    private boolean silentHeat;

    private boolean splitHeat;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    private FemaleDogEntity dog;

        public CycleEntity( LocalDate startdate, LocalDate enddate, boolean silentHeat, boolean splitHeat, FemaleDogEntity dog) {
            this.startdate = startdate;
            this.enddate = enddate;
            this.silentHeat = silentHeat;
            this.splitHeat = splitHeat;
            this.dog = dog;
        }

        protected CycleEntity() {
        }

        public Long getId() {
            return id;
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

        public FemaleDogEntity getDog() {
            return dog;
        }

        public void setDog(FemaleDogEntity dog) {
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
