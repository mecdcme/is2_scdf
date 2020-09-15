package it.istat.is2.notificator.response;

import it.istat.is2.notificator.domain.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class TaskStatusResponse {
    private Long processId;
    private TaskStatus status;

    private TaskStatusResponse(Long processId, TaskStatus status) {
        this.processId = processId;
        this.status = status;
    }

    public Long getProcessId() {
        return processId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public static class Builder {
        private Long processId;
        private TaskStatus status;

        public Builder processId(Long processId) {
            this.processId = processId;
            return this;
        }

        public Builder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public TaskStatusResponse build() {
            return new TaskStatusResponse(this.processId, this.status);
        }
    }
}
