package it.istat.is2.contingency_table.controller;

import it.istat.is2.contingency_table.bean.AuthenticationTokenHolder;
import it.istat.is2.contingency_table.service.ContingencyTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    private final ContingencyTableService contingencyTableService;


    @Autowired
    public TestController(ContingencyTableService contingencyTableService) {
        this.contingencyTableService = contingencyTableService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam("id") Long id, @RequestParam("authorization") String authorization) throws Exception {

        log.info("started method test .. whit id = {} and authorizationToekm", id, authorization);
        AuthenticationTokenHolder.getInstance().setAuthenticationToken(authorization);

        this.contingencyTableService.contingecyTable(id);
        return ResponseEntity.ok("execution contingency table ok");
    }

}
