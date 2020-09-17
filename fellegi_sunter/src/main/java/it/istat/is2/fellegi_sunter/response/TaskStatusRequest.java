package it.istat.is2.fellegi_sunter.response;

import lombok.Data;

@Data
public class TaskStatusRequest {
    private Long idElaborazione;
    private Long idStepInstance;
    private TaskStatus taskStatus;
}
