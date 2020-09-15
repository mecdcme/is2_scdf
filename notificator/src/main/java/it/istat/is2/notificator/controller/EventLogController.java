package it.istat.is2.notificator.controller;

import it.istat.is2.notificator.request.CreateEventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("/event")
public class EventLogController {

    @GetMapping("/summary")
    public ResponseEntity<List<?>> getLogsSummary() {
        throw new IllegalStateException("Not implemented yet");
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<List<?>> getLogsDetails(@PathVariable("id") Long id) {
        throw new IllegalStateException("Not implemented yet");
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest createEventRequest) {
        throw new IllegalStateException("Not implemented yet");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        throw new IllegalStateException("Not implemented yet");
    }
}
