package de.htwberlin.webapp.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

// der Controller ruft nur noch den Service auf und gibt die Daten zurück
@RestController
public class CycleController {

    private CycleService cycleService;

    @Autowired
    public CycleController(CycleService cycleService) {
        this.cycleService = cycleService;
    }

    @GetMapping(path = "/api/v1/cycles")
    public ResponseEntity<List<Cycle>> getCycles() {
        List<Cycle> healthRecords = cycleService.findAll();
        return ResponseEntity.ok(healthRecords);
    }

    @GetMapping(path = "/api/v1/cycles/{id}")
    public ResponseEntity<Cycle> fetchCycleById(@PathVariable Long id) {
        var cycle = cycleService.findById(id);
        return cycle != null? ResponseEntity.ok(cycle) : ResponseEntity.notFound().build();
    }
    @GetMapping("/api/v1/dogs/{dogId}/cycles")
    public ResponseEntity<List<Cycle>> getCyclesByDogId(@PathVariable Long dogId) {
        List<Cycle> cycles = cycleService.findByDogId(dogId);

        if (cycles.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cycles);
        }
    }
    @PostMapping(path = "/api/v1/cycles")
    public ResponseEntity<Void> createCycle(@RequestBody CycleManipulationRequest request) throws URISyntaxException {
        var cycle= cycleService.create(request);
        URI uri = new URI("/api/v1/cycles/" + cycle.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/cycles/{id}")
    public ResponseEntity<Cycle> updateCycle(@PathVariable Long id, @RequestBody CycleManipulationRequest request) {
        var cycle = cycleService.update(id, request);
        return cycle != null? ResponseEntity.ok(cycle) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/cycles/{id}")
    public ResponseEntity<Void> deleteCycle(@PathVariable Long id) {
        boolean successful = cycleService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
