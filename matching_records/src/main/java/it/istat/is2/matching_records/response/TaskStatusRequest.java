package it.istat.is2.matching_records.response;

import lombok.Data;

@Data
public class TaskStatusRequest {
    private Long idElaborazione;
    private Long idStepInstance;
    private TaskStatus taskStatus;
}
