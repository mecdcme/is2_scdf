package it.istat.is2.notificator.controller;

import it.istat.is2.notificator.domain.enums.TaskStatus;
import it.istat.is2.notificator.domain.enums.entity.ProcessEntity;
import it.istat.is2.notificator.exceptions.NoDataException;
import it.istat.is2.notificator.repository.ProcessRepository;
import it.istat.is2.notificator.request.TaskStatusRequest;
import it.istat.is2.notificator.response.TaskStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    private final ProcessRepository processRepository;

    @Autowired
    public TaskController(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @GetMapping("/status/last/{idElaborazione}/{idStepInstance}")
    public ResponseEntity<TaskStatusResponse> getLastTaskStatus(@PathVariable("idElaborazione") Long idElaborazione, @PathVariable("idStepInstance") Long idStepInstance) {

        List<ProcessEntity> optionalProcess = this.processRepository.findByProcessIdAndStepInstanceId(idElaborazione, idStepInstance);

        if (!optionalProcess.isEmpty()) {
            var last = optionalProcess.stream().sorted( (a,b) -> b.getCreationDate().compareTo(a.getCreationDate() ) ).findFirst().get();
            return ResponseEntity.of(Optional.ofNullable(new TaskStatusResponse.Builder().processId(idElaborazione).stepInstanceId(idStepInstance).status(TaskStatus.fromCode(last.getStatus())).build()));
        } else {
            throw new NoDataException("Impossibile trovare il processo con idElaborazione : " + idElaborazione + " e stepInstance : " + idStepInstance);
        }
    }

    @GetMapping("/status/{idElaborazione}/{idStepInstance}")
    public ResponseEntity<List<TaskStatusResponse>> getTaskStatus(@PathVariable("idElaborazione") Long idElaborazione, @PathVariable("idStepInstance") Long idStepInstance) {

        List<ProcessEntity> optionalProcess = this.processRepository.findByProcessIdAndStepInstanceId(idElaborazione, idStepInstance);

        if (!optionalProcess.isEmpty()) {
            var allStatus = optionalProcess.stream().sorted( (a,b) -> b.getCreationDate().compareTo(a.getCreationDate() ) ).collect(Collectors.toList());
            var result = new ArrayList(allStatus.size());

            allStatus.forEach(x -> {
                result.add(new TaskStatusResponse.Builder().processId(idElaborazione).stepInstanceId(idStepInstance).status(TaskStatus.fromCode(x.getStatus())).build());
            });

            return ResponseEntity.ok(result);
        } else {
            throw new NoDataException("Impossibile trovare il processo con idElaborazione : " + idElaborazione + " e stepInstance : " + idStepInstance);
        }
    }

    @PostMapping
    public ResponseEntity<TaskStatusResponse> createTask(@RequestBody TaskStatusRequest taskStatus) {
        ProcessEntity task = new ProcessEntity();
        task.setProcessId(taskStatus.getIdElaborazione());
        task.setStepInstanceId(taskStatus.getIdStepInstance());
        task.setStatus(taskStatus.getTaskStatus().getCode());

        this.processRepository.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new TaskStatusResponse.Builder().processId(taskStatus.getIdElaborazione()).stepInstanceId(task.getStepInstanceId()).status(taskStatus.getTaskStatus()).build()
        );
    }
}
