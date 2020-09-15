package it.istat.is2.notificator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> displayStatus() {
        return ResponseEntity.of(Optional.of("I'm Alive"));
    }
}
