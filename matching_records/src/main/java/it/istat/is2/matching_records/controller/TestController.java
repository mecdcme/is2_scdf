package it.istat.is2.matching_records.controller;

import it.istat.is2.matching_records.service.MatchingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    private final MatchingRecordService matchingRecordService;

    @Autowired
    public TestController(MatchingRecordService matchingRecordService) {
        this.matchingRecordService = matchingRecordService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam("id") Long id) throws Exception {
        log.info("started method test .. whit id = {}", id);
        this.matchingRecordService.matchingRecord(id);
        return ResponseEntity.ok("execution contingency table ok");
    }
}
