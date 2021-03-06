package it.istat.is2.fellegi_sunter.controller;

import it.istat.is2.fellegi_sunter.service.FellegiSunterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    private final FellegiSunterService fellegiSunterService;

    @Autowired
    public TestController(FellegiSunterService fellegiSunterService) {
        this.fellegiSunterService = fellegiSunterService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam("id") Long id) throws Exception {
        log.info("started method test .. whit id = {}", id);
        this.fellegiSunterService.fellegiSunter(id);
        return ResponseEntity.ok("execution contingency table ok");
    }
}
