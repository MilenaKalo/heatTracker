package de.htwberlin.webapp.health;


import de.htwberlin.webapp.cycle.Cycle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class HealthRecordController {
    private HealthRecordService healthRecordService;

    public HealthRecordController(HealthRecordService healthRecordService) {
        this.healthRecordService = healthRecordService;
    }

    @GetMapping(path = "/api/v1/healthrecords")
    public ResponseEntity<List<HealthRecord>> getHealthRecords() {
        List<HealthRecord> healthRecords = healthRecordService.findAll();
        return ResponseEntity.ok(healthRecords);
    }

    @GetMapping(path = "/api/v1/healthrecords/{id}")
    public ResponseEntity<HealthRecord> fetchHealthRecordById(@PathVariable Long id) {
        var healthRecord = healthRecordService.findById(id);
        return healthRecord != null? ResponseEntity.ok(healthRecord) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/v1/dogs/{dogId}/healthrecords")
    public ResponseEntity<List<HealthRecord>> getHealthRecordsByDogId(@PathVariable Long dogId) {
        List<HealthRecord> recordServiceByDogId = healthRecordService.findByDogId(dogId);

        if (recordServiceByDogId.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(recordServiceByDogId);
        }
    }

    @PostMapping(path = "/api/v1/healthrecords")
    public ResponseEntity<Void> createHealthRecord(@RequestBody HealthRecordManipulationRequest request) throws URISyntaxException {
        var HealthRecord= healthRecordService.create(request);
        URI uri = new URI("/api/v1/healthrecords/" + HealthRecord.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/healthrecords/{id}")
    public ResponseEntity<HealthRecord> updateHealthRecord(@PathVariable Long id, @RequestBody HealthRecordManipulationRequest request) {
        var healthRecord = healthRecordService.update(id, request);
        return healthRecord!= null? ResponseEntity.ok(healthRecord) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/healthrecords/{id}")
    public ResponseEntity<Void> deleteHealthRecord(@PathVariable Long id) {
        boolean successful = healthRecordService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}

