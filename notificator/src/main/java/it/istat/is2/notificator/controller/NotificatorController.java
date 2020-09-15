package it.istat.is2.notificator.controller;

import it.istat.is2.notificator.domain.enums.TaskStatus;
import it.istat.is2.notificator.domain.enums.entity.ProcessEntity;
import it.istat.is2.notificator.exceptions.NoDataException;
import it.istat.is2.notificator.repository.ProcessRepository;
import it.istat.is2.notificator.response.TaskStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@Slf4j
public class NotificatorController {

    private final ProcessRepository processRepository;

    @Autowired
    public NotificatorController(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @GetMapping("/task/status/{id}")
    public ResponseEntity<TaskStatusResponse> getTaskStatus(@PathVariable("id") Long id) {

        Optional<ProcessEntity> optionalProcess = this.processRepository.findById(id);

        if (optionalProcess.isPresent()) {
            return ResponseEntity.of(Optional.ofNullable(new TaskStatusResponse.Builder().processId(id).status(TaskStatus.fromCode(optionalProcess.get().getStatus())).build()));
        } else {
            throw new NoDataException("Impossibile trovare il processo con id : " + id);
            /*throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Impossibile trovare il processo con id : " + id));

             */
        }
    }
}
