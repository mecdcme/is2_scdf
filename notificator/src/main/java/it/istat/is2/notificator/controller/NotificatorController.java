package it.istat.is2.notificator.controller;

import it.istat.is2.notificator.domain.enums.TaskStatus;
import it.istat.is2.notificator.domain.enums.entity.ProcessEntity;
import it.istat.is2.notificator.exceptions.NoDataException;
import it.istat.is2.notificator.repository.ProcessRepository;
import it.istat.is2.notificator.response.TaskStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        }
    }

    @PostMapping("/task")
    public ResponseEntity<TaskStatus> createTask(@RequestBody TaskStatus taskStatus) {
        throw new IllegalStateException("not implemented yet");
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<TaskStatus> deleteTask(@PathVariable("id") Long id) {
        throw new IllegalStateException("not implemented yet");
    }


}
