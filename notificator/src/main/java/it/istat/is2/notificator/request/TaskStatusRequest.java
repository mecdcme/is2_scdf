package it.istat.is2.notificator.request;

import it.istat.is2.notificator.domain.enums.TaskStatus;
import lombok.Data;

@Data
public class TaskStatusRequest {
    private Long idElaborazione;
    private Long idStepInstance;
    private TaskStatus taskStatus;
}
