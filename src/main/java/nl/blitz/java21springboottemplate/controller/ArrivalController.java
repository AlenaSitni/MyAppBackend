package nl.blitz.java21springboottemplate.controller;

import nl.blitz.java21springboottemplate.entity.Arrival;
import nl.blitz.java21springboottemplate.service.ArrivalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arrivals")
@CrossOrigin // allow frontend to call it
public class ArrivalController {

    private final ArrivalService arrivalService;

    public ArrivalController(ArrivalService arrivalService) {
        this.arrivalService = arrivalService;
    }

    // -----------------------------------------------------
    // Fetch new arrivals from NS API
    // -----------------------------------------------------
    @PostMapping("/refresh")
    public ResponseEntity<List<Arrival>> refreshArrivals() {
        List<Arrival> arrivals = arrivalService.refreshArrivals();
        return ResponseEntity.ok(arrivals);
    }

    // -----------------------------------------------------
    // Get delayed trains
    // -----------------------------------------------------
    @GetMapping("/delayed")
    public List<Arrival> getDelayedArrivals() {
        return arrivalService.getDelayedArrivals();
    }

    // -----------------------------------------------------
    // Get cancelled trains
    // -----------------------------------------------------
    @GetMapping("/cancelled")
    public List<Arrival> getCancelledArrivals() {
        return arrivalService.getCancelledArrivals();
    }

    // -----------------------------------------------------
    // Get ALL trains
    // -----------------------------------------------------
    @GetMapping("/all")
    public List<Arrival> getAllArrivals() {
        return arrivalService.getAllArrivals();
    }
}