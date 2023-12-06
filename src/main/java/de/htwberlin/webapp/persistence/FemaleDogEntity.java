package de.htwberlin.webapp.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name= "FemaleDog")
public class FemaleDogEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column(name = "name", nullable = false)
        private String name;
        @Column(name = "birth_date", nullable = false)
        private LocalDate birthDate;
        @Column(name = "breed", nullable = false)
        private String breed;

        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "owner_id", referencedColumnName = "id")
        private UserEntity owner;

        @OneToMany(mappedBy = "dog", fetch = FetchType.EAGER)
        private List<CycleEntity> cycles = new ArrayList<>();

        @OneToMany(mappedBy = "dog", fetch = FetchType.EAGER)
        private List<HealthRecordEntity> healthRecords = new ArrayList<>();

        public FemaleDogEntity(String name, LocalDate birthDate, String breed, UserEntity owner) {
                this.name = name;
                this.birthDate = birthDate;
                this.breed = breed;
                this.owner = owner;

        }

        protected FemaleDogEntity() {
        }
        // Getter und Setter
        public Long getId() {
                return id;
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

        public UserEntity getOwner() {
                return owner;
        }

        public void setOwner(UserEntity owner) {
                this.owner = owner;
        }

        public List<CycleEntity> getCycles() {
                return cycles;
        }

        public void setCycles(List<CycleEntity> cycles) {
                this.cycles = cycles;
        }

        public List<HealthRecordEntity> getHealthRecords() {
                return healthRecords;
        }

        public void setHealthRecords(List<HealthRecordEntity> healthRecords) {
                this.healthRecords = healthRecords;
        }
}
