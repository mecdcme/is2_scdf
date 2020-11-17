package it.istat.is2.contingency_table.controller;

import it.istat.is2.app.bean.SessionBean;
import it.istat.is2.contingency_table.service.ContingencyTableService;
import it.istat.is2.worksession.domain.WorkSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class TestController {

    private final ContingencyTableService contingencyTableService;


    @Autowired
    public TestController(ContingencyTableService contingencyTableService) {
        this.contingencyTableService = contingencyTableService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam("id") Long id) throws Exception {


        log.info("started method test .. whit id = {}", id);

        this.contingencyTableService.contingecyTable(id);
        return ResponseEntity.ok("execution contingency table ok");
    }

}
