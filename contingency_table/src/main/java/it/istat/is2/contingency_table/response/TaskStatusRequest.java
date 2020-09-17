package it.istat.is2.contingency_table.response;

import lombok.Data;

@Data
public class TaskStatusRequest {
    private Long idElaborazione;
    private Long idStepInstance;
    private TaskStatus taskStatus;
}
